package com.excilys.scaltot.cdb.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.persistence.impl.CrudComputerImpl;
import com.excilys.scaltot.cdb.utils.Pagination;

@Service
public class CrudComputerService {

    @Autowired
    private CrudComputerImpl crudComputerImpl;

    /**
     * Create computer.
     * @param computer : computer
     */
    public void create(Optional<Computer> computer) {
        crudComputerImpl.create(computer);
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
        return crudComputerImpl.find(id);
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
        return crudComputerImpl.delete(id);
    }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     * @throws PersistenceException : PersistenceException
     * @throws Exception : Exception
     */
    public void update(Optional<Computer> computer) {
        crudComputerImpl.update(computer);
    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     * @throws PersistenceException :PersistenceException
     * @throws Exception : Exception
     */
    public List<Computer> findAll() {
        return crudComputerImpl.findAll();
    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfComputers() {
        return crudComputerImpl.getCountOfElements();
    }

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     */
    public List<Computer> getComputersFiltered(String nameFilter) {
        return crudComputerImpl.getComputersFiltered(nameFilter);
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
        return crudComputerImpl.findByPageFilter(pagination);
    }
}
