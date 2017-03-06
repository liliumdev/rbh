package repositories;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import play.db.jpa.JPA;
import repositories.exceptions.RepositoryException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Lilium on 14.1.2017.
 */
public class BaseRepositoryImpl<T> implements BaseRepository<T> {
    public long count() {
        Criteria criteria = getSession().createCriteria(getParameterType());
        criteria.setProjection(Projections.rowCount());
        return (Long)criteria.uniqueResult();
    }

    public void delete(Long id) throws RepositoryException {
        try {
            getSession().delete(findById(id));
        } catch(Exception e) {
            throw new RepositoryException("Error while deleting entity [" + id + "] from repository.");
        }
    }

    public void delete(List<T> entities) throws RepositoryException {
        try {
            for(T entity : entities)
                delete(entity);
        } catch(Exception e) {
            throw new RepositoryException("Error while deleting a List of entities from repository.");
        }
    }

    public void delete(T entity) throws RepositoryException {
        try {
            getSession().delete(entity);
        } catch(Exception e) {
            throw new RepositoryException("Error while deleting an entity from repository.");
        }
    }


    public List<T> findAll() {
        return getBaseCriteria().list();
    }

    public T findById(Long id) throws RepositoryException {
        try {
            return JPA.em().find(getParameterType(), id);
        } catch(Exception e) {
            throw new RepositoryException("Error while searching for an entity with ID [" + id + "] in repository.");
        }
    }

    public T save(T entity) throws RepositoryException {
        try {
            JPA.em().persist(entity);
            JPA.em().flush();
            return entity;
        } catch(Exception e) {
            throw new RepositoryException("Error while saving an entity to the repository.");
        }
    }

    public void save(List<T> entities) throws RepositoryException {
        try {
            for(T entity : entities) {
                save(entity);
            }
        } catch(Exception e) {
            throw new RepositoryException("Error while saving a List of entities to the repository.");
        }
    }

    public T update(T entity) throws RepositoryException {
        try {
            JPA.em().merge(entity);
            JPA.em().flush();
            return entity;
        } catch(Exception e) {
            throw new RepositoryException("Error while updating an entity in the repository.");
        }
    }

    public void update(List<T> entities) throws RepositoryException {
        try {
            for(T entity : entities) {
                update(entity);
            }
        } catch(Exception e) {
            throw new RepositoryException("Error while updating updating a List of entities in the repository.");
        }
    }

    public Session getSession()
    {
        return JPA.em().unwrap(Session.class);
    }

    protected Criteria getBaseCriteria() {
        return getSession().createCriteria(getParameterType());
    }

    private Class<T> getParameterType() {
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
