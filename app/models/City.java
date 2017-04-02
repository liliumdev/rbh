package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.vividsolutions.jts.geom.Polygon;
import org.hibernate.annotations.Type;
import play.data.validation.Constraints;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class City extends BaseModel<City> {
    @Constraints.Required
    private String name;
    private List<Account> accounts;
    private Country country;
    private List<Restaurant> restaurants;
    private Polygon boundary;

    @Column(name = "boundary", columnDefinition = "geometry(Polygon, 4326)")
    public Polygon getBoundary() { return boundary; }

    public void setBoundary(Polygon boundary) { this.boundary = boundary; }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId=true)
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public City duplicate(City model) {
        City c = new City();
        c.setName(model.getName());
        c.setAccounts(model.getAccounts());
        c.setCountry(model.getCountry());
        c.setRestaurants(model.getRestaurants());
        c.setBoundary(model.getBoundary());

        return c;
    }

    @Override
    public void update(City data) {
        if(data.getName() != null) setName(data.getName());
        if(data.getAccounts() != null) setAccounts(data.getAccounts());
        if(data.getCountry() != null) setCountry(data.getCountry());
        if(data.getRestaurants() != null) setRestaurants(data.getRestaurants());
        if(data.getBoundary() != null) setBoundary(data.getBoundary());
    }
}
