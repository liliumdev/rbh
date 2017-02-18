package controllers.forms;

import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lilium on 28.1.2017.
 */
public class RegisterForm {
    @Constraints.Required
    private String firstName;

    @Constraints.Required
    private String lastName;

    @Constraints.Required
    @Constraints.Email
    private String email;

    @Constraints.Required
    @Constraints.MinLength(8)
    private String password;

    @Constraints.Required
    @Constraints.MinLength(8)
    private String password2;

    @Constraints.Required
    private Long cityId;

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();

        if(!getPassword().equals(getPassword2())) {
            errors.add(new ValidationError("password", "Passwords must match."));
            errors.add(new ValidationError("password2", "Passwords must match."));
        }

        return errors.isEmpty() ? null : errors;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}