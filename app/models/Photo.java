package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * Created by Lilium on 17.1.2017.
 */
@Entity
public class Photo extends BaseModel<Photo> {
    private String imageUrl;
    private Double sort;
    private Restaurant restaurant;

    @Basic
    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id", nullable = false)
    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public Photo duplicate(Photo model)
    {
        Photo p = new Photo();
        p.setRestaurant(model.getRestaurant());
        p.setImageUrl(model.getImageUrl());
        p.setSort(model.getSort());

        return p;
    }

    @Override
    public void update(Photo data)
    {
        setRestaurant(data.getRestaurant());
        setImageUrl(data.getImageUrl());
        setSort(data.getSort());
    }

    public static class ImageUploaded {
        private String imageUrl;
        private Double sort;

        public ImageUploaded(String imageUrl, Double sort) {
            this.imageUrl = imageUrl;
            this.sort = sort;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Double getSort() {
            return sort;
        }

        public void setSort(Double sort) {
            this.sort = sort;
        }
    }

    public static class ImagesUploadResult {
        private String logoImageUrl;
        private String coverImageUrl;
        private List<ImageUploaded> photos;

        public ImagesUploadResult(String logoImageUrl, String coverImageUrl, List<ImageUploaded> photos) {
            this.logoImageUrl = logoImageUrl;
            this.coverImageUrl = coverImageUrl;
            this.photos = photos;
        }

        public String getLogoImageUrl() {
            return logoImageUrl;
        }

        public void setLogoImageUrl(String logoImageUrl) {
            this.logoImageUrl = logoImageUrl;
        }

        public String getCoverImageUrl() {
            return coverImageUrl;
        }

        public void setCoverImageUrl(String coverImageUrl) {
            this.coverImageUrl = coverImageUrl;
        }

        public List<ImageUploaded> getPhotos() {
            return photos;
        }

        public void setPhotos(List<ImageUploaded> photos) {
            this.photos = photos;
        }
    }
}
