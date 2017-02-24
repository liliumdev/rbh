package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.TemporalType;

/**
 * Created by Lilium on 14.1.2017.
 */
@Entity
public class Reservation extends BaseModel<Reservation> {
    private Date forTime;
    private Date createdAt;
    private Integer persons;
    private String request;
    private DiningTable diningTable;
    private Account account;

    @Basic
    @Column(name = "for_time", columnDefinition = "timestamp without time zone")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getForTime() {
        return forTime;
    }

    public void setForTime(Date forTime) {
        this.forTime = forTime;
    }

    @Basic
    @Column(name = "created_at", columnDefinition = "timestamp without time zone")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    public DiningTable getDiningTable() {
        return diningTable;
    }

    public void setDiningTable(DiningTable diningTable) {
        this.diningTable = diningTable;
    }

    @JsonIgnore
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

    public static class ReservationSuggestionDto {
        private Integer persons;
        private Integer freeTables;
        private Integer tableId;
        private Timestamp suggestedTime;

        public ReservationSuggestionDto() { }

        public Integer getPersons() {
            return persons;
        }

        public void setPersons(Integer persons) {
            this.persons = persons;
        }

        public Integer getFreeTables() {
            return freeTables;
        }

        public void setFreeTables(Integer freeTables) {
            this.freeTables = freeTables;
        }

        public Integer getTableId() {
            return tableId;
        }

        public void setTableId(Integer tableId) {
            this.tableId = tableId;
        }

        public Timestamp getSuggestedTime() {
            return suggestedTime;
        }

        public void setSuggestedTime(Timestamp suggestedTime) {
            this.suggestedTime = suggestedTime;
        }
    }

    public static class MyReservationDto {
        private Integer id;
        private Timestamp forTime;
        private String name;
        private Integer persons;

        public MyReservationDto() { }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Timestamp getForTime() {
            return forTime;
        }

        public void setForTime(Timestamp forTime) {
            this.forTime = forTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPersons() {
            return persons;
        }

        public void setPersons(Integer persons) {
            this.persons = persons;
        }
    }
}
