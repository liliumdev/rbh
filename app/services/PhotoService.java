package services;

import models.Photo;
import org.hibernate.criterion.Restrictions;
import repositories.PhotoRepository;

public class PhotoService extends BaseService<Photo, PhotoRepository> {
    public Photo getByFilenameAndRestaurantId(String name, Long restaurantId) {
        return (Photo) (repository.getSession().createCriteria(Photo.class)
                .add(Restrictions.eq("imageUrl", name))
                .add(Restrictions.eq("restaurant.id", restaurantId))
                .uniqueResult());
    }
}
