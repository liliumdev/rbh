package controllers;

import controllers.forms.RateForm;
import models.Restaurant;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.RestaurantService;
import services.ReviewService;

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
    public Result all(Integer limit) {
        if(limit == null || limit == 0)
            return super.all();

        return ok(toJson(service.random(limit)));
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

            // Does this account actually exist?
            RateForm data = form.get();
            Double newRating = service.rate(id, data.getRating(), data.getDescription(), ctx().request().username());
            if(newRating == null)
                return badRequest(Json.toJson("There's been an error while rating this restaurant."));

            return ok(Json.newObject().put("rating", newRating));
        } catch (Exception e) {
            return internalServerError(Json.toJson("Internal server error in RestaurantController@rate"));
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"NORMAL", "ADMIN"})
    public Result didRate(Long id) {
        return ok(Json.toJson(reviewService.didReview(id, ctx().request().username())));
    }
}
