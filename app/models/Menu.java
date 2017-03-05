package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Lilium on 17.1.2017.
 */
@Entity
public class Menu extends BaseModel<Menu>  {
    private String name;
    private Restaurant restaurant;
    private List<MenuItem> menuItems;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public Menu duplicate(Menu model)
    {
        Menu m = new Menu();
        m.setRestaurant(model.getRestaurant());
        m.setMenuItems(model.getMenuItems());
        m.setName(model.getName());

        return m;
    }

    @Override
    public void update(Menu data)
    {
        setRestaurant(data.getRestaurant());
        setMenuItems(data.getMenuItems());
        setName(data.getName());
    }
}
