package controllers.forms;

import play.data.validation.Constraints;

public class ResetForm {
    @Constraints.Required
    @Constraints.MinLength(8)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}