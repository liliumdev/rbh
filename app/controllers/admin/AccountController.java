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

import static play.libs.Json.toJson;

public class AccountController extends BaseAdminController<Account, AccountService> {
    @Transactional
    @SecureAuth.Authenticated(roles = {"NORMAL", "ADMIN"})
    public Result get(Long id) {
        try {
            // Is the user trying to get his own information?
            Account wantedAccount = service.get(id);
            if(wantedAccount.getEmail().equals(request().username())) {
                return ok(Json.toJson(wantedAccount));
            }

            // If not, see is this user an admin and does he have permission to view any user
            return getAsAdmin(id);
        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@get"));
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result getAsAdmin(Long id) {
        return super.get(id);
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"NORMAL", "ADMIN"})
    public Result update(Long id) {
        try {
            // Is the user trying to update his own information?
            Account wantedAccount = service.get(id);
            if(wantedAccount.getEmail().equals(request().username())) {
                Form<Account> form = formFactory.form(Account.class).bindFromRequest();
                if(form.hasErrors()) {
                    return badRequest(form.errorsAsJson());
                }

                // User can't change the role himself, unless he's an admin
                Account updatedAccount = form.get();

                if(service.getUserRole(wantedAccount) != AccountService.USER_TYPE.ADMIN) {
                    updatedAccount.setRole(wantedAccount.getRole());
                }

                return super.update(id, updatedAccount);
            }

            // Is this user an admin and does he have permission to update another user
            return updateAsAdmin(id);
        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@update"));
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result updateAsAdmin(Long id) {
        return super.update(id);
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
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
            if(!(role >= 0 && role <= 1)) {
                return badRequest(Json.toJson("Unknown role!"));
            }

            // The account has been created
            return created(Json.toJson(service.create(form.get(), role)));
        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@create"));
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result demote(Long id) {
        try {
            return ok(Json.toJson(service.giveRole(id, AccountService.USER_TYPE.NORMAL.getValue())));
        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@demote"));
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"ADMIN"})
    public Result promote(Long id) {
        try {
            return ok(Json.toJson(service.giveRole(id, AccountService.USER_TYPE.ADMIN.getValue())));
        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@promote"));
        }
    }

}