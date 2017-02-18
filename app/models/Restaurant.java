package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vividsolutions.jts.geom.Point;

import javax.persistence.*;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lilium on 17.1.2017.
 */
@Entity
public class Restaurant extends BaseModel<Restaurant>  {
    private String name;
    private String description;
    private Double reviewRating;
    private Integer reviewCount;
    private Integer activeMenu;
    private String logoImageUrl;
    private String coverImageUrl;
    private Integer pricing;
    private List<DiningTable> diningTables;
    private List<Menu> menus;
    private List<Photo> photos;
    private Set<Category> categories = new HashSet<Category>();
    private Point latLong;
    private City city;

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
    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "review_rating")
    public Double getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(Double reviewRating) {
        this.reviewRating = reviewRating;
    }

    @Basic
    @Column(name = "review_count")
    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    @Basic
    @Column(name = "active_menu")
    public Integer getActiveMenu() {
        return activeMenu;
    }

    public void setActiveMenu(Integer activeMenu) {
        this.activeMenu = activeMenu;
    }

    @Basic
    @Column(name = "logo_image_url")
    public String getLogoImageUrl() {
        return logoImageUrl;
    }

    public void setLogoImageUrl(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }

    @Basic
    @Column(name = "cover_image_url")
    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    @Basic
    @Column(name = "pricing")
    public Integer getPricing() {
        return pricing;
    }

    public void setPricing(Integer pricing) {
        this.pricing = pricing;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant")
    public List<DiningTable> getDiningTables() {
        return diningTables;
    }

    public void setDiningTables(List<DiningTable> diningTables) {
        this.diningTables = diningTables;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant")
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant")
    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @JsonManagedReference
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="RestaurantCategory",
            joinColumns={@JoinColumn(name="restaurant_id")},
            inverseJoinColumns={@JoinColumn(name="category_id")})
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Column(name = "lat_long")
    public Point getLatLong() { return latLong; }

    public void setLatLong(Point latLong) { this.latLong = latLong; }

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public Restaurant duplicate(Restaurant model)
    {
        Restaurant r = new Restaurant();
        r.setActiveMenu(model.getActiveMenu());
        r.setCategories(model.getCategories());
        r.setCoverImageUrl(model.getCoverImageUrl());
        r.setDescription(model.getDescription());
        r.setDiningTables(model.getDiningTables());
        r.setLatLong(model.getLatLong());
        r.setLogoImageUrl(model.getLogoImageUrl());
        r.setMenus(model.getMenus());
        r.setName(model.getName());
        r.setPhotos(model.getPhotos());
        r.setPricing(model.getPricing());
        r.setReviewCount(model.getReviewCount());
        r.setReviewRating(model.getReviewRating());

        return r;
    }

    @Override
    public void update(Restaurant data)
    {
        if(data.getActiveMenu() != null) setActiveMenu(data.getActiveMenu());
        if(data.getCategories() != null) setCategories(data.getCategories());
        if(data.getCoverImageUrl() != null) setCoverImageUrl(data.getCoverImageUrl());
        if(data.getDescription() != null) setDescription(data.getDescription());
        if(data.getDiningTables() != null) setDiningTables(data.getDiningTables());
        if(data.getLogoImageUrl() != null) setLogoImageUrl(data.getLogoImageUrl());
        if(data.getMenus() != null) setMenus(data.getMenus());
        if(data.getName() != null) setName(data.getName());
        if(data.getPhotos() != null) setPhotos(data.getPhotos());
        if(data.getPricing() != null) setPricing(data.getPricing());
        if(data.getReviewCount() != null) setReviewCount(data.getReviewCount());
        if(data.getReviewRating() != null) setReviewRating(data.getReviewRating());
        if(data.getLatLong() != null) setLatLong(data.getLatLong());
    }

}
