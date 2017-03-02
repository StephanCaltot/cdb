package com.excilys.scaltot.cdb.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.repository.impl.CrudServiceComputerImpl;

public class CrudComputerService {

    /**
     * Create computer.
     * @param computer : computer
     */
    public static void create(Optional<Computer> computer) {
        CrudServiceComputerImpl.INSTANCE.create(computer);
    }

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     * @return computer entity find with id gave in parameter
     * @throws PersistenceException : PersistenceException
     * @throws Exception
     */
    public static Optional<Computer> find(long id) {
        return CrudServiceComputerImpl.INSTANCE.find(id);
    }

    /**
     * Delete CRUD's operation.
     *
     * @param id : id
     * @throws PersistenceException : PersistenceException
     * @throws SQLException
     * @return boolean
     */
    public static Boolean delete(long id) {
        return CrudServiceComputerImpl.INSTANCE.delete(id);
    }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     * @throws PersistenceException : PersistenceException
     * @throws Exception : Exception
     */
    public static void update(Optional<Computer> computer) {
        CrudServiceComputerImpl.INSTANCE.update(computer);
    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     * @throws PersistenceException :PersistenceException
     * @throws Exception : Exception
     */
    public static List<Computer> findAll() {
        return CrudServiceComputerImpl.INSTANCE.findAll();
    }

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param offset : begin's value of current page
     * @param numberForEachpPage : number of elements for each page
     * @return list of computers paginated
     * @throws PersistenceException : PersistenceException
     * @throws Exception
     */
    public static List<Computer> findByPage(long offset, long numberForEachpPage) {
        return CrudServiceComputerImpl.INSTANCE.findByPage(offset, numberForEachpPage);
    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public static long getCountOfComputers() {
        return CrudServiceComputerImpl.INSTANCE.getCountOfComputers();
    }

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     */
    public static List<Computer> getComputersFiltered(String nameFilter) {
        return CrudServiceComputerImpl.INSTANCE.getComputersFiltered(nameFilter);
    }

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param offset : begin's value of current page
     * @param numberForEachpPage : number of elements for each page
     * @param filter : name filter
     * @return list of computers paginated
     * @throws PersistenceException : PersistenceException
     * @throws Exception
     */
    public static List<Computer> findByPageFilter(long offset, long numberForEachpPage, String filter) {
        return CrudServiceComputerImpl.INSTANCE.findByPageFilter(offset, numberForEachpPage, filter);
    }
}
