package controllers.forms;

import play.data.validation.Constraints;

/**
 * Created by Lilium on 28.1.2017.
 */
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