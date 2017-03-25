package controllers;

import models.City;
import play.db.jpa.Transactional;
import services.CityService;
import play.mvc.Result;
import services.exceptions.ServiceException;

import static play.libs.Json.toJson;

public class CityController extends BaseController<City, CityService> {
    @Transactional
    public Result allFromCountry(Long countryId) {
        try {
            return ok(toJson(service.allFromCountry(countryId)));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@create");
        } catch(Exception e) {
            return internalServerError("Internal server error in BaseController@create");
        }
    }

    @Transactional
    public Result allWithCount() {
        try {
            return ok(toJson(service.allWithCount()));
        } catch(ServiceException e) {
            return badRequest("Service error in BaseController@create");
        } catch(Exception e) {
            return internalServerError("Internal server error in BaseController@create");
        }
    }
}
