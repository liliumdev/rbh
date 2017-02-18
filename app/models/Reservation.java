package models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

/**
 * Created by Lilium on 14.1.2017.
 */
@Entity
public class Reservation extends BaseModel<Reservation> {
    private Timestamp forTime;
    private Timestamp createdAt;
    private Integer persons;
    private String request;
    private DiningTable diningTable;
    private Account account;

    @Basic
    @Column(name = "for_time")
    public Timestamp getForTime() {
        return forTime;
    }

    public void setForTime(Timestamp forTime) {
        this.forTime = forTime;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "persons")
    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    @Basic
    @Column(name = "request")
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }


    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    public DiningTable getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(DiningTable diningTable) {
        this.diningTable = diningTable;
    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public Reservation duplicate(Reservation model)
    {
        Reservation r = new Reservation();
        r.setAccount(model.getAccount());
        r.setCreatedAt(model.getCreatedAt());
        r.setDiningTable(model.getDiningTable());
        r.setForTime(model.getForTime());
        r.setPersons(model.getPersons());
        r.setRequest(model.getRequest());

        return r;
    }

    @Override
    public void update(Reservation data)
    {
        setAccount(data.getAccount());
        setCreatedAt(data.getCreatedAt());
        setDiningTable(data.getDiningTable());
        setForTime(data.getForTime());
        setPersons(data.getPersons());
        setRequest(data.getRequest());
    }
}
