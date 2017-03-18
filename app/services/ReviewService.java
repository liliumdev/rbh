package services;

import models.Account;
import models.Review;
import org.hibernate.criterion.Restrictions;
import repositories.ReviewRepository;

import javax.inject.Inject;

public class ReviewService extends BaseService<Review, ReviewRepository> {
    protected AccountService accountService;

    @Inject
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }


    public Boolean didReview(Long restaurantId, String email) {
        // Did this user review this restaurant already
        if(email == null) {
            return false;
        }

        Account account = accountService.getByEmail(email);
        if(account == null) {
            return false;
        }

        Review oldReview = (Review) (repository.getSession().createCriteria(Review.class)
                .add(Restrictions.eq("account", account))
                .add(Restrictions.eq("restaurant.id", restaurantId))
                .uniqueResult());

        return oldReview != null;
    }
}
