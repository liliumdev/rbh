package models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Lilium on 17.1.2017.
 */
@Entity
public class Review extends BaseModel<Review> {
    private Integer rating;
    private String description;
    private Restaurant restaurant;
    private Account account;
    private Timestamp createdAt;

    @Basic
    @Column(name = "rating")
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Basic
    @Column(name = "created_at")
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    @Override
    public Review duplicate(Review model)
    {
        Review r = new Review();
        r.setRestaurant(model.getRestaurant());
        r.setAccount(model.getAccount());
        r.setCreatedAt(model.getCreatedAt());
        r.setDescription(model.getDescription());
        r.setRating(model.getRating());
        return r;
    }

    @Override
    public void update(Review data)
    {
        if(data.getRestaurant() != null) setRestaurant(data.getRestaurant());
        if(data.getAccount() != null) setAccount(data.getAccount());
        if(data.getCreatedAt() != null) setCreatedAt(data.getCreatedAt());
        if(data.getDescription() != null) setDescription(data.getDescription());
        if(data.getRating() != null) setRating(data.getRating());
    }
}
