package controllers;

import models.Category;
import play.db.jpa.Transactional;
import services.CategoryService;

import play.mvc.Result;
import services.exceptions.ServiceException;

public class CategoryController extends BaseController<Category, CategoryService> {

}
