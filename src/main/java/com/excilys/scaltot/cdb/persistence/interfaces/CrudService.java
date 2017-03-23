package com.excilys.scaltot.cdb.persistence.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
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
     * @param id : id
     * @param connection : connection
     * @return T
     * @throws SQLException : SQLException
     */
    Optional<T> find(long id, Connection connection) throws SQLException;

    /**
     * Retrieves all elements.
     * @return T
     * @throws SQLException : SQLException
     */
    List<T> findAll(Connection connection) throws SQLException;

    /**
     * Retrieves all elements paginated and filtered.
     * @param pagination : page
     * @return List<T>
     * @throws SQLException : SQLException
     */
    List<T> findByPageFilter(Pagination pagination, Connection connection) throws SQLException;

    /**
     * Delete one elements referenced by id in parameter.
     * @param id : id
     * @return boolean
     * @throws SQLException : SQLException
     */
    boolean delete(long id, Connection connection) throws SQLException;

    /**
     * Return number of elements.
     * @return long
     * @throws SQLException : SQLException
     */
    long getCountOfElements(Connection connection) throws SQLException;

}
