package controllers;

import models.Category;
import play.db.jpa.Transactional;
import services.CategoryService;

import play.mvc.Result;
import services.exceptions.ServiceException;

public class CategoryController extends BaseController<Category, CategoryService> {
    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result create() {
        return super.create();
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result update(Long id) {
        return super.update(id);
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result delete(Long id)
    {
        try {
            // Are there any restaurants in this category?
            Category category = service.get(id);
            Integer numOfRestaurants = category.getRestaurants().size();
            if(numOfRestaurants > 0)
                return badRequest("Can't delete this category. There are " + numOfRestaurants +
                        " existing restaurants listed with this category!");

            return super.delete(id);
        } catch(ServiceException e) {
            return badRequest("Service error in CategoryController@delete");
        } catch (Exception e) {
            return internalServerError("Internal server error in CategoryController@delete");
        }
    }
}
