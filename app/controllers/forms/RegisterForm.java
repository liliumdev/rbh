package controllers.forms;

import controllers.forms.admin.AccountCreationForm;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lilium on 28.1.2017.
 */
public class RegisterForm extends AccountCreationForm {
    @Constraints.Required
    @Constraints.MinLength(8)
    private String password2;

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();

        if(!getPassword().equals(getPassword2())) {
            errors.add(new ValidationError("password", "Passwords must match."));
            errors.add(new ValidationError("password2", "Passwords must match."));
        }

        return errors.isEmpty() ? null : errors;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}