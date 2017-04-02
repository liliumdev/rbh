package controllers.admin;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.BaseAdminController;
import controllers.SecureAuth;
import models.City;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.CityService;
import services.exceptions.ServiceException;
import static play.libs.Json.toJson;

public class CityController extends BaseAdminController<City, CityService> {

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result create(Long countryId) {
        try {
            City city = Json.mapper().readValue(request().body().asJson().toString(), City.class);

            return created(toJson(service.create(countryId, city)));
        } catch(ServiceException e) {
            return badRequest("Service error in CityController@create");
        } catch(Exception e) {
            return internalServerError("Internal server error in CityController@create");
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result updateBoundary(Long id) {
        try {
            City city = Json.mapper().readValue(request().body().asJson().toString(), City.class);

            return ok(toJson(service.update(id, city)));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@update");
        } catch(Exception e) {
            return internalServerError("Internal server error in BaseController@update");
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result delete(Long id) {
        try {
            // Are there any restaurants in this city?
            City city = service.get(id);
            Integer numOfRestaurants = city.getRestaurants().size();
            if(numOfRestaurants > 0) {
                return badRequest("Can't delete this city. There are " + numOfRestaurants +
                                          " existing restaurants in this city!");
            }

            return super.delete(id);
        } catch(ServiceException e) {
            return badRequest("Service error in CityController@delete");
        } catch(Exception e) {
            return internalServerError("Internal server error in CityController@delete");
        }
    }
}
