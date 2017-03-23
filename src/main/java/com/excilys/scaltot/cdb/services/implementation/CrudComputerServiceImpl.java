package com.excilys.scaltot.cdb.services.implementation;

import java.sql.Connection;
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
import com.excilys.scaltot.cdb.utils.DatabaseManager;
import com.excilys.scaltot.cdb.utils.Pagination;

@Service
@Scope("singleton")
@Transactional
public class CrudComputerServiceImpl implements CrudComputerService {

    @Autowired
    private CrudComputer crudComputerImpl;
    private Connection connection;
    @Autowired
    private DatabaseManager databaseManager;

    /**
     * Create computer.
     * @param computer : computer
     */
    public void create(Optional<Computer> computer) {

        connection = databaseManager.getConnection();

        try {
            crudComputerImpl.create(computer, connection);
            databaseManager.commit();

        } catch (SQLException e) {
            databaseManager.rollback();
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
    }

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     * @return computer entity find with id gave in parameter
     */
    public Optional<Computer> find(long id) {

        connection = databaseManager.getConnection();

        try {
            return crudComputerImpl.find(id, connection);

        } catch (SQLException e) {

            throw new PersistenceException(e);

        } finally {

            databaseManager.closeConnection();

        }
    }

    /**
     * Delete CRUD's operation.
     *
     * @param id : id
     * @return boolean
     */
    public Boolean delete(long id) {

        connection = databaseManager.getConnection();

        try {
           if (!crudComputerImpl.delete(id, connection)) {
               return false;
           }
           databaseManager.commit();
           return true;
       } catch (SQLException e) {
           databaseManager.rollback();
           throw new PersistenceException(e);
       } finally {
           databaseManager.closeConnection();
       }
   }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     */
    public void update(Optional<Computer> computer) {

        connection = databaseManager.getConnection();

        try {
            crudComputerImpl.update(computer, connection);
            databaseManager.commit();
       } catch (SQLException e) {
           databaseManager.rollback();
           throw new PersistenceException(e);
       } finally {
           databaseManager.closeConnection();
       }
    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     */
    public List<Computer> findAll() {

        connection = databaseManager.getConnection();

        try {

            return crudComputerImpl.findAll(connection);

        } catch (SQLException e) {

            throw new PersistenceException(e);

        } finally {

            databaseManager.closeConnection();

        }
    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfComputers() {

        connection = databaseManager.getConnection();

        try {

            return crudComputerImpl.getCountOfElements(connection);

        } catch (SQLException e) {

            throw new PersistenceException(e);

        } finally {

            databaseManager.closeConnection();

        }
    }

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     * @throws SQLException : SQLException
     */
    public List<Computer> getComputersFiltered(String nameFilter) throws SQLException {

        connection = databaseManager.getConnection();

        try {

            return crudComputerImpl.getComputersFiltered(nameFilter, connection);

        } catch (SQLException e) {

            throw new PersistenceException(e);

        } finally {

            databaseManager.closeConnection();

        }
    }

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param pagination : pagination
     * @return list of computers paginated
     */
    public List<Computer> findByPageFilter(Pagination pagination) {

        connection = databaseManager.getConnection();

        try {

            return crudComputerImpl.findByPageFilter(pagination, connection);

        } catch (SQLException e) {

            throw new PersistenceException(e);

        } finally {

            databaseManager.closeConnection();

        }
    }
}