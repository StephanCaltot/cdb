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
     * @throws PersistenceException : PersistenceException
     */
    public Optional<Company> find(long id) throws PersistenceException {

        if (id <= 0) {
            LOGGER.warn("You are trying to find a company with null or negative id");
            return Optional.empty();
        }

        Company company = null;

        try {

            jdbcTemplateObject.queryForObject(DaoProperties.FIND_COMPANY, new MapperCompany(), id);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException(dataAccessException);
        }

        return Optional.of(company);
    }

    /**
     * Retrieves all companies.
     * @return list contains all companies
     * @throws PersistenceException : PersistenceException
     */
    public List<Company> findAll() throws PersistenceException {

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
     * @throws PersistenceException : PersistenceException
     */
    public List<Company> findByPageFilter(Pagination pagination) throws PersistenceException {

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

            return jdbcTemplateObject.query("select company.id, company.name from company where company.name like '%" + filter + "%' limit " + pageSize + " offset " + offset + ";", new MapperCompany(), filter, pageSize, offset);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }
    }

    /**
     * Return the number of computer in database.
     * @return long
     * @throws PersistenceException : PersistenceException
     */
    public long getCountOfElements() throws PersistenceException {

        try {

            return jdbcTemplateObject.queryForObject(DaoProperties.COUNT_COMPANY, long.class);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }
    }

    /**
     * Delete one company with computer associated.
     * @param id : id of company
     * @return boolean
     * @throws PersistenceException : PersistenceException
     */
    public boolean delete(long id) throws PersistenceException {
        if (id <= 0) {
            LOGGER.warn("You are trying to delete a company with null or negative id !\n");
            return false;
        }

        try {
            int res = jdbcTemplateObject.update(DaoProperties.DELETE_COMPUTER_COMPANY, id);

            if (res == 0) {
                LOGGER.warn("Deletion of computer with id" + id + "failed !");
                return false;
            }

            res = jdbcTemplateObject.update(DaoProperties.DELETE_COMPANY, id);

            if (res == 0) {
                LOGGER.warn("This company doesn't exist, choose an other ID !");
                return false;
            }
            return true;
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

    }

}