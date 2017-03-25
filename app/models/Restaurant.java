package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vividsolutions.jts.geom.Point;
import javax.persistence.*;
import java.util.Date;
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
    private List<Review> reviews;
    private Set<Category> categories = new HashSet<Category>();
    private Point latLong;
    private City city;
    private Date workingTimeFrom;
    private Date workingTimeTo;
    private Date minimumCancelTime;

    // Helper for deserializing JSON array to Set
    private List<Category> categoriesList;
    private LatLongPoint latLongPoint;

    @Basic
    @Column(name = "name", nullable = false)
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
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DiningTable> getDiningTables() {
        return diningTables;
    }

    public void setDiningTables(List<DiningTable> diningTables) {
        this.diningTables = diningTables;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @JsonManagedReference
    @ManyToMany
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

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    @JsonManagedReference
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Basic
    @Column(name = "working_time_from", columnDefinition = "timestamp without time zone")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getWorkingTimeFrom() {
        return workingTimeFrom;
    }

    public void setWorkingTimeFrom(Date workingTimeFrom) {
        this.workingTimeFrom = workingTimeFrom;
    }

    @Basic
    @Column(name = "working_time_to", columnDefinition = "timestamp without time zone")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getWorkingTimeTo() {
        return workingTimeTo;
    }

    public void setWorkingTimeTo(Date workingTimeTo) {
        this.workingTimeTo = workingTimeTo;
    }

    @Basic
    @Column(name = "min_cancel_time", columnDefinition = "timestamp without time zone")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getMinimumCancelTime() {
        return minimumCancelTime;
    }

    public void setMinimumCancelTime(Date minimumCancelTime) {
        this.minimumCancelTime = minimumCancelTime;
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
        r.setWorkingTimeFrom(model.getWorkingTimeFrom());
        r.setWorkingTimeTo(model.getWorkingTimeTo());
        r.setMinimumCancelTime(model.getMinimumCancelTime());

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
        if(data.getWorkingTimeFrom() != null) setWorkingTimeFrom(data.getWorkingTimeFrom());
        if(data.getWorkingTimeTo() != null) setWorkingTimeTo(data.getWorkingTimeTo());
        if(data.getMinimumCancelTime() != null) setMinimumCancelTime(data.getMinimumCancelTime());
    }

    /* Helper stuff for deserializing from JSON representation */
    @Transient
    public List<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    @Transient
    public LatLongPoint getLatLongPoint() {
        return latLongPoint;
    }

    public void setLatLongPoint(LatLongPoint latLongPoint) {
        this.latLongPoint = latLongPoint;
    }


    public static class LatLongPoint {
        private String type;
        private List<Double> coordinates;


        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<Double> getCoordinates() {
            return coordinates;
        }

        public void setCoordinates(List<Double> coordinates) {
            this.coordinates = coordinates;
        }
    }

    public static class RestaurantMapPointDto {
        private String name;
        private Double lat;
        private Double lng;
        private Integer id;

        public RestaurantMapPointDto() { }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLng() {
            return lng;
        }

        public void setLng(Double lng) {
            this.lng = lng;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
