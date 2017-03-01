package services;

import models.DiningTable;
import models.Reservation;
import models.Restaurant;
import models.Review;
import models.filters.RestaurantFilterBuilder;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import repositories.RestaurantRepository;
import services.exceptions.ServiceException;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Lilium on 14.1.2017.
 */

public class RestaurantService extends BaseService<Restaurant, RestaurantRepository> {
    private ReviewService reviewService;
    private AccountService accountService;

    @Inject
    public void setReviewService(ReviewService reviewService)
    {
        this.reviewService = reviewService;
    }

    @Inject
    public void setAccountService(AccountService accountService)
    {
        this.accountService = accountService;
    }

    public List<Restaurant> random(Integer limit) throws ServiceException {
        try {
            Criteria criteria = repository.getSession().createCriteria(Restaurant.class);
            criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY RANDOM()"));
            criteria.setMaxResults(limit);
            return criteria.list();
        } catch (HibernateException e) {
            throw new ServiceException("RestaurantService couldn't find random restaurants.", e);
        }
    }

    public List<Restaurant> filter(RestaurantFilterBuilder rfb) throws ServiceException {
        try {
            Criteria criteria = repository.getSession().createCriteria(Restaurant.class);
            criteria = rfb.addConditions(criteria);

            return criteria.list();
        } catch (HibernateException e) {
            throw new ServiceException("RestaurantService couldn't filter restaurants.", e);
        }
    }

    public Long getNumberOfReservationsToday(Long restaurantId) throws ServiceException {
        try {
            Restaurant restaurant = this.get(restaurantId);
            Date today = new Date(); today.setHours(0); today.setMinutes(0);

            Criteria criteria = repository.getSession().createCriteria(Reservation.class, "reservation");
            criteria.createAlias("reservation.diningTable", "diningTable");
            criteria.createAlias("diningTable.restaurant", "restaurant");
            criteria.add(Restrictions.eqProperty("reservation.diningTable.id", "diningTable.id"));
            criteria.add(Restrictions.eqProperty("diningTable.restaurant.id", "restaurant.id"));
            criteria.add(Restrictions.ge("reservation.createdAt", today));
            criteria.setProjection(Projections.rowCount());

            return (Long)criteria.uniqueResult();
        } catch (ServiceException e) {
            throw new ServiceException("RestaurantService couldn't rate a restaurant.", e);
        }
    }

    public Double rate(Long restaurantId, Integer rating, String description, String email) throws ServiceException {
        try {
            // Did this user already review the retaurant?
            if(reviewService.didReview(restaurantId, email))
                return null;

            Restaurant restaurant = this.get(restaurantId);
            if(restaurant == null)
                return null;

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

            Double newRating = (Double)reviewService.repository.getSession().createCriteria(Review.class)
                    .setProjection(Projections.avg("rating"))
                    .add(Restrictions.eq("restaurant.id", restaurantId))
                    .uniqueResult();

            restaurant.setReviewRating(newRating);
            this.update(restaurantId, restaurant);

            return newRating;
        } catch (ServiceException e) {
            throw new ServiceException("RestaurantService couldn't rate a restaurant.", e);
        }
    }
}
