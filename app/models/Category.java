package models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lilium on 17.1.2017.
 */
@Entity
public class Category extends BaseModel<Category>  {
    private String name;
    private Set<Restaurant> restaurants = new HashSet<Restaurant>();

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    @ManyToMany(mappedBy="categories")
    public Set<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public void setRestaurants(Set<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    @Override
    public Category duplicate(Category model)
    {
        Category c = new Category();
        c.setName(model.getName());
        c.setRestaurants(model.getRestaurants());

        return c;
    }

    @Override
    public void update(Category data)
    {
        if(data.getName() != null) setName(data.getName());
        if(data.getRestaurants() != null) setRestaurants(data.getRestaurants());
    }
}
