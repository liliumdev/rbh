package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class HomeController extends Controller {
    public Result index() {
        return ok(views.html.main.render());
    }

    public Result catchAll(String path) {
        return ok(views.html.main.render());
    }
}
