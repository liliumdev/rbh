
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/rests/restaurants/conf/routes
// @DATE:Fri Feb 24 07:08:01 CET 2017

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  // @LINE:8
  AccountController_8: controllers.AccountController,
  // @LINE:20
  CountryController_1: controllers.CountryController,
  // @LINE:26
  CityController_6: controllers.CityController,
  // @LINE:34
  RestaurantController_2: controllers.RestaurantController,
  // @LINE:40
  ReservationController_5: controllers.ReservationController,
  // @LINE:45
  CategoryController_3: controllers.CategoryController,
  // @LINE:51
  DatabaseFakerController_4: controllers.DatabaseFakerController,
  // @LINE:54
  Assets_7: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController,
    // @LINE:8
    AccountController_8: controllers.AccountController,
    // @LINE:20
    CountryController_1: controllers.CountryController,
    // @LINE:26
    CityController_6: controllers.CityController,
    // @LINE:34
    RestaurantController_2: controllers.RestaurantController,
    // @LINE:40
    ReservationController_5: controllers.ReservationController,
    // @LINE:45
    CategoryController_3: controllers.CategoryController,
    // @LINE:51
    DatabaseFakerController_4: controllers.DatabaseFakerController,
    // @LINE:54
    Assets_7: controllers.Assets
  ) = this(errorHandler, HomeController_0, AccountController_8, CountryController_1, CityController_6, RestaurantController_2, ReservationController_5, CategoryController_3, DatabaseFakerController_4, Assets_7, "/")

  import ReverseRouteContext.empty

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, AccountController_8, CountryController_1, CityController_6, RestaurantController_2, ReservationController_5, CategoryController_3, DatabaseFakerController_4, Assets_7, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/accounts""", """controllers.AccountController.all()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/accounts/""" + "$" + """id<[^/]+>""", """controllers.AccountController.get(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/accounts/exists/""" + "$" + """email<[^/]+>""", """controllers.AccountController.emailExists(email:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/accounts""", """controllers.AccountController.register()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/accounts/login""", """controllers.AccountController.login()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/accounts/forgot""", """controllers.AccountController.forgot()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/accounts/reset/""" + "$" + """token<[^/]+>""", """controllers.AccountController.reset(token:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/accounts/valid-token/""" + "$" + """token<[^/]+>""", """controllers.AccountController.isValidToken(token:String)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/accounts/""" + "$" + """id<[^/]+>""", """controllers.AccountController.update(id:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/accounts/""" + "$" + """id<[^/]+>""", """controllers.AccountController.delete(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/countries""", """controllers.CountryController.all()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/countries/""" + "$" + """id<[^/]+>""", """controllers.CountryController.get(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/countries""", """controllers.CountryController.create()"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/countries/""" + "$" + """id<[^/]+>""", """controllers.CountryController.update(id:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/countries/""" + "$" + """id<[^/]+>""", """controllers.CountryController.delete(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/cities""", """controllers.CityController.all()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/cities/with-count""", """controllers.CityController.allWithCount()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/cities/""" + "$" + """id<[^/]+>""", """controllers.CityController.get(id:Long)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/cities/""" + "$" + """id<[^/]+>""", """controllers.CityController.update(id:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/cities/""" + "$" + """id<[^/]+>""", """controllers.CityController.delete(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/countries/""" + "$" + """countryId<[^/]+>/cities""", """controllers.CityController.allFromCountry(countryId:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/countries/""" + "$" + """countryId<[^/]+>/cities""", """controllers.CityController.create(countryId:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/restaurants""", """controllers.RestaurantController.all(limit:Integer ?= 0)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/restaurants/""" + "$" + """id<[^/]+>""", """controllers.RestaurantController.get(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/restaurants/""" + "$" + """id<[^/]+>/rate""", """controllers.RestaurantController.rate(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/restaurants/""" + "$" + """id<[^/]+>/did-rate""", """controllers.RestaurantController.didRate(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/restaurants/""" + "$" + """id<[^/]+>/reservations-today""", """controllers.RestaurantController.getNumberOfReservationsToday(id:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/reservations""", """controllers.ReservationController.create()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/reservations/suggestions""", """controllers.ReservationController.getReservationSuggestions()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/reservations/available""", """controllers.ReservationController.isReservationAvailable()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/reservations/my""", """controllers.ReservationController.myReservations()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/categories""", """controllers.CategoryController.all()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/categories""", """controllers.CategoryController.create()"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/categories/""" + "$" + """id<[^/]+>""", """controllers.CategoryController.update(id:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """api/v1/categories/""" + "$" + """id<[^/]+>""", """controllers.CategoryController.delete(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """seedDb""", """controllers.DatabaseFakerController.seedDatabase()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      """""",
      this.prefix + """"""
    )
  )

  // @LINE:8
  private[this] lazy val controllers_AccountController_all1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/accounts")))
  )
  private[this] lazy val controllers_AccountController_all1_invoker = createInvoker(
    AccountController_8.all(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AccountController",
      "all",
      Nil,
      "GET",
      """""",
      this.prefix + """api/v1/accounts"""
    )
  )

  // @LINE:9
  private[this] lazy val controllers_AccountController_get2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/accounts/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AccountController_get2_invoker = createInvoker(
    AccountController_8.get(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AccountController",
      "get",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """api/v1/accounts/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:10
  private[this] lazy val controllers_AccountController_emailExists3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/accounts/exists/"), DynamicPart("email", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AccountController_emailExists3_invoker = createInvoker(
    AccountController_8.emailExists(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AccountController",
      "emailExists",
      Seq(classOf[String]),
      "GET",
      """""",
      this.prefix + """api/v1/accounts/exists/""" + "$" + """email<[^/]+>"""
    )
  )

  // @LINE:11
  private[this] lazy val controllers_AccountController_register4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/accounts")))
  )
  private[this] lazy val controllers_AccountController_register4_invoker = createInvoker(
    AccountController_8.register(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AccountController",
      "register",
      Nil,
      "POST",
      """""",
      this.prefix + """api/v1/accounts"""
    )
  )

  // @LINE:12
  private[this] lazy val controllers_AccountController_login5_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/accounts/login")))
  )
  private[this] lazy val controllers_AccountController_login5_invoker = createInvoker(
    AccountController_8.login(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AccountController",
      "login",
      Nil,
      "POST",
      """""",
      this.prefix + """api/v1/accounts/login"""
    )
  )

  // @LINE:13
  private[this] lazy val controllers_AccountController_forgot6_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/accounts/forgot")))
  )
  private[this] lazy val controllers_AccountController_forgot6_invoker = createInvoker(
    AccountController_8.forgot(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AccountController",
      "forgot",
      Nil,
      "POST",
      """""",
      this.prefix + """api/v1/accounts/forgot"""
    )
  )

  // @LINE:14
  private[this] lazy val controllers_AccountController_reset7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/accounts/reset/"), DynamicPart("token", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AccountController_reset7_invoker = createInvoker(
    AccountController_8.reset(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AccountController",
      "reset",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """api/v1/accounts/reset/""" + "$" + """token<[^/]+>"""
    )
  )

  // @LINE:15
  private[this] lazy val controllers_AccountController_isValidToken8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/accounts/valid-token/"), DynamicPart("token", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AccountController_isValidToken8_invoker = createInvoker(
    AccountController_8.isValidToken(fakeValue[String]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AccountController",
      "isValidToken",
      Seq(classOf[String]),
      "POST",
      """""",
      this.prefix + """api/v1/accounts/valid-token/""" + "$" + """token<[^/]+>"""
    )
  )

  // @LINE:16
  private[this] lazy val controllers_AccountController_update9_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/accounts/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AccountController_update9_invoker = createInvoker(
    AccountController_8.update(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AccountController",
      "update",
      Seq(classOf[Long]),
      "PUT",
      """""",
      this.prefix + """api/v1/accounts/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:17
  private[this] lazy val controllers_AccountController_delete10_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/accounts/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_AccountController_delete10_invoker = createInvoker(
    AccountController_8.delete(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.AccountController",
      "delete",
      Seq(classOf[Long]),
      "DELETE",
      """""",
      this.prefix + """api/v1/accounts/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:20
  private[this] lazy val controllers_CountryController_all11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/countries")))
  )
  private[this] lazy val controllers_CountryController_all11_invoker = createInvoker(
    CountryController_1.all(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CountryController",
      "all",
      Nil,
      "GET",
      """""",
      this.prefix + """api/v1/countries"""
    )
  )

  // @LINE:21
  private[this] lazy val controllers_CountryController_get12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/countries/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CountryController_get12_invoker = createInvoker(
    CountryController_1.get(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CountryController",
      "get",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """api/v1/countries/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:22
  private[this] lazy val controllers_CountryController_create13_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/countries")))
  )
  private[this] lazy val controllers_CountryController_create13_invoker = createInvoker(
    CountryController_1.create(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CountryController",
      "create",
      Nil,
      "POST",
      """""",
      this.prefix + """api/v1/countries"""
    )
  )

  // @LINE:23
  private[this] lazy val controllers_CountryController_update14_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/countries/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CountryController_update14_invoker = createInvoker(
    CountryController_1.update(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CountryController",
      "update",
      Seq(classOf[Long]),
      "PUT",
      """""",
      this.prefix + """api/v1/countries/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:24
  private[this] lazy val controllers_CountryController_delete15_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/countries/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CountryController_delete15_invoker = createInvoker(
    CountryController_1.delete(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CountryController",
      "delete",
      Seq(classOf[Long]),
      "DELETE",
      """""",
      this.prefix + """api/v1/countries/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:26
  private[this] lazy val controllers_CityController_all16_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/cities")))
  )
  private[this] lazy val controllers_CityController_all16_invoker = createInvoker(
    CityController_6.all(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CityController",
      "all",
      Nil,
      "GET",
      """""",
      this.prefix + """api/v1/cities"""
    )
  )

  // @LINE:27
  private[this] lazy val controllers_CityController_allWithCount17_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/cities/with-count")))
  )
  private[this] lazy val controllers_CityController_allWithCount17_invoker = createInvoker(
    CityController_6.allWithCount(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CityController",
      "allWithCount",
      Nil,
      "GET",
      """""",
      this.prefix + """api/v1/cities/with-count"""
    )
  )

  // @LINE:28
  private[this] lazy val controllers_CityController_get18_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/cities/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CityController_get18_invoker = createInvoker(
    CityController_6.get(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CityController",
      "get",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """api/v1/cities/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:29
  private[this] lazy val controllers_CityController_update19_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/cities/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CityController_update19_invoker = createInvoker(
    CityController_6.update(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CityController",
      "update",
      Seq(classOf[Long]),
      "PUT",
      """""",
      this.prefix + """api/v1/cities/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:30
  private[this] lazy val controllers_CityController_delete20_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/cities/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CityController_delete20_invoker = createInvoker(
    CityController_6.delete(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CityController",
      "delete",
      Seq(classOf[Long]),
      "DELETE",
      """""",
      this.prefix + """api/v1/cities/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:31
  private[this] lazy val controllers_CityController_allFromCountry21_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/countries/"), DynamicPart("countryId", """[^/]+""",true), StaticPart("/cities")))
  )
  private[this] lazy val controllers_CityController_allFromCountry21_invoker = createInvoker(
    CityController_6.allFromCountry(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CityController",
      "allFromCountry",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """api/v1/countries/""" + "$" + """countryId<[^/]+>/cities"""
    )
  )

  // @LINE:32
  private[this] lazy val controllers_CityController_create22_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/countries/"), DynamicPart("countryId", """[^/]+""",true), StaticPart("/cities")))
  )
  private[this] lazy val controllers_CityController_create22_invoker = createInvoker(
    CityController_6.create(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CityController",
      "create",
      Seq(classOf[Long]),
      "POST",
      """""",
      this.prefix + """api/v1/countries/""" + "$" + """countryId<[^/]+>/cities"""
    )
  )

  // @LINE:34
  private[this] lazy val controllers_RestaurantController_all23_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/restaurants")))
  )
  private[this] lazy val controllers_RestaurantController_all23_invoker = createInvoker(
    RestaurantController_2.all(fakeValue[Integer]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "all",
      Seq(classOf[Integer]),
      "GET",
      """""",
      this.prefix + """api/v1/restaurants"""
    )
  )

  // @LINE:35
  private[this] lazy val controllers_RestaurantController_get24_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/restaurants/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RestaurantController_get24_invoker = createInvoker(
    RestaurantController_2.get(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "get",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """api/v1/restaurants/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:36
  private[this] lazy val controllers_RestaurantController_rate25_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/restaurants/"), DynamicPart("id", """[^/]+""",true), StaticPart("/rate")))
  )
  private[this] lazy val controllers_RestaurantController_rate25_invoker = createInvoker(
    RestaurantController_2.rate(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "rate",
      Seq(classOf[Long]),
      "POST",
      """""",
      this.prefix + """api/v1/restaurants/""" + "$" + """id<[^/]+>/rate"""
    )
  )

  // @LINE:37
  private[this] lazy val controllers_RestaurantController_didRate26_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/restaurants/"), DynamicPart("id", """[^/]+""",true), StaticPart("/did-rate")))
  )
  private[this] lazy val controllers_RestaurantController_didRate26_invoker = createInvoker(
    RestaurantController_2.didRate(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "didRate",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """api/v1/restaurants/""" + "$" + """id<[^/]+>/did-rate"""
    )
  )

  // @LINE:38
  private[this] lazy val controllers_RestaurantController_getNumberOfReservationsToday27_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/restaurants/"), DynamicPart("id", """[^/]+""",true), StaticPart("/reservations-today")))
  )
  private[this] lazy val controllers_RestaurantController_getNumberOfReservationsToday27_invoker = createInvoker(
    RestaurantController_2.getNumberOfReservationsToday(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RestaurantController",
      "getNumberOfReservationsToday",
      Seq(classOf[Long]),
      "GET",
      """""",
      this.prefix + """api/v1/restaurants/""" + "$" + """id<[^/]+>/reservations-today"""
    )
  )

  // @LINE:40
  private[this] lazy val controllers_ReservationController_create28_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/reservations")))
  )
  private[this] lazy val controllers_ReservationController_create28_invoker = createInvoker(
    ReservationController_5.create(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ReservationController",
      "create",
      Nil,
      "POST",
      """""",
      this.prefix + """api/v1/reservations"""
    )
  )

  // @LINE:41
  private[this] lazy val controllers_ReservationController_getReservationSuggestions29_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/reservations/suggestions")))
  )
  private[this] lazy val controllers_ReservationController_getReservationSuggestions29_invoker = createInvoker(
    ReservationController_5.getReservationSuggestions(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ReservationController",
      "getReservationSuggestions",
      Nil,
      "POST",
      """""",
      this.prefix + """api/v1/reservations/suggestions"""
    )
  )

  // @LINE:42
  private[this] lazy val controllers_ReservationController_isReservationAvailable30_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/reservations/available")))
  )
  private[this] lazy val controllers_ReservationController_isReservationAvailable30_invoker = createInvoker(
    ReservationController_5.isReservationAvailable(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ReservationController",
      "isReservationAvailable",
      Nil,
      "POST",
      """""",
      this.prefix + """api/v1/reservations/available"""
    )
  )

  // @LINE:43
  private[this] lazy val controllers_ReservationController_myReservations31_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/reservations/my")))
  )
  private[this] lazy val controllers_ReservationController_myReservations31_invoker = createInvoker(
    ReservationController_5.myReservations(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ReservationController",
      "myReservations",
      Nil,
      "GET",
      """""",
      this.prefix + """api/v1/reservations/my"""
    )
  )

  // @LINE:45
  private[this] lazy val controllers_CategoryController_all32_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/categories")))
  )
  private[this] lazy val controllers_CategoryController_all32_invoker = createInvoker(
    CategoryController_3.all(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CategoryController",
      "all",
      Nil,
      "GET",
      """""",
      this.prefix + """api/v1/categories"""
    )
  )

  // @LINE:46
  private[this] lazy val controllers_CategoryController_create33_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/categories")))
  )
  private[this] lazy val controllers_CategoryController_create33_invoker = createInvoker(
    CategoryController_3.create(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CategoryController",
      "create",
      Nil,
      "POST",
      """""",
      this.prefix + """api/v1/categories"""
    )
  )

  // @LINE:47
  private[this] lazy val controllers_CategoryController_update34_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/categories/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CategoryController_update34_invoker = createInvoker(
    CategoryController_3.update(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CategoryController",
      "update",
      Seq(classOf[Long]),
      "PUT",
      """""",
      this.prefix + """api/v1/categories/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:48
  private[this] lazy val controllers_CategoryController_delete35_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("api/v1/categories/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CategoryController_delete35_invoker = createInvoker(
    CategoryController_3.delete(fakeValue[Long]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CategoryController",
      "delete",
      Seq(classOf[Long]),
      "DELETE",
      """""",
      this.prefix + """api/v1/categories/""" + "$" + """id<[^/]+>"""
    )
  )

  // @LINE:51
  private[this] lazy val controllers_DatabaseFakerController_seedDatabase36_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("seedDb")))
  )
  private[this] lazy val controllers_DatabaseFakerController_seedDatabase36_invoker = createInvoker(
    DatabaseFakerController_4.seedDatabase(),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.DatabaseFakerController",
      "seedDatabase",
      Nil,
      "GET",
      """ Deployment helper routes""",
      this.prefix + """seedDb"""
    )
  )

  // @LINE:54
  private[this] lazy val controllers_Assets_versioned37_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned37_invoker = createInvoker(
    Assets_7.versioned(fakeValue[String], fakeValue[Asset]),
    HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      """ Map static resources from the /public folder to the /assets URL path""",
      this.prefix + """assets/""" + "$" + """file<.+>"""
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index())
      }
  
    // @LINE:8
    case controllers_AccountController_all1_route(params) =>
      call { 
        controllers_AccountController_all1_invoker.call(AccountController_8.all())
      }
  
    // @LINE:9
    case controllers_AccountController_get2_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_AccountController_get2_invoker.call(AccountController_8.get(id))
      }
  
    // @LINE:10
    case controllers_AccountController_emailExists3_route(params) =>
      call(params.fromPath[String]("email", None)) { (email) =>
        controllers_AccountController_emailExists3_invoker.call(AccountController_8.emailExists(email))
      }
  
    // @LINE:11
    case controllers_AccountController_register4_route(params) =>
      call { 
        controllers_AccountController_register4_invoker.call(AccountController_8.register())
      }
  
    // @LINE:12
    case controllers_AccountController_login5_route(params) =>
      call { 
        controllers_AccountController_login5_invoker.call(AccountController_8.login())
      }
  
    // @LINE:13
    case controllers_AccountController_forgot6_route(params) =>
      call { 
        controllers_AccountController_forgot6_invoker.call(AccountController_8.forgot())
      }
  
    // @LINE:14
    case controllers_AccountController_reset7_route(params) =>
      call(params.fromPath[String]("token", None)) { (token) =>
        controllers_AccountController_reset7_invoker.call(AccountController_8.reset(token))
      }
  
    // @LINE:15
    case controllers_AccountController_isValidToken8_route(params) =>
      call(params.fromPath[String]("token", None)) { (token) =>
        controllers_AccountController_isValidToken8_invoker.call(AccountController_8.isValidToken(token))
      }
  
    // @LINE:16
    case controllers_AccountController_update9_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_AccountController_update9_invoker.call(AccountController_8.update(id))
      }
  
    // @LINE:17
    case controllers_AccountController_delete10_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_AccountController_delete10_invoker.call(AccountController_8.delete(id))
      }
  
    // @LINE:20
    case controllers_CountryController_all11_route(params) =>
      call { 
        controllers_CountryController_all11_invoker.call(CountryController_1.all())
      }
  
    // @LINE:21
    case controllers_CountryController_get12_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_CountryController_get12_invoker.call(CountryController_1.get(id))
      }
  
    // @LINE:22
    case controllers_CountryController_create13_route(params) =>
      call { 
        controllers_CountryController_create13_invoker.call(CountryController_1.create())
      }
  
    // @LINE:23
    case controllers_CountryController_update14_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_CountryController_update14_invoker.call(CountryController_1.update(id))
      }
  
    // @LINE:24
    case controllers_CountryController_delete15_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_CountryController_delete15_invoker.call(CountryController_1.delete(id))
      }
  
    // @LINE:26
    case controllers_CityController_all16_route(params) =>
      call { 
        controllers_CityController_all16_invoker.call(CityController_6.all())
      }
  
    // @LINE:27
    case controllers_CityController_allWithCount17_route(params) =>
      call { 
        controllers_CityController_allWithCount17_invoker.call(CityController_6.allWithCount())
      }
  
    // @LINE:28
    case controllers_CityController_get18_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_CityController_get18_invoker.call(CityController_6.get(id))
      }
  
    // @LINE:29
    case controllers_CityController_update19_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_CityController_update19_invoker.call(CityController_6.update(id))
      }
  
    // @LINE:30
    case controllers_CityController_delete20_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_CityController_delete20_invoker.call(CityController_6.delete(id))
      }
  
    // @LINE:31
    case controllers_CityController_allFromCountry21_route(params) =>
      call(params.fromPath[Long]("countryId", None)) { (countryId) =>
        controllers_CityController_allFromCountry21_invoker.call(CityController_6.allFromCountry(countryId))
      }
  
    // @LINE:32
    case controllers_CityController_create22_route(params) =>
      call(params.fromPath[Long]("countryId", None)) { (countryId) =>
        controllers_CityController_create22_invoker.call(CityController_6.create(countryId))
      }
  
    // @LINE:34
    case controllers_RestaurantController_all23_route(params) =>
      call(params.fromQuery[Integer]("limit", Some(0))) { (limit) =>
        controllers_RestaurantController_all23_invoker.call(RestaurantController_2.all(limit))
      }
  
    // @LINE:35
    case controllers_RestaurantController_get24_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_RestaurantController_get24_invoker.call(RestaurantController_2.get(id))
      }
  
    // @LINE:36
    case controllers_RestaurantController_rate25_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_RestaurantController_rate25_invoker.call(RestaurantController_2.rate(id))
      }
  
    // @LINE:37
    case controllers_RestaurantController_didRate26_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_RestaurantController_didRate26_invoker.call(RestaurantController_2.didRate(id))
      }
  
    // @LINE:38
    case controllers_RestaurantController_getNumberOfReservationsToday27_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_RestaurantController_getNumberOfReservationsToday27_invoker.call(RestaurantController_2.getNumberOfReservationsToday(id))
      }
  
    // @LINE:40
    case controllers_ReservationController_create28_route(params) =>
      call { 
        controllers_ReservationController_create28_invoker.call(ReservationController_5.create())
      }
  
    // @LINE:41
    case controllers_ReservationController_getReservationSuggestions29_route(params) =>
      call { 
        controllers_ReservationController_getReservationSuggestions29_invoker.call(ReservationController_5.getReservationSuggestions())
      }
  
    // @LINE:42
    case controllers_ReservationController_isReservationAvailable30_route(params) =>
      call { 
        controllers_ReservationController_isReservationAvailable30_invoker.call(ReservationController_5.isReservationAvailable())
      }
  
    // @LINE:43
    case controllers_ReservationController_myReservations31_route(params) =>
      call { 
        controllers_ReservationController_myReservations31_invoker.call(ReservationController_5.myReservations())
      }
  
    // @LINE:45
    case controllers_CategoryController_all32_route(params) =>
      call { 
        controllers_CategoryController_all32_invoker.call(CategoryController_3.all())
      }
  
    // @LINE:46
    case controllers_CategoryController_create33_route(params) =>
      call { 
        controllers_CategoryController_create33_invoker.call(CategoryController_3.create())
      }
  
    // @LINE:47
    case controllers_CategoryController_update34_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_CategoryController_update34_invoker.call(CategoryController_3.update(id))
      }
  
    // @LINE:48
    case controllers_CategoryController_delete35_route(params) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_CategoryController_delete35_invoker.call(CategoryController_3.delete(id))
      }
  
    // @LINE:51
    case controllers_DatabaseFakerController_seedDatabase36_route(params) =>
      call { 
        controllers_DatabaseFakerController_seedDatabase36_invoker.call(DatabaseFakerController_4.seedDatabase())
      }
  
    // @LINE:54
    case controllers_Assets_versioned37_route(params) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned37_invoker.call(Assets_7.versioned(path, file))
      }
  }
}
