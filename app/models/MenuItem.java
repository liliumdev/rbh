package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class MenuItem extends BaseModel<MenuItem>  {
    private String name;
    private String description;
    private BigDecimal price;
    private Double sort;
    private Menu menu;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "sort")
    public Double getSort() {
        return sort;
    }

    public void setSort(Double sort) {
        this.sort = sort;
    }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id", nullable = false)
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Override
    public MenuItem duplicate(MenuItem model)
    {
        MenuItem m = new MenuItem();
        m.setDescription(model.getDescription());
        m.setMenu(model.getMenu());
        m.setName(model.getName());
        m.setPrice(model.getPrice());
        m.setSort(model.getSort());

        return m;
    }

    @Override
    public void update(MenuItem data)
    {
        setDescription(data.getDescription());
        setMenu(data.getMenu());
        setName(data.getName());
        setPrice(data.getPrice());
        setSort(data.getSort());
    }
}
