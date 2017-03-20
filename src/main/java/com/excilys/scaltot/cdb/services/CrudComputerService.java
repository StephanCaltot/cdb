package com.excilys.scaltot.cdb.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.dao.impl.CrudComputerImpl;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.utils.Pagination;

public enum CrudComputerService {

    INSTANCE;

    /**
     * Create computer.
     * @param computer : computer
     */
    public void create(Optional<Computer> computer) {
        CrudComputerImpl.INSTANCE.create(computer);
    }

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     * @return computer entity find with id gave in parameter
     * @throws PersistenceException : PersistenceException
     * @throws Exception
     */
    public Optional<Computer> find(long id) {
        return CrudComputerImpl.INSTANCE.find(id);
    }

    /**
     * Delete CRUD's operation.
     *
     * @param id : id
     * @throws PersistenceException : PersistenceException
     * @throws SQLException
     * @return boolean
     */
    public Boolean delete(long id) {
        return CrudComputerImpl.INSTANCE.delete(id);
    }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     * @throws PersistenceException : PersistenceException
     * @throws Exception : Exception
     */
    public void update(Optional<Computer> computer) {
        CrudComputerImpl.INSTANCE.update(computer);
    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     * @throws PersistenceException :PersistenceException
     * @throws Exception : Exception
     */
    public List<Computer> findAll() {
        return CrudComputerImpl.INSTANCE.findAll();
    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfComputers() {
        return CrudComputerImpl.INSTANCE.getCountOfElements();
    }

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     */
    public List<Computer> getComputersFiltered(String nameFilter) {
        return CrudComputerImpl.INSTANCE.getComputersFiltered(nameFilter);
    }

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param pagination : pagination
     * @return list of computers paginated
     * @throws PersistenceException : PersistenceException
     * @throws Exception
     */
    public List<Computer> findByPageFilter(Pagination pagination) {
        return CrudComputerImpl.INSTANCE.findByPageFilter(pagination);
    }
}
