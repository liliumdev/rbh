package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import controllers.forms.ForgotForm;
import controllers.forms.LoginForm;
import controllers.forms.RegisterForm;
import controllers.forms.ResetForm;
import models.Account;
import models.ForgotPassword;
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
    public void setForgotService(ForgotPasswordService forgotService) {
        this.forgotService = forgotService;
    }

    @Inject
    public void setMailer(MailerClient mailer) {
        this.mailer = mailer;
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result login() {
        try {
            // Validate login form fields
            Form<LoginForm> form = formFactory.form(LoginForm.class).bindFromRequest();
            if(form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }

            // Does this account actually exist?
            LoginForm data = form.get();
            Account account = service.getByEmailAndPassword(data.getEmail(), data.getPassword());
            if(account == null) {
                return unauthorized(Json.toJson("Wrong email and/or password."));
            }

            // The account exists, grant the token
            return ok(Json.newObject().put("token", grantToken(account)));
        } catch(Exception e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@login"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result register() {
        try {
            // Validate the registration form
            Form<RegisterForm> form = formFactory.form(RegisterForm.class).bindFromRequest();

            // Is this email already used?
            if(service.getByEmail(form.get().getEmail()) != null) {
                form.errors().put("email", ValidationHelper.singleError("email", "This email already exists."));
            }

            if(form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }

            // The account has been created
            return created(Json.toJson(service.create(form.get())));
        } catch(Exception e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@register"));
        }
    }

    // This controller method is called while the user is typing in the email
    @Transactional
    public Result emailExists(String email) {
        try {
            // Does this email already exist
            if(service.getByEmail(email) != null) {
                return badRequest(Json.toJson("Email already exists"));
            }

            return ok(Json.toJson("Email doesn't exist."));
        } catch(Exception e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@emailExists"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result forgot() {
        try {
            // Validate the forgot form email field
            Form<ForgotForm> form = formFactory.form(ForgotForm.class).bindFromRequest();

            if(form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }

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
        } catch(ServiceException e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@forgot"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result reset(String token) {
        try {
            ForgotPassword forgotPassword = forgotService.getByToken(token);
            if(forgotPassword == null) {
                return badRequest("Wrong reset token.");
            }

            // Validate the new password
            Form<ResetForm> form = formFactory.form(ResetForm.class).bindFromRequest();
            if(form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            }

            // Set the new password
            Account relatedAccount = forgotPassword.getAccount();
            relatedAccount.setPassword(form.get().getPassword());
            service.update(relatedAccount.getId(), relatedAccount);

            // Delete the token which we just used
            forgotService.delete(forgotPassword.getId());

            return ok(Json.toJson("Password successfully changed. You can now login with your new password."));
        } catch(Exception e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@reset"));
        }
    }

    @Transactional
    @BodyParser.Of(BodyParser.Json.class)
    public Result isValidToken(String token) {
        try {
            ForgotPassword forgotPassword = forgotService.getByToken(token);
            if(forgotPassword == null) {
                return badRequest("Wrong reset token.");
            }

            return ok(Json.toJson("Token exists."));
        } catch(Exception e) {
            return internalServerError(Json.toJson("Internal server error in AccountController@isValidToken"));
        }
    }

    @Transactional
    @SecureAuth.Authenticated(roles = {"NORMAL", "ADMIN"})
    public Result currentAccount() {
        return ok(Json.toJson(service.getByEmail(request().username())));
    }

    public String grantToken(Account account) throws Exception {
        try {
            return JWT.create()
                    .withClaim("name", account.getFirstName())
                    .withClaim("email", account.getEmail())
                    .withClaim("role", service.getUserRole(account).name())
                    .sign(Algorithm.HMAC256(Play.application().configuration().getString("play.crypto.secret")));
        } catch(Exception exception) {
            throw new Exception("Couldn't create the JWT.");
        }
    }
}