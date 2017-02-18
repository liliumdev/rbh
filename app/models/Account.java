package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import play.data.validation.Constraints;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Lilium on 17.1.2017.
 */

@Entity
public class Account extends BaseModel<Account> {
    @Constraints.Email
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Integer role;
    private City city;
    private List<ForgotPassword> forgotTokens;
    private List<Reservation> reservations;

    @Basic
    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "role")
    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    public List<ForgotPassword> getForgotTokens() {
        return forgotTokens;
    }

    public void setForgotTokens(List<ForgotPassword> forgotTokens) {
        this.forgotTokens = forgotTokens;
    }

    @OneToMany(mappedBy = "account")
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public Account duplicate(Account model)
    {
        Account a = new Account();
        a.setFirstName(model.getFirstName());
        a.setLastName(model.getLastName());
        a.setCity(model.getCity());
        a.setEmail(model.getEmail());
        a.setForgotTokens(model.getForgotTokens());
        a.setReservations(model.getReservations());
        a.setRole(model.getRole());
        a.setPassword(model.getPassword());
        return a;
    }

    @Override
    public void update(Account data) {
        if(data.getFirstName() != null) setFirstName(data.getFirstName());
        if(data.getLastName() != null) setLastName(data.getLastName());
        if(data.getPassword() != null) setPassword(data.getPassword());
        if(data.getCity() != null) setCity(data.getCity());
        if(data.getEmail() != null) setEmail(data.getEmail());
        if(data.getForgotTokens() != null) setForgotTokens(data.getForgotTokens());
        if(data.getReservations() != null) setReservations(data.getReservations());
        if(data.getRole() != null) setRole(data.getRole());
    }
}
