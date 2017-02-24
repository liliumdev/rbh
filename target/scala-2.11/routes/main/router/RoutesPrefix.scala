
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/rests/restaurants/conf/routes
// @DATE:Fri Feb 24 07:08:01 CET 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
