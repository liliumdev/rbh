package controllers;

import models.BaseModel;
import play.db.jpa.Transactional;
import play.mvc.Result;
import services.BaseService;

public class BaseAdminController<M extends BaseModel<M>, S extends BaseService> extends BaseController<M, S> {
    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result create() {
        return super.create();
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result delete(Long id) {
        return super.delete(id);
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result update(Long id) {
        return super.update(id);
    }
}
