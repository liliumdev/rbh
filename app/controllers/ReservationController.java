package controllers;

/**
 * Created by Lilium on 23.2.2017.
 */

import javax.inject.Inject;

import controllers.forms.CompleteReservationForm;
import controllers.forms.ReservationForm;
import models.Reservation;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.ReservationService;
import services.RestaurantService;
import services.exceptions.ServiceException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static play.libs.Json.toJson;

public class ReservationController extends BaseController<Reservation, ReservationService> {
    protected RestaurantService restaurantService;

    @Inject
    public void setRestaurantService(RestaurantService restaurantService)
    {
        this.restaurantService = restaurantService;
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"NORMAL", "ADMIN"})
    public Result myReservations() {
        try {
            return ok(Json.toJson(service.getReservationsByEmail(request().username())));
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in ReservationController@myReservations"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    @SecureAuth.Authenticated(roles={"NORMAL", "ADMIN"})
    public Result create() {
        try {
            Form<CompleteReservationForm> form = formFactory.form(CompleteReservationForm.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            CompleteReservationForm data = form.get();

            String time = data.getTime();
            Long restaurantId = data.getRestaurantId();
            Long tableId = data.getTableId();
            Integer persons = data.getPersons();

            if(service.isReservationAvailable(time, restaurantId, tableId))
                return ok(Json.toJson(service.create(restaurantId, time, tableId, persons, request().username())));

            return badRequest("Oops, seems that someone got this table before you did!");

        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in ReservationController@myReservations"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result isReservationAvailable() {
        try {
            Form<CompleteReservationForm> form = formFactory.form(CompleteReservationForm.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            CompleteReservationForm data = form.get();

            Boolean available = service.isReservationAvailable(data.getTime(), data.getRestaurantId(), data.getTableId());
            if(available)
                return ok(Json.toJson("Reservation is available"));

            return badRequest("Oops, seems that someone got this table before you did!");
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in ReservationController@reservationsToday"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result getReservationSuggestions() {
        try {
            Form<ReservationForm> form = formFactory.form(ReservationForm.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            ReservationForm data = form.get();

            LocalDateTime today = LocalDateTime.now();

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime wishedTime = LocalDateTime.parse(data.getWishedTime(), formatter);

                // Normalize to half hour increment
                if(wishedTime.getMinute() != 0 && wishedTime.getMinute() != 30)
                    wishedTime.minusMinutes(wishedTime.getMinute());

                if(wishedTime.isBefore(today))
                    return badRequest(Json.toJson("You cannot reserve in the past!"));

                LocalDateTime fromTime = wishedTime.minusHours(1).minusMinutes(30);
                LocalDateTime toTime = fromTime.plusHours(3);

                LocalDateTime minimum = LocalDateTime.of(wishedTime.toLocalDate(), LocalTime.of(8, 0, 0));
                LocalDateTime maximum = LocalDateTime.of(wishedTime.toLocalDate(), LocalTime.of(21, 30, 0));

                // Correct from and to suggestions if it's before working hours or before today
                if(fromTime.isBefore(minimum)) {
                    fromTime = minimum;
                    toTime = fromTime.plusHours(3);
                }

                if(toTime.isAfter(maximum)) {
                    toTime = maximum;
                    fromTime = toTime.minusHours(3);
                }

                String from = fromTime.format(formatter);
                String to = toTime.format(formatter);

                return ok(toJson(service.getReservationSuggestions(from, to, data.getRestaurantId(), data.getPersons())));

            } catch (DateTimeParseException e) {
                return badRequest(Json.toJson("Cannot parse input time."));
            }

        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in ReservationController@getReservationSuggestions"));
        }
    }

}
