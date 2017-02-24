package controllers;

import models.Category;
import play.db.jpa.Transactional;
import services.CategoryService;

import play.mvc.Result;

public class CategoryController extends BaseController<Category, CategoryService> {
    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result create() {
        return super.create();
    }

}
