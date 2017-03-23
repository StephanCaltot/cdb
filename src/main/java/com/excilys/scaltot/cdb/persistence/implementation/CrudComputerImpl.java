package com.excilys.scaltot.cdb.persistence.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.mappers.MapperComputer;
import com.excilys.scaltot.cdb.persistence.CrudServiceConstant;
import com.excilys.scaltot.cdb.persistence.DaoProperties;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudComputer;
import com.excilys.scaltot.cdb.utils.Pagination;

/**
 * CRUD service allows CRUD operations on Computer entities.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
@Repository
@Scope("singleton")
public class CrudComputerImpl implements CrudComputer {

    Logger LOGGER = LoggerFactory.getLogger(CrudComputerImpl.class);

    private ResultSet resultSet;
    private Computer computer;
    private List<Computer> computers;

    /**
     * Create CRUD's operation.
     *
     * @param computer : computer
     * @throws SQLException 
     */
    public void create(Optional<Computer> computer, Connection connection) throws SQLException {
        if (!computer.isPresent()) {
            LOGGER.warn("You are trying to create a null computer !\n");
            return;
        }

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

    }

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     * @return computer entity find with id gave in parameter
     * @throws SQLException : SQLException
     */
    public Optional<Computer> find(long id, Connection connection2) throws SQLException {

        if (id <= 0) {
            LOGGER.warn("You are trying to find a computer with null or negative id !\n");
            return Optional.empty();
        }

        CrudServiceConstant.preparedStatementFind = connection2.prepareStatement(DaoProperties.FIND_COMPUTER);
        CrudServiceConstant.preparedStatementFind.setLong(1, id);

        if (!CrudServiceConstant.preparedStatementFind.executeQuery().isBeforeFirst()) {
            LOGGER.warn("You are trying to find a computer doesn't exists anymore");
            return Optional.empty();
        }

        resultSet = CrudServiceConstant.preparedStatementFind.executeQuery();
        resultSet.next();
        computer = MapperComputer.resultSetToEntity(Optional.of(resultSet)).get();

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
    public boolean delete(long id, Connection connection) throws SQLException {
        if (id <= 0) {
            LOGGER.warn("You are trying to delete a computer with null or negative id !\n");
            return false;
        }

        CrudServiceConstant.preparedStatementDelete = connection.prepareStatement(DaoProperties.DELETE_COMPUTER);
        CrudServiceConstant.preparedStatementDelete.setLong(1, id);
        if (CrudServiceConstant.preparedStatementDelete.executeUpdate() == 0) {
            LOGGER.warn("This computer doesn't exist, choose an other ID !");
            return false;
        }
        return true;
    }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     * @throws SQLException 
     * @throws PersistenceException : PersistenceException
     * @throws Exception
     */
    public void update(Optional<Computer> computer, Connection connection) throws SQLException {
        if (!computer.isPresent()) {
            LOGGER.warn("You are trying to update a null computer !\n");
            return;
        }

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

    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     * @throws SQLException : SQLException 
     * @throws PersistenceException : SQLException
     * @throws Exception : SQLException
     */
    public List<Computer> findAll(Connection connection) throws SQLException {

        computers = new ArrayList<>();

        CrudServiceConstant.preparedStatementFindAll = connection.prepareStatement(DaoProperties.FIND_ALL_COMPUTERS);
        resultSet = CrudServiceConstant.preparedStatementFindAll.executeQuery();
        while (resultSet.next()) {
            if (MapperComputer.resultSetToEntity(Optional.of(resultSet)).isPresent()) {
                computer = MapperComputer.resultSetToEntity(Optional.of(resultSet)).get();
                computers.add(computer);
            }
        }

        return computers;
    }

    /**
     * Return the number of computer in database.
     * @return long
     * @throws SQLException 
     */
    public long getCountOfElements(Connection connection) throws SQLException {

        CrudServiceConstant.preparedStatementCountComputer = connection.prepareStatement(DaoProperties.COUNT_COMPUTER);
        resultSet = CrudServiceConstant.preparedStatementCountComputer.executeQuery();
        resultSet.next();
        return resultSet.getInt("number");

    }

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     * @throws SQLException 
     */
    public List<Computer> getComputersFiltered(String nameFilter, Connection connection) throws SQLException {

        computers = new ArrayList<>();

        CrudServiceConstant.preparedStatementComputersFiltered = connection.prepareStatement(DaoProperties.COMPUTER_FILTERED);
        CrudServiceConstant.preparedStatementComputersFiltered.setString(1, "%" + nameFilter + "%");
        resultSet = CrudServiceConstant.preparedStatementComputersFiltered.executeQuery();
        while (resultSet.next()) {
            if (MapperComputer.resultSetToEntity(Optional.of(resultSet)).isPresent()) {
                computer = MapperComputer.resultSetToEntity(Optional.of(resultSet)).get();
                computers.add(computer);
            }
        }
        return computers;
    }

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param pagination : page
     * @return list of computers paginated
     * @throws SQLException 
     * @throws PersistenceException : PersistenceException
     * @throws Exception
     */
    public List<Computer> findByPageFilter(Pagination pagination, Connection connection) throws SQLException {

        computers = new ArrayList<>();
        long offset = pagination.getOffset();
        long pageSize = pagination.getPageSize();
        String filter = pagination.getFilter();

        if (pageSize <= 0) {
            pageSize = CrudServiceConstant.LIMIT_DEFAULT;
        }
        if (offset < 0) {
            offset = 0;
        }

        CrudServiceConstant.preparedStatementFindByPage = connection.prepareStatement(DaoProperties.PAGE_COMPUTER_FILTERED);
        CrudServiceConstant.preparedStatementFindByPage.setString(1, "%" + filter + "%");
        CrudServiceConstant.preparedStatementFindByPage.setLong(2, pageSize);
        CrudServiceConstant.preparedStatementFindByPage.setLong(3, offset);
        resultSet = CrudServiceConstant.preparedStatementFindByPage.executeQuery();
        while (resultSet.next()) {
            if (MapperComputer.resultSetToEntity(Optional.of(resultSet)).isPresent()) {
                computer = MapperComputer.resultSetToEntity(Optional.of(resultSet)).get();
                computers.add(computer);
            }
        }

        return computers;
    }

}
