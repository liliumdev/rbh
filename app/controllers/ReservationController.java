package controllers;

import javax.inject.Inject;

import controllers.forms.CompleteReservationForm;
import controllers.forms.ReservationForm;
import models.Reservation;
import models.Restaurant;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.ReservationService;
import services.RestaurantService;
import services.exceptions.ServiceException;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static play.libs.Json.toJson;

public class ReservationController extends BaseController<Reservation, ReservationService> {
    protected RestaurantService restaurantService;

    @Inject
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"NORMAL", "ADMIN"})
    public Result myReservations() {
        try {
            return ok(Json.toJson(service.getReservationsByEmail(request().username())));
        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in ReservationController@myReservations"));
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"NORMAL", "ADMIN"})
    public Result deleteMyReservation(Long id) {
        try {
            if(service.deleteReservation(id, request().username())) {
                return ok();
            } else {
                return badRequest("Couldn't cancel this reservation. The restaurant policy is that you can't cancel the reservation too soon to the reserved time!");
            }
        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in ReservationController@deleteMyReservation"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    @SecureAuth.Authenticated(roles = {"NORMAL", "ADMIN"})
    public Result create() {
        try {
            Form<CompleteReservationForm> form = formFactory.form(CompleteReservationForm.class).bindFromRequest();
            if(form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }

            CompleteReservationForm data = form.get();

            String time = data.getTime();
            Long restaurantId = data.getRestaurantId();
            Long tableId = data.getTableId();
            Integer persons = data.getPersons();
            String request = data.getRequest();

            if(service.isReservationAvailable(time, restaurantId, tableId, persons)) {
                return ok(Json.toJson(service.create(restaurantId, time, tableId, persons, request().username(), request)));
            }

            return badRequest("Oops, seems that someone got this table before you did!");

        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in ReservationController@myReservations"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result isReservationAvailable() {
        try {
            Form<CompleteReservationForm> form = formFactory.form(CompleteReservationForm.class).bindFromRequest();
            if(form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }

            CompleteReservationForm data = form.get();

            Boolean available = service.isReservationAvailable(data.getTime(), data.getRestaurantId(), data.getTableId(), data.getPersons());
            if(available) {
                return ok(Json.toJson("Reservation is available"));
            }

            return badRequest("Oops, seems that someone got this table before you did!");
        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in ReservationController@reservationsToday"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result getReservationSuggestions() {
        try {
            Form<ReservationForm> form = formFactory.form(ReservationForm.class).bindFromRequest();
            if(form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }

            ReservationForm data = form.get();

            LocalDateTime today = LocalDateTime.now();

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime wishedTime = LocalDateTime.parse(data.getWishedTime(), formatter);

                // Normalize to half hour increment
                if(wishedTime.getMinute() != 0 && wishedTime.getMinute() != 30) {
                    wishedTime.minusMinutes(wishedTime.getMinute());
                }

                if(wishedTime.isBefore(today)) {
                    return badRequest(Json.toJson("You cannot reserve in the past!"));
                }

                LocalDateTime fromTime = wishedTime.minusHours(1).minusMinutes(30);
                LocalDateTime toTime = fromTime.plusHours(3);

                // Restaurant should have it's own working hours
                Restaurant restaurant = restaurantService.get(data.getRestaurantId());
                Date workStart = restaurant.getWorkingTimeFrom();
                Date workEnd = restaurant.getWorkingTimeTo();

                LocalDateTime minimum = LocalDateTime.of(wishedTime.toLocalDate(), LocalTime.of(workStart.getHours(), workStart.getMinutes(), 0));
                LocalDateTime maximum = LocalDateTime.of(wishedTime.toLocalDate(), LocalTime.of(workEnd.getHours(), workEnd.getMinutes(), 0));

                // Correct from and to suggestions if it's before working hours or before today
                if(fromTime.isBefore(minimum)) {
                    fromTime = minimum;
                    toTime = fromTime.plusHours(3);
                }

                if(toTime.isAfter(maximum)) {
                    toTime = maximum;
                    fromTime = toTime.minusHours(3);
                    if(fromTime.isBefore(minimum)) {
                        fromTime = minimum; // Because of minusHours(3) we have to check this again
                    }
                }

                // If from time is before the current time
                if(fromTime.isBefore(today)) {
                    fromTime = today;
                    // Round to next half-hour interval (if it's, say, 4:34, it will round to 5:00, if it's
                    // 4:15, it will round to 4:30
                    Integer currentMinutes = fromTime.getMinute();
                    fromTime = fromTime.plusMinutes((currentMinutes >= 30 ? 60 : 30) - currentMinutes);
                }


                String from = fromTime.format(formatter);
                String to = toTime.format(formatter);

                return ok(toJson(service.getReservationSuggestions(from, to, data.getRestaurantId(), data.getPersons())));

            } catch(DateTimeParseException e) {
                return badRequest(Json.toJson("Cannot parse input time."));
            }

        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in ReservationController@getReservationSuggestions"));
        }
    }

}
