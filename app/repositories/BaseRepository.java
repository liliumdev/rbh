package repositories;

import repositories.exceptions.RepositoryException;
import java.util.List;

/**
 * Created by Lilium on 14.1.2017.
 */
public interface BaseRepository<T> {
    /**
     * Returns the number of entities.
     *
     * @return
     */
    long count();

    /**
     * Deletes the entity with the given ID.
     *
     * @param id
     * @return
     */
    void delete(Long id) throws RepositoryException;

    /**
     * Deletes all entities within the given list.
     *
     * @param entities
     * @return
     */
    void delete(List<T> entities) throws RepositoryException;

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @return
     */
    void delete(T entity) throws RepositoryException;

    /**
     * Returns all instances.
     *
     * @return
     */
    List<T> findAll();

    /**
     * Returns the entity with the given ID.
     *
     * @param id
     * @return
     */
    T findById(Long id) throws RepositoryException;

    /**
     * Saves the given entity.
     *
     * @param entity
     * @return
     */
    T save(T entity) throws RepositoryException;

    /**
     * Saves all given entities.
     *
     * @param entities
     * @return
     */
    void save(List<T> entities) throws RepositoryException;

    /**
     * Updates the given entity.
     *
     * @param entity
     * @return
     */
    T update(T entity) throws RepositoryException;

    /**
     * Updates all given entities.
     *
     * @param entities
     * @return
     */
    void update(List<T> entities) throws RepositoryException;
}
