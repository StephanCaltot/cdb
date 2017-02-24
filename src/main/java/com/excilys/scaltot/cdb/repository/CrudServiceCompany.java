package com.excilys.scaltot.cdb.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;

/**
 * Crud service allows CRUD's operations on Company entities.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public class CrudServiceCompany implements CrudService<Company> {

    private ResultSet resultSet;
    private Company company;
    private List<Company> companies;
    private Connection connection;

    /**
     * Constructor initializing statement.
     *
     * @throws SQLException
     */
    public CrudServiceCompany() {

    }

    /**
     * Find CRUD's operation.
     *
     * @param id :
     * @return company entity find with id gave in parameter
     * @throws SQLException
     */
    public Optional<Company> find(long id) {

        connection = CrudServiceConstant.jdbcConnection.getConnection();
        company = null;

        try {
            CrudServiceConstant.preparedStatementFind = connection.prepareStatement(DaoProperties.FIND_COMPANY);
            CrudServiceConstant.preparedStatementFind.setLong(1, id);
            resultSet = CrudServiceConstant.preparedStatementFind.executeQuery();
            resultSet.next();
            company = MapperCompany.resultSetToEntity(resultSet).get();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
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
     * @throws Exception
     */
    public List<Company> findAll() {

        connection = CrudServiceConstant.jdbcConnection.getConnection();
        companies = new ArrayList<>();

        try {
            CrudServiceConstant.preparedStatementFindAll = connection
                    .prepareStatement(DaoProperties.FIND_ALL_COMPANIES);
            resultSet = CrudServiceConstant.preparedStatementFind.executeQuery();
            while (resultSet.next()) {
                if (MapperCompany.resultSetToEntity(resultSet).isPresent()) {
                    company = MapperCompany.resultSetToEntity(resultSet).get();
                    companies.add(company);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }

        return companies;
    }

    /**
     * Allows pagination for findAll companies.
     *
     * @param offset :
     * @param numberForEachpPage :
     * @return list of companies paginated
     * @throws Exception :
     */
    public List<Company> findByPage(long offset, int numberForEachpPage) {

        connection = CrudServiceConstant.jdbcConnection.getConnection();
        companies = new ArrayList<>();

        if (numberForEachpPage <= 0) {
            numberForEachpPage = CrudServiceConstant.LIMIT_DEFAULT;
        }
        if (offset < 0) {
            offset = 0;
        }
        try {
            CrudServiceConstant.preparedStatementFindByPage = connection.prepareStatement(DaoProperties.PAGE_COMPANY);
            CrudServiceConstant.preparedStatementFindByPage.setInt(1, numberForEachpPage);
            CrudServiceConstant.preparedStatementFindByPage.setLong(2, offset);
            resultSet = CrudServiceConstant.preparedStatementFindByPage.executeQuery();
            while (resultSet.next()) {
                if (MapperCompany.resultSetToEntity(resultSet).isPresent()) {
                    company = MapperCompany.resultSetToEntity(resultSet).get();
                    companies.add(company);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CrudServiceConstant.jdbcConnection.closeConnection();
        }

        return companies;
    }
}