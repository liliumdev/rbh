package models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import play.data.validation.Constraints;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Country extends BaseModel<Country> {
    @Constraints.Required
    private String name;
    private List<City> cities;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "country")
    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public Country duplicate(Country model)
    {
        Country c = new Country();
        c.setName(model.getName());
        c.setCities(model.getCities());

        return c;
    }

    @Override
    public void update(Country data) {
        if(data.getName() != null) setName(data.getName());
        if(data.getCities() != null) setCities(data.getCities());
    }
}
