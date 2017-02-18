package services;

import models.Restaurant;
import models.Review;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import repositories.RestaurantRepository;
import services.exceptions.ServiceException;

import javax.inject.Inject;
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

    public List<Restaurant> random(Integer limit) {
        Criteria criteria = repository.getSession().createCriteria(Restaurant.class);
        criteria.add(Restrictions.sqlRestriction("1=1 ORDER BY RANDOM()"));
        criteria.setMaxResults(limit);
        return criteria.list();
    }

    public Double rate(Long restaurantId, Integer rating, String description, String email) throws ServiceException {
        try {
            // Did this user already review the retaurant?
            if(reviewService.didReview(restaurantId, email))
                return null;

            Restaurant restaurant = this.get(restaurantId);

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
