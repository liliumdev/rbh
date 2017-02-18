
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/rests/restaurants/conf/routes
// @DATE:Thu Feb 23 00:25:18 CET 2017

import play.api.routing.JavaScriptReverseRoute
import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers.javascript {
  import ReverseRouteContext.empty

  // @LINE:34
  class ReverseRestaurantController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:37
    def didRate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.didRate",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/restaurants/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0) + "/did-rate"})
        }
      """
    )
  
    // @LINE:35
    def get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.get",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/restaurants/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:34
    def all: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.all",
      """
        function(limit0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/restaurants" + _qS([(limit0 == null ? null : (""" + implicitly[QueryStringBindable[Integer]].javascriptUnbind + """)("limit", limit0))])})
        }
      """
    )
  
    // @LINE:36
    def rate: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RestaurantController.rate",
      """
        function(id0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/restaurants/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0) + "/rate"})
        }
      """
    )
  
  }

  // @LINE:43
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:43
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:26
  class ReverseCityController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:30
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CityController.delete",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/cities/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:31
    def allFromCountry: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CityController.allFromCountry",
      """
        function(countryId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/countries/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("countryId", countryId0) + "/cities"})
        }
      """
    )
  
    // @LINE:29
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CityController.update",
      """
        function(id0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/cities/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:28
    def get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CityController.get",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/cities/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:27
    def allWithCount: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CityController.allWithCount",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/cities/with-count"})
        }
      """
    )
  
    // @LINE:26
    def all: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CityController.all",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/cities"})
        }
      """
    )
  
    // @LINE:32
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CityController.create",
      """
        function(countryId0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/countries/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("countryId", countryId0) + "/cities"})
        }
      """
    )
  
  }

  // @LINE:20
  class ReverseCountryController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CountryController.delete",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/countries/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:22
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CountryController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/countries"})
        }
      """
    )
  
    // @LINE:23
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CountryController.update",
      """
        function(id0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/countries/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:21
    def get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CountryController.get",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/countries/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:20
    def all: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CountryController.all",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/countries"})
        }
      """
    )
  
  }

  // @LINE:40
  class ReverseDatabaseFakerController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:40
    def seedDatabase: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.DatabaseFakerController.seedDatabase",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "seedDb"})
        }
      """
    )
  
  }

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:8
  class ReverseAccountController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def forgot: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AccountController.forgot",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/accounts/forgot"})
        }
      """
    )
  
    // @LINE:17
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AccountController.delete",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/accounts/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:14
    def reset: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AccountController.reset",
      """
        function(token0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/accounts/reset/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token0))})
        }
      """
    )
  
    // @LINE:15
    def isValidToken: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AccountController.isValidToken",
      """
        function(token0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/accounts/valid-token/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("token", encodeURIComponent(token0))})
        }
      """
    )
  
    // @LINE:16
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AccountController.update",
      """
        function(id0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/accounts/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:10
    def emailExists: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AccountController.emailExists",
      """
        function(email0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/accounts/exists/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("email", encodeURIComponent(email0))})
        }
      """
    )
  
    // @LINE:9
    def get: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AccountController.get",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/accounts/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id0)})
        }
      """
    )
  
    // @LINE:11
    def register: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AccountController.register",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/accounts"})
        }
      """
    )
  
    // @LINE:8
    def all: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AccountController.all",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/accounts"})
        }
      """
    )
  
    // @LINE:12
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.AccountController.login",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "api/v1/accounts/login"})
        }
      """
    )
  
  }


}
