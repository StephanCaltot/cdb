package com.excilys.scaltot.cdb.repository.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.repository.CrudServiceComputer;
import com.excilys.scaltot.cdb.repository.CrudServiceConstant;
import com.excilys.scaltot.cdb.repository.MapperComputer;
import com.excilys.scaltot.cdb.utils.DaoProperties;

/**
 * CRUD service allows CRUD operations on Computer entities.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public enum CrudServiceComputerImpl implements CrudServiceComputer {

    INSTANCE;

    Logger LOGGER = LoggerFactory.getLogger(CrudServiceComputerImpl.class);

    private ResultSet resultSet;
    private Computer computer;
    private List<Computer> computers;
    private Connection connection;

    /**
     * Create CRUD's operation.
     *
     * @param computer : computer
     * @throws PersistenceException : persistence exception
     * @throws SQLException : SQLException
     */
    public void create(Optional<Computer> computer) {
        if (!computer.isPresent()) {
            LOGGER.warn("You are trying to create a null computer !\n");
            return;
        }
        connection = CrudServiceConstant.jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementInsert = connection.prepareStatement(DaoProperties.CREATE_COMPUTER);

            if (computer.get().getName() != null) {
                CrudServiceConstant.preparedStatementInsert.setString(1, computer.get().getName());
            } else {
                CrudServiceConstant.preparedStatementInsert.setNull(1, java.sql.Types.VARCHAR);
            }

            if (computer.get().getDateWichIsIntroduced() != null) {
                CrudServiceConstant.preparedStatementInsert.setObject(2,
                        Date.valueOf(computer.get().getDateWichIsIntroduced()));
            } else {
                CrudServiceConstant.preparedStatementInsert.setNull(2, java.sql.Types.DATE);
            }
            if (computer.get().getDateWichIsDiscontinued() != null) {
                CrudServiceConstant.preparedStatementInsert.setDate(3,
                        Date.valueOf(computer.get().getDateWichIsDiscontinued()));
            } else {
                CrudServiceConstant.preparedStatementInsert.setNull(3, java.sql.Types.DATE);
            }

            if (computer.get().getManufacturer() != null) {
                CrudServiceConstant.preparedStatementInsert.setLong(4, computer.get().getManufacturer().getId());
            } else {
                CrudServiceConstant.preparedStatementInsert.setNull(4, java.sql.Types.INTEGER);

            }
            CrudServiceConstant.preparedStatementInsert.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }
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

        if (id <= 0) {
            LOGGER.warn("You are trying to find a computer with null or negative id !\n");
            return Optional.empty();
        }
        connection = CrudServiceConstant.jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_COMPUTER);
            CrudServiceConstant.preparedStatementFind.setLong(1, id);

            if (!CrudServiceConstant.preparedStatementFind.executeQuery().isBeforeFirst()) {
                LOGGER.warn("You are trying to find a computer doesn't exists anymore");
                return Optional.empty();
            }

            resultSet = CrudServiceConstant.preparedStatementFind.executeQuery();
            resultSet.next();
            computer = MapperComputer.resultSetToEntity(Optional.of(resultSet)).get();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }

        return Optional.of(computer);

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
        if (id <= 0) {
            LOGGER.warn("You are trying to delete a computer with null or negative id !\n");
            return false;
        }
        connection = CrudServiceConstant.jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementDelete = connection.prepareStatement(DaoProperties.DELETE_COMPUTER);
            CrudServiceConstant.preparedStatementDelete.setLong(1, id);
            if (CrudServiceConstant.preparedStatementDelete.executeUpdate() == 0) {
                LOGGER.warn("This computer doesn't exist, choose an other ID !");
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }
    }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     * @throws PersistenceException : PersistenceException
     * @throws Exception
     */
    public void update(Optional<Computer> computer) {
        if (!computer.isPresent()) {
            LOGGER.warn("You are trying to update a null computer !\n");
            return;
        }
        connection = CrudServiceConstant.jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementUpdate = connection.prepareStatement(DaoProperties.UPDATE_COMPUTER);

            if (computer.get().getName() != null) {
                CrudServiceConstant.preparedStatementUpdate.setString(1, computer.get().getName());
            } else {
                CrudServiceConstant.preparedStatementUpdate.setNull(1, java.sql.Types.VARCHAR);
            }

            if (computer.get().getDateWichIsIntroduced() != null) {
                CrudServiceConstant.preparedStatementUpdate.setObject(2,
                        Date.valueOf(computer.get().getDateWichIsIntroduced()));
            } else {
                CrudServiceConstant.preparedStatementUpdate.setNull(2, java.sql.Types.DATE);
            }

            if (computer.get().getDateWichIsDiscontinued() != null) {
                CrudServiceConstant.preparedStatementUpdate.setDate(3,
                        Date.valueOf(computer.get().getDateWichIsDiscontinued()));
            } else {
                CrudServiceConstant.preparedStatementUpdate.setNull(3, java.sql.Types.DATE);
            }

            if (computer.get().getManufacturer() != null) {
                CrudServiceConstant.preparedStatementUpdate.setLong(4, computer.get().getManufacturer().getId());
            } else {
                CrudServiceConstant.preparedStatementUpdate.setNull(4, java.sql.Types.INTEGER);
            }

            if (computer.get().getId() != 0) {
                CrudServiceConstant.preparedStatementUpdate.setLong(5, computer.get().getId());
            } else {
                CrudServiceConstant.preparedStatementUpdate.setNull(5, java.sql.Types.INTEGER);
            }

            CrudServiceConstant.preparedStatementUpdate.execute();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }
    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     * @throws PersistenceException
     * @throws Exception
     */
    public List<Computer> findAll() {

        connection = CrudServiceConstant.jdbcConnection.getConnection();
        computers = new ArrayList<>();

        try {
            CrudServiceConstant.preparedStatementFindAll = connection
                    .prepareStatement(DaoProperties.FIND_ALL_COMPUTERS);
            resultSet = CrudServiceConstant.preparedStatementFindAll.executeQuery();
            while (resultSet.next()) {
                if (MapperComputer.resultSetToEntity(Optional.of(resultSet)).isPresent()) {
                    computer = MapperComputer.resultSetToEntity(Optional.of(resultSet)).get();
                    computers.add(computer);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }

        return computers;
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
    public List<Computer> findByPage(long offset, long numberForEachpPage) {

        connection = CrudServiceConstant.jdbcConnection.getConnection();
        computers = new ArrayList<>();

        if (numberForEachpPage <= 0) {
            numberForEachpPage = CrudServiceConstant.LIMIT_DEFAULT;
        }
        if (offset < 0) {
            offset = 0;
        }
        try {
            CrudServiceConstant.preparedStatementFindByPage = connection.prepareStatement(DaoProperties.PAGE_COMPUTER);
            CrudServiceConstant.preparedStatementFindByPage.setLong(1, numberForEachpPage);
            CrudServiceConstant.preparedStatementFindByPage.setLong(2, offset);
            resultSet = CrudServiceConstant.preparedStatementFindByPage.executeQuery();
            while (resultSet.next()) {
                if (MapperComputer.resultSetToEntity(Optional.of(resultSet)).isPresent()) {
                    computer = MapperComputer.resultSetToEntity(Optional.of(resultSet)).get();
                    computers.add(computer);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }

        return computers;
    }
}
