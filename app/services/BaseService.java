package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.BaseModel;
import repositories.BaseRepository;
import repositories.exceptions.RepositoryException;
import services.exceptions.ServiceException;
import java.util.List;

/**
 * Created by Lilium on 20.1.2017.
 */
@Singleton
public abstract class BaseService<M extends BaseModel<M>, R extends BaseRepository<M> > {
    protected R repository;

    @Inject
    public void setRepository(R repository) {
        this.repository = repository;
    }

    public List<M> all()
    {
        return (List<M>)repository.findAll();
    }

    public M get(Long id) throws ServiceException {
        try {
            return repository.findById(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Service couldn't find model [" + id + "].", e);
        }
    }

    public M create(M model) throws ServiceException {
        try {
            repository.save(model);
            return model;
        } catch (RepositoryException e) {
            throw new ServiceException("Service couldn't create model.", e);
        }
    }

    public M update(Long id, M data) throws ServiceException {
        try {
            M model = get(id);
            if (model == null) {
                throw new ServiceException("Service couldn't find model [" + id + "].");
            }
            model.update(data);
            repository.update(model);
            return model;
        } catch (RepositoryException e) {
            throw new ServiceException("Service couldn't update model.", e);
        }
    }

    public void delete(Long id) throws ServiceException {
        try {
            M model = get(id);
            if (model == null) {
                throw new ServiceException("Service couldn't find model [" + id + "].");
            }
            repository.delete(id);
        } catch (RepositoryException e) {
            throw new ServiceException("Service couldn't find model [" + id + "].", e);
        }
    }
}