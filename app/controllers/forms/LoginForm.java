package controllers.forms;

import play.data.validation.Constraints;

public class LoginForm {
    @Constraints.Required
    @Constraints.Email
    private String email;

    @Constraints.Required
    @Constraints.MinLength(8)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}