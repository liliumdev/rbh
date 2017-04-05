package services;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import models.Photo;
import models.Reservation;
import models.Restaurant;
import models.Review;
import models.filters.RestaurantFilterBuilder;
import models.helpers.OrderByRawSql;
import modules.PlayS3;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.spatial.GeolatteGeometryType;
import org.hibernate.spatial.criterion.SpatialRestrictions;
import org.hibernate.spatial.dialect.postgis.PGGeometryTypeDescriptor;
import org.hibernate.transform.Transformers;
import repositories.RestaurantRepository;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import javax.xml.ws.Service;
import java.util.Date;
import java.util.List;

public class RestaurantService extends BaseService<Restaurant, RestaurantRepository> {
    private ReviewService reviewService;
    private AccountService accountService;
    private PhotoService photoService;

    @Inject
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Inject
    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    public void delete(Long id) throws ServiceException {
        // First delete images from AWS S3
        Restaurant r = get(id);
        deletePhotoFromAws(id, "gallery/logos", r.getLogoImageUrl(), true);
        deletePhotoFromAws(id, "gallery/covers", r.getCoverImageUrl(), false);
        for(Photo p : r.getPhotos()) {
            deletePhotoFromAws(id, "gallery", p.getImageUrl(), true);
        }

        super.delete(id);
    }

    public void deletePhotoFromAws(Long restaurantId, String directory, String filename, Boolean deleteThumb) throws ServiceException {
        try {
            AmazonS3 client = PlayS3.getAmazonS3();
            client.deleteObject(new DeleteObjectRequest(PlayS3.getBucketName(), directory + "/" + filename));
            if(deleteThumb) {
                client.deleteObject(new DeleteObjectRequest(PlayS3.getBucketName(), directory + "/thumbs/" + filename));
                Photo p = photoService.getByFilenameAndRestaurantId(filename, restaurantId);
                if(p != null) {
                    photoService.delete(p.getId());
                }
            }
        } catch (SdkClientException e) {
            throw new ServiceException("Could not delete an image from AWS!");
        } catch (ServiceException e) {
            throw new ServiceException("Could not delete an image from AWS!");
        }
    }

    public List<Restaurant> random(Integer limit) throws ServiceException {
        try {
            Criteria criteria = repository.getSession().createCriteria(Restaurant.class);
            criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY RANDOM()"));
            criteria.setMaxResults(limit);
            return criteria.list();
        } catch(HibernateException e) {
            throw new ServiceException("RestaurantService couldn't find random restaurants.", e);
        }
    }

    public List<Restaurant> bestRated(Integer limit) throws ServiceException {
        try {
            Criteria criteria = repository.getSession().createCriteria(Restaurant.class);
            criteria.addOrder(Order.desc("reviewRating"));
            criteria.setMaxResults(limit);
            return criteria.list();
        } catch(HibernateException e) {
            throw new ServiceException("RestaurantService couldn't find random restaurants.", e);
        }
    }

    public List<Restaurant.RestaurantMapPointDto> nearest(Integer limit, Double lat, Double lng) throws ServiceException {
        try {
            final GeometryFactory gf = new GeometryFactory();
            Point point = gf.createPoint(new Coordinate(lat, lng, 0.0));

            Criteria criteria = repository.getSession().createCriteria(Restaurant.class);
            // Distance is given in degrees. Calculating with matlab and km2deg, a spherical distance of
            // 5km is around 0.045
            criteria.add(SpatialRestrictions.distanceWithin("latLong", point, 0.05));
            criteria.addOrder(OrderByRawSql.sql("ST_Distance(lat_long, ST_Geomfromtext('POINT(" + lat + " " + lng + ")'))"));
            criteria.setMaxResults(limit);
            return criteria.list();


        } catch(Exception e) {
            throw new ServiceException("RestaurantService couldn't find nearest restaurants.", e);
        }
    }

    public List<Restaurant> filter(RestaurantFilterBuilder rfb) throws ServiceException {
        try {
            Criteria criteria = repository.getSession().createCriteria(Restaurant.class);
            criteria = rfb.addConditions(criteria);

            return criteria.list();
        } catch(HibernateException e) {
            throw new ServiceException("RestaurantService couldn't filter restaurants.", e);
        }
    }

    public Long getNumberOfReservationsToday(Long restaurantId) throws ServiceException {
        try {
            Date today = new Date();
            today.setHours(0);
            today.setMinutes(0);

            Criteria criteria = repository.getSession().createCriteria(Reservation.class, "reservation");
            criteria.createAlias("reservation.diningTable", "diningTable");
            criteria.createAlias("diningTable.restaurant", "restaurant");
            criteria.add(Restrictions.eqProperty("reservation.diningTable.id", "diningTable.id"));
            criteria.add(Restrictions.eq("diningTable.restaurant.id", restaurantId));
            criteria.add(Restrictions.ge("reservation.createdAt", today));
            criteria.setProjection(Projections.rowCount());

            return (Long) criteria.uniqueResult();
        } catch(HibernateException e) {
            throw new ServiceException("RestaurantService couldn't rate a restaurant.", e);
        }
    }

    public List<Reservation> getAllReservations(Long restaurantId, String time) throws ServiceException {
        try {
            if(!time.equals("past") && !time.equals("future") && !time.equals("all")) {
                time = "past";
            }

            Date now = new Date();

            Criteria criteria = repository.getSession().createCriteria(Reservation.class, "reservation");
            criteria.createAlias("reservation.diningTable", "diningTable");
            criteria.createAlias("diningTable.restaurant", "restaurant");
            criteria.add(Restrictions.eqProperty("reservation.diningTable.id", "diningTable.id"));
            criteria.add(Restrictions.eq("diningTable.restaurant.id", restaurantId));
            if(!time.equals("all")) {
                criteria.add(time.equals("past") ? Restrictions.le("reservation.forTime", now) : Restrictions.ge("reservation.forTime", now));
            }
            criteria.addOrder(Order.asc("reservation.createdAt"));

            return criteria.list();
        } catch(HibernateException e) {
            throw new ServiceException("RestaurantService couldn't rate a restaurant.", e);
        }
    }

    public Review rate(Long restaurantId, Integer rating, String description, String email) throws ServiceException {
        try {
            // Did this user already review the retaurant?
            if(reviewService.didReview(restaurantId, email)) {
                return null;
            }

            Restaurant restaurant = this.get(restaurantId);
            if(restaurant == null) {
                return null;
            }

            // Create this review
            Review review = new Review();
            review.setRestaurant(restaurant);
            review.setCreatedAt(new java.sql.Timestamp(new Date().getTime())); // Current time
            review.setAccount(accountService.getByEmail(email));
            review.setDescription(description);
            review.setRating(rating);
            reviewService.create(review);

            // Recalculate the restaurant rating
            restaurant.setReviewCount(restaurant.getReviewCount() + 1); // First increase the count

            restaurant.setReviewRating(this.getRating(restaurantId));
            this.update(restaurantId, restaurant);

            return review;
        } catch(ServiceException e) {
            throw new ServiceException("RestaurantService couldn't rate a restaurant.", e);
        }
    }

    public Double getRating(Long restaurantId) {
        Double rating = (Double) reviewService.repository.getSession().createCriteria(Review.class)
                .setProjection(Projections.avg("rating"))
                .add(Restrictions.eq("restaurant.id", restaurantId))
                .uniqueResult();

        return rating;
    }
}
