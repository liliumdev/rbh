package controllers;

import models.City;
import play.data.Form;
import play.db.jpa.Transactional;
import services.CityService;
import play.mvc.Result;
import services.exceptions.ServiceException;

import static play.libs.Json.toJson;

public class CityController extends BaseController<City, CityService> {

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result create(Long countryId)
    {
        try {
            Form<City> form = formFactory.form(City.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            return created(toJson(service.create(countryId, form.get())));
        } catch(ServiceException e) {
            return badRequest("Service error in CityController@create");
        } catch (Exception e) {
            return internalServerError("Internal server error in CityController@create");
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result update(Long id) {
        return super.update(id);
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result delete(Long id)
    {
        try {
            // Are there any restaurants in this city?
            City city = service.get(id);
            Integer numOfRestaurants = city.getRestaurants().size();
            if(numOfRestaurants > 0)
                return badRequest("Can't delete this city. There are " + numOfRestaurants +
                                    " existing restaurants in this city!");

            return super.delete(id);
        } catch(ServiceException e) {
            return badRequest("Service error in CityController@delete");
        } catch (Exception e) {
            return internalServerError("Internal server error in CityController@delete");
        }
    }

    @Transactional
    public Result allFromCountry(Long countryId)
    {
        try {
            return ok(toJson(service.allFromCountry(countryId)));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@create");
        } catch (Exception e) {
            return internalServerError("Internal server error in BaseController@create");
        }
    }

    @Transactional
    public Result allWithCount() {
        try {
            return ok(toJson(service.allWithCount()));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@create");
        } catch (Exception e) {
            return internalServerError("Internal server error in BaseController@create");
        }
    }
}
