package com.excilys.scaltot.cdb.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.computer.Computer;

/**
 * Crud service allows CRUD operations on Computer entities.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public class CrudServiceComputer implements CrudService<Computer> {

    private ResultSet resultSet;
    private Computer computer;
    private List<Computer> computers;
    private Connection connection;

    /**
     * Constructor initializing statement.
     *
     * @throws SQLException
     */
    public CrudServiceComputer() {

    }

    /**
     * Create CRUD's operation.
     *
     * @param computer :
     * @throws SQLException :
     */
    public void create(Computer computer) {

        connection = CrudServiceConstant.jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementInsert = connection.prepareStatement(DaoProperties.CREATE_COMPUTER);
            CrudServiceConstant.preparedStatementInsert.setString(1, computer.getName());
            if (computer.getDateWichIsIntroduced() != null) {
                CrudServiceConstant.preparedStatementInsert.setObject(2,
                        Date.valueOf(computer.getDateWichIsIntroduced()));
            } else {
                CrudServiceConstant.preparedStatementInsert.setNull(2, java.sql.Types.DATE);
            }
            if (computer.getDateWichIsDiscontinued() != null) {
                CrudServiceConstant.preparedStatementInsert.setDate(3,
                        Date.valueOf(computer.getDateWichIsDiscontinued()));
            } else {
                CrudServiceConstant.preparedStatementInsert.setNull(3, java.sql.Types.DATE);
            }

            if (computer.getManufacturer() != null) {
                CrudServiceConstant.preparedStatementInsert.setLong(4, computer.getManufacturer().getId());
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
     * @param id :
     * @return computer entity find with id gave in parameter
     * @throws Exception
     */
    public Optional<Computer> find(long id) {
        connection = CrudServiceConstant.jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_COMPUTER);
            CrudServiceConstant.preparedStatementFind.setLong(1, id);
            resultSet = CrudServiceConstant.preparedStatementFind.executeQuery();
            resultSet.next();
            computer = MapperComputer.resultSetToEntity(resultSet).get();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }

        return Optional.of(computer);

    }

    /**
     * Delete CRUD's operation.
     *
     * @param id :
     * @throws SQLException
     */
    public void delete(long id) {

        connection = CrudServiceConstant.jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementDelete = connection.prepareStatement(DaoProperties.DELETE_COMPUTER);
            CrudServiceConstant.preparedStatementDelete.setLong(1, id);
            CrudServiceConstant.preparedStatementDelete.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }
    }

    /**
     * Update CRUD's operation.
     *
     * @param computer :
     * @throws Exception
     */
    public void update(Computer computer) {

        connection = CrudServiceConstant.jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementUpdate = connection.prepareStatement(DaoProperties.UPDATE_COMPUTER);
            CrudServiceConstant.preparedStatementUpdate.setString(1, computer.getName());
            CrudServiceConstant.preparedStatementUpdate.setLong(2, computer.getId());
            CrudServiceConstant.preparedStatementUpdate.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }
    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
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
                if (MapperComputer.resultSetToEntity(resultSet).isPresent()) {
                    computer = MapperComputer.resultSetToEntity(resultSet).get();
                    computers.add(computer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }

        return computers;
    }

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param offset :
     * @param numberForEachpPage :
     * @return list of computers paginated
     * @throws Exception
     */
    public List<Computer> findByPage(long offset, int numberForEachpPage) {

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
            CrudServiceConstant.preparedStatementFindByPage.setInt(1, numberForEachpPage);
            CrudServiceConstant.preparedStatementFindByPage.setLong(2, offset);
            resultSet = CrudServiceConstant.preparedStatementFindByPage.executeQuery();
            while (resultSet.next()) {
                if (MapperComputer.resultSetToEntity(resultSet).isPresent()) {
                    computer = MapperComputer.resultSetToEntity(resultSet).get();
                    computers.add(computer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }

        return computers;
    }
}
