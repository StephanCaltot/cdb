package com.excilys.scaltot.cdb.dao.interfaces;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.utils.Pagination;

/**
 * @author Caltot Stéphan
 *
 *         22 févr. 2017
 */
public interface CrudService<T> {
    /**
     * Retrieves one element referenced by id parameter.
     * @param id :
     * @return T
     * @throws Exception :
     */
    Optional<T> find(long id);

    /**
     * Retrieves all elements.
     * @return T
     * @throws Exception :
     */
    List<T> findAll();

    /**
     * Retrieves all elements paginated and filtered.
     * @param pagination : page
     * @return List<T>
     * @throws Exception :
     */
    List<T> findByPageFilter(Pagination pagination);

    /**
     * Delete one elements referenced by id in parameter.
     * @param id : id
     * @return boolean
     */
    boolean delete(long id);

    /**
     * Return number of elements.
     * @return long
     */
    long getCountOfElements();

}
