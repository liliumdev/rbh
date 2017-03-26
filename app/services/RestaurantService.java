package services;

import models.Reservation;
import models.Restaurant;
import models.Review;
import models.filters.RestaurantFilterBuilder;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.spatial.GeolatteGeometryType;
import org.hibernate.spatial.dialect.postgis.PGGeometryTypeDescriptor;
import org.hibernate.transform.Transformers;
import repositories.RestaurantRepository;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class RestaurantService extends BaseService<Restaurant, RestaurantRepository> {
    private ReviewService reviewService;
    private AccountService accountService;

    @Inject
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
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

    public List<Restaurant.RestaurantMapPointDto> nearest(Integer limit) throws ServiceException {
        try {
            String sql = new StringBuilder()
                    .append("SELECT id, name, ST_X(lat_long) AS lat, ST_Y(lat_long) AS lng ")
                    .append("FROM restaurant ")
                    .append("WHERE ST_DWithin(restaurant.lat_long, ST_Geomfromtext('POINT(43.85 18.38)'), 3000) ")
                    .append("ORDER BY ST_Distance(restaurant.lat_long, ST_Geomfromtext('POINT(43.85 18.38)')) ")
                    .append("LIMIT " + limit.toString())
                    .toString();

            NativeQuery query = repository.getSession().createNativeQuery(sql);

            query.setResultTransformer(Transformers.aliasToBean(Restaurant.RestaurantMapPointDto.class));


            return (List<Restaurant.RestaurantMapPointDto>)query.getResultList();
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
            if(!time.equals("past") && !time.equals("future")) {
                time = "past";
            }

            Date now = new Date();

            Criteria criteria = repository.getSession().createCriteria(Reservation.class, "reservation");
            criteria.createAlias("reservation.diningTable", "diningTable");
            criteria.createAlias("diningTable.restaurant", "restaurant");
            criteria.add(Restrictions.eqProperty("reservation.diningTable.id", "diningTable.id"));
            criteria.add(Restrictions.eq("diningTable.restaurant.id", restaurantId));
            criteria.add(time.equals("past") ? Restrictions.le("reservation.forTime", now) : Restrictions.ge("reservation.forTime", now));
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
