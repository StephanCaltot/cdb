package com.excilys.scaltot.cdb.services.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudComputer;
import com.excilys.scaltot.cdb.services.interfaces.CrudComputerService;
import com.excilys.scaltot.cdb.utils.Pagination;

@Service
@Scope("singleton")
@Transactional
public class CrudComputerServiceImpl implements CrudComputerService {

    @Autowired
    private CrudComputer crudComputerImpl;

    /**
     * Create computer.
     * @param computer : computer
     */
    public void create(Optional<Computer> computer) {

        try {
            crudComputerImpl.create(computer);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     * @return computer entity find with id gave in parameter
     */
    public Optional<Computer> find(long id) {

        try {
            return crudComputerImpl.find(id);

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }

    /**
     * Delete CRUD's operation.
     *
     * @param id : id
     * @return boolean
     */
    public Boolean delete(long id) {

        try {
           if (!crudComputerImpl.delete(id)) {
               return false;
           }
           return true;
       } catch (SQLException e) {
           throw new PersistenceException(e);
       }
   }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     */
    public void update(Optional<Computer> computer) {

        try {
            crudComputerImpl.update(computer);
       } catch (SQLException e) {
           throw new PersistenceException(e);
       }
    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     */
    public List<Computer> findAll() {

        try {

            return crudComputerImpl.findAll();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfComputers() {

        try {

            return crudComputerImpl.getCountOfElements();

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     * @throws SQLException : SQLException
     */
    public List<Computer> getComputersFiltered(String nameFilter) throws SQLException {

        try {

            return crudComputerImpl.getComputersFiltered(nameFilter);

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param pagination : pagination
     * @return list of computers paginated
     */
    public List<Computer> findByPageFilter(Pagination pagination) {

        try {

            return crudComputerImpl.findByPageFilter(pagination);

        } catch (SQLException e) {

            throw new PersistenceException(e);

        }
    }
}