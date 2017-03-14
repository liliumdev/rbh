package controllers.admin;

import controllers.BaseAdminController;
import controllers.SecureAuth;
import controllers.forms.admin.AccountCreationForm;
import models.Account;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.AccountService;
import services.exceptions.ServiceException;

public class AccountController extends BaseAdminController<Account, AccountService> {
    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    @BodyParser.Of(BodyParser.Json.class)
    public Result create(Integer role) {
        try {
            // Validate the registration form
            Form<AccountCreationForm> form = formFactory.form(AccountCreationForm.class).bindFromRequest();

            // Is this email already present in the account table?
            if(service.getByEmail(form.get().getEmail()) != null) {
                form.errors().put("email", ValidationHelper.singleError("email", "This email already exists."));
            }

            if(form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }

            // Currently only roles allowed are NORMAL and ADMIN, that is interval [0, 1]
            if(!(role >= 0 && role <= 1))
                return badRequest(Json.toJson("Unknown role!"));

            // The account has been created
            return created(Json.toJson(service.create(form.get(), role)));
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@create"));
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result demote(Long id)
    {
        try {
            return ok(Json.toJson(service.giveRole(id, AccountService.USER_TYPE.NORMAL.getValue())));
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@demote"));
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result promote(Long id)
    {
        try {
            return ok(Json.toJson(service.giveRole(id, AccountService.USER_TYPE.ADMIN.getValue())));
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@promote"));
        }
    }

}