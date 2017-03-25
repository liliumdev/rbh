package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AccountService;
import services.CityService;
import services.RestaurantService;
import javax.inject.Inject;

public class AdminDashboardController extends Controller {
    private RestaurantService restaurantService;
    private CityService cityService;
    private AccountService accountService;

    @Inject
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Inject
    public void setCityService(CityService cityService) {
        this.cityService = cityService;
    }

    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    // Dashboard statistics
    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result index() {
        long restaurants = restaurantService.count();
        long locations = cityService.count();
        long users = accountService.count();

        ObjectNode stats = Json.newObject();
        stats.put("restaurants", restaurants);
        stats.put("locations", locations);
        stats.put("users", users);

        return ok(stats);
    }

}
