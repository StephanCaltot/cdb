package com.excilys.scaltot.cdb.services.interfaces;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.pagination.Pagination;

/**
 * @author Caltot Stéphan
 *
 * 22 mars 2017
 */
public interface CrudComputerService {

    /**
     * Create a computer gave in parameter.
     * @param computer : computer to create
     */
    void create(Optional<Computer> computer);

    /**
     * Find and retrieves one computer by id.
     * @param id : id of computer to find
     * @return computer find with id selected
     */
    Optional<Computer> find(long id);

    /**
     * Delete CRUD's operation.
     *
     * @param id : id
     * @return long id
     */
    long delete(long id);

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     * @return computer
     */
    Optional<Computer> update(Optional<Computer> computer);

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     */
    List<Computer> findAll();

    /**
     * Return the number of computer in database.
     * @return long
     */
    long getCountOfComputers();

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     */
    List<Computer> getComputersFiltered(String nameFilter);

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param pagination : pagination
     * @return list of computers paginated
     */
    List<Computer> findByPageFilter(Pagination pagination);

}
