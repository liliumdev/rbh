package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ForgotPassword extends BaseModel<ForgotPassword> {
    private String token;
    private Account account;

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public ForgotPassword duplicate(ForgotPassword model)
    {
        ForgotPassword f = new ForgotPassword();
        f.setAccount(model.getAccount());
        f.setToken(model.getToken());

        return f;
    }

    @Override
    public void update(ForgotPassword data) {
        if(data.getAccount() != null) setAccount(data.getAccount());
        if(data.getToken() != null) setToken(data.getToken());
    }
}
