package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Lilium on 17.1.2017.
 */
@Entity
public class DiningTable extends BaseModel<DiningTable> {
    private Integer amount;
    private Restaurant restaurant;
    private List<Reservation> reservations;
    private Integer persons;

    @Basic
    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "diningTable", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Basic
    @Column(name = "persons")
    public Integer getPersons() {
        return persons;
    }

    public void setPersons(Integer persons) {
        this.persons = persons;
    }

    @Override
    public DiningTable duplicate(DiningTable model)
    {
        DiningTable d = new DiningTable();
        d.setReservations(model.getReservations());
        d.setAmount(model.getAmount());
        d.setPersons(model.getPersons());
        d.setRestaurant(model.getRestaurant());

        return d;
    }

    @Override
    public void update(DiningTable data)
    {
        setReservations(data.getReservations());
        setAmount(data.getAmount());
        setPersons(data.getPersons());
        setRestaurant(data.getRestaurant());
    }
}
