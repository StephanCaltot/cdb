package com.excilys.scaltot.cdb.persistence.implementation;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.mappers.MapperCompany;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.pagination.Pagination;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudCompany;
import com.excilys.scaltot.cdb.persistence.utils.CrudServiceConstant;
import com.excilys.scaltot.cdb.persistence.utils.DaoProperties;
import com.excilys.scaltot.cdb.persistence.utils.Datasource;

/**
 * Crud service allows CRUD's operations on Company entities.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
@Repository
@Scope("singleton")
public class CrudCompanyImpl implements CrudCompany {

    Logger LOGGER = LoggerFactory.getLogger(CrudCompanyImpl.class.getName());

    @Autowired
    private Datasource dataSource;

    private JdbcTemplate jdbcTemplateObject;

    /**
     * Avoids error on JDBC template initialization.
     */
    @PostConstruct
    public void setDataSource() {

       this.jdbcTemplateObject = new JdbcTemplate(dataSource);

    }

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     * @return company entity find with id gave in parameter
     */
    public Optional<Company> find(long id) {

        LOGGER.warn("Finding company with id " + id + "...");

        if (id <= 0) {
            throw new IllegalArgumentException("You are trying to find a company with null or negative id !");
        }

        Company company = null;

        try {

            company = jdbcTemplateObject.queryForObject(DaoProperties.FIND_COMPANY, new MapperCompany(), id);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }

        return Optional.of(company);
    }

    /**
     * Retrieves all companies.
     * @return List contains all companies
     */
    public List<Company> findAll() {

        LOGGER.warn("Doing find all company...");

        try {

            return jdbcTemplateObject.query(DaoProperties.FIND_ALL_COMPANIES, new MapperCompany());

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }
    }

    /**
     * Allows pagination for findAll companies.
     * @param pagination : page
     * @return List of companies
     */
    public List<Company> findByPageFilter(Pagination pagination) {

        LOGGER.warn("Getting company filtered by name beggining by " + pagination.getFilter() +  "...");

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

            return jdbcTemplateObject.query(DaoProperties.PAGE_COMPANY_FILTERED, new MapperCompany(), filter, pageSize, offset);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }
    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfElements() {

        LOGGER.warn("Getting number of company...");

        try {

            return jdbcTemplateObject.queryForObject(DaoProperties.COUNT_COMPANY, long.class);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }
    }

    /**
     * Delete one company with computer associated.
     * @param id : id of company
     * @return long id
     */
    public long delete(long id) {

        LOGGER.warn("Deleting company with id " + id + "...");

        if (id <= 0) {

            throw new IllegalArgumentException("You are trying to delete a company with null or negative id !");
        }

        try {

            jdbcTemplateObject.update(DaoProperties.DELETE_COMPUTER_COMPANY, id);
            jdbcTemplateObject.update(DaoProperties.DELETE_COMPANY, id);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

        return id;

    }

}