package controllers;

import controllers.forms.RateForm;
import controllers.forms.RestaurantFilterForm;
import models.Restaurant;
import models.filters.RestaurantFilterBuilder;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.RestaurantService;
import services.ReviewService;
import services.exceptions.ServiceException;

import javax.inject.Inject;

import static play.libs.Json.toJson;

public class RestaurantController extends BaseController<Restaurant, RestaurantService> {
    protected ReviewService reviewService;

    @Inject
    public void setReviewService(ReviewService reviewService)
    {
        this.reviewService = reviewService;
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result filter() {
        try {
            // Validate review form fields
            Form<RestaurantFilterForm> form = formFactory.form(RestaurantFilterForm.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            RestaurantFilterForm data = form.get();

            RestaurantFilterBuilder rfb = new RestaurantFilterBuilder()
                    .setName(data.getName())
                    .setQuery(data.getQuery())
                    .setPricing(data.getPricing())
                    .setRating(data.getRating())
                    .setCategories(data.getCategories())
                    .setSortKey("name")
                    .setSortAsc(true)
                    .setPageNumber(data.getPageNumber())
                    .setPageSize(data.getPageSize());

            return ok(Json.toJson(service.filter(rfb)));

        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in RestaurantController@filter"));
        }

    }

    @Transactional
    public Result all(Integer limit) {
        try {
            if(limit == null || limit == 0)
                return super.all();

            return ok(toJson(service.random(limit)));
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in RestaurantController@all"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    @SecureAuth.Authenticated(roles={"NORMAL", "ADMIN"})
    public Result rate(Long id) {
        try {
            // Validate review form fields
            Form<RateForm> form = formFactory.form(RateForm.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            RateForm data = form.get();
            Double newRating = service.rate(id, data.getRating(), data.getDescription(), ctx().request().username());
            if(newRating == null)
                return badRequest(Json.toJson("There's been an error while rating this restaurant."));

            return ok(Json.newObject().put("rating", newRating));
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in RestaurantController@rate"));
        }
    }

    @Transactional
    public Result getNumberOfReservationsToday(Long id) {
        try {
            return ok(toJson(service.getNumberOfReservationsToday(id)));
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in RestaurantController@reservationsToday"));
        }
    }


    @Transactional
    @SecureAuth.Authenticated(roles={"NORMAL", "ADMIN"})
    public Result didRate(Long id) {
        return ok(Json.toJson(reviewService.didReview(id, ctx().request().username())));
    }
}
