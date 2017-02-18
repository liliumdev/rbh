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
    public Result create(Long countryId)
    {
        try {
            Form<City> form = formFactory.form(City.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            return created(toJson(service.create(countryId, form.get())));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@create");
        } catch (Exception e) {
            return internalServerError("Internal server error in BaseController@create");
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
