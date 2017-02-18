
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._


     object main_Scope0 {
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._

class main extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.4*/("""

"""),format.raw/*3.1*/("""<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>RestaurantsApp</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--ENV/DEVELOPMENT-->
    <!--
        <meta name="restaurants-app/config/environment" content="%7B%22modulePrefix%22%3A%22restaurants-app%22%2C%22environment%22%3A%22development%22%2C%22rootURL%22%3A%22/%22%2C%22locationType%22%3A%22auto%22%2C%22EmberENV%22%3A%7B%22FEATURES%22%3A%7B%7D%2C%22EXTEND_PROTOTYPES%22%3A%7B%22Date%22%3Afalse%7D%2C%22apiHost%22%3Anull%7D%2C%22APP%22%3A%7B%22LOG_ACTIVE_GENERATION%22%3Atrue%2C%22LOG_TRANSITIONS%22%3Atrue%2C%22LOG_TRANSITIONS_INTERNAL%22%3Atrue%2C%22LOG_VIEW_LOOKUPS%22%3Atrue%2C%22name%22%3A%22restaurants-app%22%2C%22version%22%3A%220.0.0+e293eee1%22%7D%2C%22g-map%22%3A%7B%22key%22%3A%22AIzaSyC5xfl4ytPUyDUy_yulTluJpZoBZKECEyE%22%2C%22libraries%22%3A%5B%22places%22%5D%7D%2C%22ember-simple-auth%22%3A%7B%22authorizer%22%3A%22authorizer%3Atoken%22%2C%22baseURL%22%3A%22http%3A//localhost%3A9000%22%7D%2C%22ember-simple-auth-token%22%3A%7B%22refreshAccessTokens%22%3Afalse%2C%22authorizer%22%3A%22authorizer%3Atoken%22%2C%22identificationField%22%3A%22email%22%2C%22serverTokenEndpoint%22%3A%22http%3A//localhost%3A9000/api/v1/accounts/login%22%7D%2C%22apiHost%22%3A%22http%3A//localhost%3A9000%22%2C%22exportApplicationGlobal%22%3Atrue%7D" />
    -->
    <!--/ENV/DEVELOPMENT-->

    <!--ENV/PRODUCTION-->
    <meta name="restaurants-app/config/environment" content="%7B%22modulePrefix%22%3A%22restaurants-app%22%2C%22environment%22%3A%22production%22%2C%22rootURL%22%3A%22/%22%2C%22locationType%22%3A%22auto%22%2C%22EmberENV%22%3A%7B%22FEATURES%22%3A%7B%7D%2C%22EXTEND_PROTOTYPES%22%3A%7B%22Date%22%3Afalse%7D%2C%22apiHost%22%3Anull%7D%2C%22APP%22%3A%7B%22name%22%3A%22restaurants-app%22%2C%22version%22%3A%220.0.0+9f7eb052%22%7D%2C%22g-map%22%3A%7B%22key%22%3A%22AIzaSyC5xfl4ytPUyDUy_yulTluJpZoBZKECEyE%22%2C%22libraries%22%3A%5B%22places%22%5D%7D%2C%22ember-simple-auth%22%3A%7B%22authorizer%22%3A%22authorizer%3Atoken%22%2C%22baseURL%22%3A%22http%3A//serene-island-63897.herokuapp.com%22%7D%2C%22ember-simple-auth-token%22%3A%7B%22refreshAccessTokens%22%3Afalse%2C%22authorizer%22%3A%22authorizer%3Atoken%22%2C%22identificationField%22%3A%22email%22%2C%22serverTokenEndpoint%22%3A%22http%3A//serene-island-63897.herokuapp.com/api/v1/accounts/login%22%7D%2C%22apiHost%22%3A%22http%3A//serene-island-63897.herokuapp.com%22%2C%22exportApplicationGlobal%22%3Afalse%7D" />
    <!--/ENV/PRODUCTION-->

        <script type="text/javascript" src="//maps.googleapis.com/maps/api/js?key=AIzaSyC5xfl4ytPUyDUy_yulTluJpZoBZKECEyE&libraries=places"></script>

        <link rel="stylesheet" href=""""),_display_(/*24.39*/routes/*24.45*/.Assets.versioned("stylesheets/vendor.css")),format.raw/*24.88*/("""">
        <link rel="stylesheet" href=""""),_display_(/*25.39*/routes/*25.45*/.Assets.versioned("stylesheets/restaurants-app.css")),format.raw/*25.97*/("""">


    </head>
    <body class="grey-back">


        <script src=""""),_display_(/*32.23*/routes/*32.29*/.Assets.versioned("javascripts/vendor.js")),format.raw/*32.71*/(""""></script>
        <script src=""""),_display_(/*33.23*/routes/*33.29*/.Assets.versioned("javascripts/restaurants-app.js")),format.raw/*33.80*/(""""></script>


    </body>
</html>
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


}

/**/
object main extends main_Scope0.main
              /*
                  -- GENERATED --
                  DATE: Wed Feb 15 19:21:37 CET 2017
                  SOURCE: C:/Users/Lilium/IdeaProjects/untitled/restaurants/app/views/main.scala.html
                  HASH: ef9ae24e243d21d82347a2d9c146f3ff58aee462
                  MATRIX: 736->1|832->3|862->7|3739->2857|3754->2863|3818->2906|3887->2948|3902->2954|3975->3006|4079->3083|4094->3089|4157->3131|4219->3166|4234->3172|4306->3223
                  LINES: 27->1|32->1|34->3|55->24|55->24|55->24|56->25|56->25|56->25|63->32|63->32|63->32|64->33|64->33|64->33
                  -- GENERATED --
              */
          