
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/rests/restaurants/conf/routes
// @DATE:Thu Feb 23 00:25:18 CET 2017

import play.api.mvc.{ QueryStringBindable, PathBindable, Call, JavascriptLiteral }
import play.core.routing.{ HandlerDef, ReverseRouteContext, queryString, dynamicString }


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:6
package controllers {

  // @LINE:34
  class ReverseRestaurantController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:37
    def didRate(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/restaurants/" + implicitly[PathBindable[Long]].unbind("id", id) + "/did-rate")
    }
  
    // @LINE:35
    def get(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/restaurants/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:34
    def all(limit:Integer = 0): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/restaurants" + queryString(List(if(limit == 0) None else Some(implicitly[QueryStringBindable[Integer]].unbind("limit", limit)))))
    }
  
    // @LINE:36
    def rate(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "api/v1/restaurants/" + implicitly[PathBindable[Long]].unbind("id", id) + "/rate")
    }
  
  }

  // @LINE:43
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:43
    def versioned(file:Asset): Call = {
      implicit val _rrc = new ReverseRouteContext(Map(("path", "/public")))
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:26
  class ReverseCityController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:30
    def delete(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "api/v1/cities/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:31
    def allFromCountry(countryId:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/countries/" + implicitly[PathBindable[Long]].unbind("countryId", countryId) + "/cities")
    }
  
    // @LINE:29
    def update(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("PUT", _prefix + { _defaultPrefix } + "api/v1/cities/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:28
    def get(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/cities/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:27
    def allWithCount(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/cities/with-count")
    }
  
    // @LINE:26
    def all(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/cities")
    }
  
    // @LINE:32
    def create(countryId:Long): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "api/v1/countries/" + implicitly[PathBindable[Long]].unbind("countryId", countryId) + "/cities")
    }
  
  }

  // @LINE:20
  class ReverseCountryController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def delete(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "api/v1/countries/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:22
    def create(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "api/v1/countries")
    }
  
    // @LINE:23
    def update(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("PUT", _prefix + { _defaultPrefix } + "api/v1/countries/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:21
    def get(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/countries/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:20
    def all(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/countries")
    }
  
  }

  // @LINE:40
  class ReverseDatabaseFakerController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:40
    def seedDatabase(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "seedDb")
    }
  
  }

  // @LINE:6
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def index(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix)
    }
  
  }

  // @LINE:8
  class ReverseAccountController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def forgot(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "api/v1/accounts/forgot")
    }
  
    // @LINE:17
    def delete(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("DELETE", _prefix + { _defaultPrefix } + "api/v1/accounts/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:14
    def reset(token:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "api/v1/accounts/reset/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:15
    def isValidToken(token:String): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "api/v1/accounts/valid-token/" + implicitly[PathBindable[String]].unbind("token", dynamicString(token)))
    }
  
    // @LINE:16
    def update(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("PUT", _prefix + { _defaultPrefix } + "api/v1/accounts/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:10
    def emailExists(email:String): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/accounts/exists/" + implicitly[PathBindable[String]].unbind("email", dynamicString(email)))
    }
  
    // @LINE:9
    def get(id:Long): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/accounts/" + implicitly[PathBindable[Long]].unbind("id", id))
    }
  
    // @LINE:11
    def register(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "api/v1/accounts")
    }
  
    // @LINE:8
    def all(): Call = {
      import ReverseRouteContext.empty
      Call("GET", _prefix + { _defaultPrefix } + "api/v1/accounts")
    }
  
    // @LINE:12
    def login(): Call = {
      import ReverseRouteContext.empty
      Call("POST", _prefix + { _defaultPrefix } + "api/v1/accounts/login")
    }
  
  }


}
