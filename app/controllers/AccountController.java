package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import controllers.forms.ForgotForm;
import controllers.forms.LoginForm;
import controllers.forms.RegisterForm;
import controllers.forms.ResetForm;
import controllers.forms.admin.AccountCreationForm;
import models.Account;
import models.ForgotPassword;
import org.mindrot.jbcrypt.BCrypt;
import play.Play;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.AccountService;
import services.ForgotPasswordService;
import services.exceptions.ServiceException;

import javax.inject.Inject;

public class AccountController extends BaseController<Account, AccountService> {
    protected ForgotPasswordService forgotService;
    protected MailerClient mailer;

    @Inject
    public void setForgotService(ForgotPasswordService forgotService)
    {
        this.forgotService = forgotService;
    }

    @Inject
    public void setMailer(MailerClient mailer) { this.mailer = mailer; }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result login()
    {
        try {
            // Validate login form fields
            Form<LoginForm> form = formFactory.form(LoginForm.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            // Does this account actually exist?
            LoginForm data = form.get();
            Account account = service.getByEmailAndPassword(data.getEmail(), data.getPassword());
            if(account == null)
                return unauthorized(Json.toJson("Wrong email and/or password."));

            // Grant the token
            return ok(Json.newObject().put("token", grantToken(account)));
        } catch (Exception e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@login"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result register() {
        try {
            // Validate the registration form
            Form<RegisterForm> form = formFactory.form(RegisterForm.class).bindFromRequest();

            // Is this email already present in the account table?
            if(service.getByEmail(form.get().getEmail()) != null) {
                form.errors().put("email", ValidationHelper.singleError("email", "This email already exists."));
            }

            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            // The account has been created
            return created(Json.toJson(service.create(form.get())));
        } catch (Exception e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@register"));
        }
    }

    @Transactional
    public Result emailExists(String email) {
        try {
            // Is this email already present in the account table?
            if(service.getByEmail(email) != null) {
                return badRequest(Json.toJson("Email already exists"));
            }

            return ok(Json.toJson("Email doesn't exist."));
        } catch (Exception e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@emailExists"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result forgot()
    {
        try {
            // Validate the forgot form email field
            Form<ForgotForm> form = formFactory.form(ForgotForm.class).bindFromRequest();

            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            // Does this email actually exist?
            ForgotForm forgotForm = form.get();
            String email = forgotForm.getEmail();
            if(service.getByEmail(email) == null) {
                form.errors().put("email", ValidationHelper.singleError("email", "This email doesn't exist."));
                return badRequest(form.errorsAsJson());
            }

            ForgotPassword forgotPasswordToken = forgotService.create(email);
            String token = forgotPasswordToken.getToken();
            String host = configuration.getString("host");
            Email resetEmail = new Email()
                    .setSubject("Restaurants - Your password reset")
                    .setFrom("Restaurants <admin@restaurants.ba>")
                    .addTo(email)
                    .setBodyText("Please manually copy the address http://" + host + "/reset-password/" + token + "in your browser " +
                                 "in order to reset your password.")
                    .setBodyHtml("<html><body><h1>Restaurants</h1><p>Please click <a href=\"http://" + host + "/reset-password/" +
                                 token + "\"><b>here</b></a> to reset your password.<br><br>If, in some case, the link " +
                                 "doesn't work, please manually copy the address http://" + host + "/reset-password/" + token +
                                 " in your browser.</p></body></html>");
            mailer.send(resetEmail);

            return ok("An email containing further instructions has been sent to your email.");
        } catch (ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@forgot"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result reset(String token)
    {
        try {
            ForgotPassword forgotPassword = forgotService.getByToken(token);
            if(forgotPassword == null)
                return badRequest("Wrong reset token.");

            // Validate the new password
            Form<ResetForm> form = formFactory.form(ResetForm.class).bindFromRequest();
            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

            Account relatedAccount = forgotPassword.getAccount();
            relatedAccount.setPassword(BCrypt.hashpw(form.get().getPassword(), BCrypt.gensalt()));
            service.update(relatedAccount.getId(), relatedAccount);
            forgotService.delete(forgotPassword.getId());

            return ok(Json.toJson("Password successfully changed. You can now login with your new password."));
        } catch (Exception e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@reset"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result isValidToken(String token)
    {
        try {
            ForgotPassword forgotPassword = forgotService.getByToken(token);
            if(forgotPassword == null)
                return badRequest("Wrong reset token.");

            return ok(Json.toJson("Token exists."));
        } catch (Exception e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@isValidToken"));
        }
    }

    public String grantToken(Account account) throws Exception
    {
        try {
            return JWT.create()
                    .withClaim("name", account.getFirstName())
                    .withClaim("email", account.getEmail())
                    .withClaim("role", service.getUserRole(account).name())
                    .sign(Algorithm.HMAC256(Play.application().configuration().getString("play.crypto.secret")));
        } catch (Exception exception) {
            throw new Exception("Couldn't create the JWT.");
        }
    }

    // Admin pages

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result all() {
        return super.all();
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result get(Long id) {
        return super.get(id);
    }

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

            if(form.hasErrors())
                return badRequest(form.errorsAsJson());

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
    public Result delete(Long id)
    {
        return super.delete(id);
    }

    @Transactional
    @SecureAuth.Authenticated(roles={"ADMIN"})
    public Result update(Long id) {
        return super.update(id);
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