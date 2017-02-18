
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/rests/restaurants/conf/routes
// @DATE:Thu Feb 23 00:25:18 CET 2017

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseRestaurantController RestaurantController = new controllers.ReverseRestaurantController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseCityController CityController = new controllers.ReverseCityController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseCountryController CountryController = new controllers.ReverseCountryController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseDatabaseFakerController DatabaseFakerController = new controllers.ReverseDatabaseFakerController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHomeController HomeController = new controllers.ReverseHomeController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseAccountController AccountController = new controllers.ReverseAccountController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseRestaurantController RestaurantController = new controllers.javascript.ReverseRestaurantController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseCityController CityController = new controllers.javascript.ReverseCityController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseCountryController CountryController = new controllers.javascript.ReverseCountryController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseDatabaseFakerController DatabaseFakerController = new controllers.javascript.ReverseDatabaseFakerController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHomeController HomeController = new controllers.javascript.ReverseHomeController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseAccountController AccountController = new controllers.javascript.ReverseAccountController(RoutesPrefix.byNamePrefix());
  }

}
