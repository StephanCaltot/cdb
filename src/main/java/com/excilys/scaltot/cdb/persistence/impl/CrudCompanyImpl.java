package com.excilys.scaltot.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.mappers.MapperCompany;
import com.excilys.scaltot.cdb.persistence.CrudServiceConstant;
import com.excilys.scaltot.cdb.persistence.DaoProperties;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudCompany;
import com.excilys.scaltot.cdb.utils.DatabaseManager;
import com.excilys.scaltot.cdb.utils.Pagination;

/**
 * Crud service allows CRUD's operations on Company entities.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
@Repository
@Scope("singleton")
public enum CrudCompanyImpl implements CrudCompany {

    INSTANCE;

    Logger LOGGER = LoggerFactory.getLogger(CrudCompanyImpl.class.getName());

    private ResultSet resultSet;
    private Company company;
    private List<Company> companies;
    private Connection connection;
    @Autowired
    private DatabaseManager jdbcConnection;

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     *            :
     * @return company entity find with id gave in parameter
     * @throws PersistenceException
     *             :
     * @throws SQLException
     */
    public Optional<Company> find(long id) {

        if (id <= 0) {
            LOGGER.warn("You are trying to find a company with null or negative id");
            return Optional.empty();
        }

        connection = jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_COMPANY);
            CrudServiceConstant.preparedStatementFind.setLong(1, id);
            resultSet = CrudServiceConstant.preparedStatementFind.executeQuery();
            resultSet.next();
            company = MapperCompany.resultSetToEntity(Optional.of(resultSet)).get();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcConnection.closeConnection();
        }

        return Optional.of(company);
    }

    /**
     * Retrieves all companies.
     *
     * @return list contains all companies
     * @throws PersistenceException
     *             :
     * @throws Exception
     */
    public List<Company> findAll() {

        connection = jdbcConnection.getConnection();
        companies = new ArrayList<>();

        try {
            CrudServiceConstant.preparedStatementFindAll = connection.prepareStatement(DaoProperties.FIND_ALL_COMPANIES);
            resultSet = CrudServiceConstant.preparedStatementFindAll.executeQuery();
            while (resultSet.next()) {
                if (MapperCompany.resultSetToEntity(Optional.of(resultSet)).isPresent()) {
                    company = MapperCompany.resultSetToEntity(Optional.of(resultSet)).get();
                    companies.add(company);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            jdbcConnection.closeConnection();
        }

        return companies;
    }

    /**
     * Allows pagination for findAll companies.
     * @param pagination : page
     * @return List of companies
     */
    public List<Company> findByPageFilter(Pagination pagination) {

        connection = jdbcConnection.getConnection();
        companies = new ArrayList<>();
        long offset = pagination.getOffset();
        long pageSize = pagination.getPageSize();
        String filter = pagination.getFilter();

        if (pageSize <= 0) {
            pageSize = CrudServiceConstant.LIMIT_DEFAULT;
        }
        if (offset < 0) {
            offset = 0;
        }
        try {
            CrudServiceConstant.preparedStatementFindByPage = connection.prepareStatement(DaoProperties.PAGE_COMPANY_FILTERED);
            CrudServiceConstant.preparedStatementFindByPage.setString(1, "%" + filter + "%");
            CrudServiceConstant.preparedStatementFindByPage.setLong(2, pageSize);
            CrudServiceConstant.preparedStatementFindByPage.setLong(3, offset);
            resultSet = CrudServiceConstant.preparedStatementFindByPage.executeQuery();
            while (resultSet.next()) {
                if (MapperCompany.resultSetToEntity(Optional.of(resultSet)).isPresent()) {
                    company = MapperCompany.resultSetToEntity(Optional.of(resultSet)).get();
                    companies.add(company);
                }
            }
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            jdbcConnection.closeConnection();
        }

        return companies;
    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfElements() {

        connection = jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementCountCompanies = connection.prepareStatement(DaoProperties.COUNT_COMPANY);
            resultSet = CrudServiceConstant.preparedStatementCountCompanies.executeQuery();
            resultSet.next();
            return resultSet.getInt("number");
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            jdbcConnection.closeConnection();
        }
    }

    /**
     * Delete one company with computer associated.
     * @param id : id of company
     * @return boolean
     */
    public boolean delete(long id) {
        if (id <= 0) {
            LOGGER.warn("You are trying to delete a computer with null or negative id !\n");
            return false;
        }
        connection = jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementDelete = connection.prepareStatement(DaoProperties.DELETE_COMPUTER_COMPANY);
            CrudServiceConstant.preparedStatementDelete.setLong(1, id);
            if (CrudServiceConstant.preparedStatementDelete.executeUpdate() == 0) {
                LOGGER.warn("This company doesn't exist, choose an other ID !");
                return false;
            }
            CrudServiceConstant.preparedStatementDelete = connection.prepareStatement(DaoProperties.DELETE_COMPANY);
            CrudServiceConstant.preparedStatementDelete.setLong(1, id);
            CrudServiceConstant.preparedStatementDelete.executeUpdate();

            jdbcConnection.commit();
            return true;
        } catch (SQLException e) {
            jdbcConnection.rollback();
            throw new PersistenceException(e);
        } finally {
            jdbcConnection.closeConnection();
        }
    }
}