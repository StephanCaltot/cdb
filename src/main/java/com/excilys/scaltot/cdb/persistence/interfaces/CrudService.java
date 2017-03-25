package com.excilys.scaltot.cdb.persistence.interfaces;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.utils.Pagination;

/**
 * @author Caltot Stéphan
 *
 *         22 févr. 2017
 */
public interface CrudService<T> {

    /**
     * Retrieves one element referenced by id parameter.
     * @param id : id
     * @return T
     * @throws PersistenceException : PersistenceException
     */
    Optional<T> find(long id) throws PersistenceException;

    /**
     * Retrieves all elements.
     * @return T
     * @throws PersistenceException : PersistenceException
     */
    List<T> findAll() throws PersistenceException;

    /**
     * Retrieves all elements paginated and filtered.
     * @param pagination : page
     * @return List<T>
     * @throws PersistenceException : PersistenceException
     */
    List<T> findByPageFilter(Pagination pagination) throws PersistenceException;

    /**
     * Delete one elements referenced by id in parameter.
     * @param id : id
     * @return boolean
     * @throws PersistenceException : PersistenceException
     */
    boolean delete(long id) throws PersistenceException;

    /**
     * Return number of elements.
     * @return long
     * @throws PersistenceException : PersistenceException
     */
    long getCountOfElements() throws PersistenceException;

}
