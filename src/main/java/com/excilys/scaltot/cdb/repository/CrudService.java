package com.excilys.scaltot.cdb.repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Caltot Stéphan
 *
 *         22 févr. 2017
 */
public interface CrudService<T> {
    /**
     *
     * @param id :
     * @return T
     * @throws Exception :
     */
    Optional<T> find(long id);

    /**
     *
     * @return T
     * @throws Exception :
     */
    List<T> findAll();

    /**
     *
     * @param offset :
     * @param numberForEachpPage :
     * @return List<T>
     * @throws Exception :
     */
    List<T> findByPage(long offset, long numberForEachpPage);

}
