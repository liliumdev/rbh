package controllers;

import models.Country;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.CountryService;

public class CountryController extends BaseController<Country, CountryService> {
}
