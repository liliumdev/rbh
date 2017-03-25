package controllers.forms;

import play.data.validation.Constraints;

public class ForgotForm {
    @Constraints.Required
    @Constraints.Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}