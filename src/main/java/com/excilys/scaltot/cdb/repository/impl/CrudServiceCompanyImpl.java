package com.excilys.scaltot.cdb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.repository.CrudService;
import com.excilys.scaltot.cdb.repository.CrudServiceConstant;
import com.excilys.scaltot.cdb.repository.mappers.MapperCompany;
import com.excilys.scaltot.cdb.repository.Pagination;
import com.excilys.scaltot.cdb.utils.DaoProperties;

/**
 * Crud service allows CRUD's operations on Company entities.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public enum CrudServiceCompanyImpl implements CrudService<Company> {

    INSTANCE;
    Logger LOGGER = LoggerFactory.getLogger(CrudServiceCompanyImpl.class.getName());

    private ResultSet resultSet;
    private Company company;
    private List<Company> companies;
    private Connection connection;

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

        connection = CrudServiceConstant.jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_COMPANY);
            CrudServiceConstant.preparedStatementFind.setLong(1, id);
            resultSet = CrudServiceConstant.preparedStatementFind.executeQuery();
            resultSet.next();
            company = MapperCompany.resultSetToEntity(Optional.of(resultSet)).get();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
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

        connection = CrudServiceConstant.jdbcConnection.getConnection();
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
            CrudServiceConstant.jdbcConnection.closeConnection();
        }

        return companies;
    }

    /**
     * Allows pagination for findAll companies.
     * @param pagination : page
     * @return List of companies
     */
    public List<Company> findByPage(Pagination pagination) {

        connection = CrudServiceConstant.jdbcConnection.getConnection();
        companies = new ArrayList<>();
        long offset = pagination.getOffset();
        long pageSize = pagination.getPageSize();

        if (pageSize <= 0) {
            pageSize = CrudServiceConstant.LIMIT_DEFAULT;
        }
        if (offset < 0) {
            offset = 0;
        }
        try {
            CrudServiceConstant.preparedStatementFindByPage = connection.prepareStatement(DaoProperties.PAGE_COMPANY);
            CrudServiceConstant.preparedStatementFindByPage.setLong(1, pageSize);
            CrudServiceConstant.preparedStatementFindByPage.setLong(2, offset);
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
            CrudServiceConstant.jdbcConnection.closeConnection();
        }

        return companies;
    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfCompanies() {

        connection = CrudServiceConstant.jdbcConnection.getConnection();

        try {
            CrudServiceConstant.preparedStatementCountCompanies = connection.prepareStatement(DaoProperties.COUNT_COMPANY);
            resultSet = CrudServiceConstant.preparedStatementCountCompanies.executeQuery();
            resultSet.next();
            return resultSet.getInt("number");
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }
    }
}