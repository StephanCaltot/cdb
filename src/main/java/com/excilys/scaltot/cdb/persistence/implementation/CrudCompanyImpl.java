package com.excilys.scaltot.cdb.persistence.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.mappers.MapperCompany;
import com.excilys.scaltot.cdb.persistence.CrudServiceConstant;
import com.excilys.scaltot.cdb.persistence.DaoProperties;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudCompany;
import com.excilys.scaltot.cdb.utils.Datasource;
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
     * @param connection : connection
     * @return company entity find with id gave in parameter
     * @throws SQLException
     */
    public Optional<Company> find(long id) throws SQLException {

        if (id <= 0) {
            LOGGER.warn("You are trying to find a company with null or negative id");
            return Optional.empty();
        }

        Company company = jdbcTemplateObject.queryForObject(DaoProperties.FIND_COMPANY, new MapperCompany(), id);

        return Optional.of(company);
    }

    /**
     * Retrieves all companies.
     * @param connection : connection
     * @return list contains all companies
     * @throws SQLException  : SQLException
     */
    public List<Company> findAll() throws SQLException {

        List<Company> companies = jdbcTemplateObject.query(DaoProperties.FIND_ALL_COMPANIES, new MapperCompany());

        return companies;
    }

    /**
     * Allows pagination for findAll companies.
     * @param pagination : page
     * @return List of companies
     * @throws SQLException
     */
    public List<Company> findByPageFilter(Pagination pagination) throws SQLException {

        long offset = pagination.getOffset();
        long pageSize = pagination.getPageSize();
        String filter = pagination.getFilter();

        if (pageSize <= 0) {
            pageSize = CrudServiceConstant.LIMIT_DEFAULT;
        }
        if (offset < 0) {
            offset = 0;
        }

        List<Company> companies = jdbcTemplateObject.query(DaoProperties.PAGE_COMPANY_FILTERED, new MapperCompany(), filter, pageSize, offset);

        return companies;
    }

    /**
     * Return the number of computer in database.
     * @return long
     * @throws SQLException : SQLException
     */
    public long getCountOfElements() throws SQLException {

        long countOfElements = jdbcTemplateObject.queryForObject(DaoProperties.COUNT_COMPANY, long.class);

        return countOfElements;
    }

    /**
     * Delete one company with computer associated.
     * @param id : id of company
     * @param connection : connection
     * @return boolean
     * @throws SQLException : SQLException
     */
    public boolean delete(long id) throws SQLException {
        if (id <= 0) {
            LOGGER.warn("You are trying to delete a company with null or negative id !\n");
            return false;
        }

        int res = jdbcTemplateObject.queryForObject(DaoProperties.DELETE_COMPUTER_COMPANY, Integer.class, id);

        if (res == 0) {
            LOGGER.warn("Deletion of computer with id" + id + "failed !");
            return false;
        }

        res = jdbcTemplateObject.queryForObject(DaoProperties.DELETE_COMPANY, Integer.class, id);
        
        if (res == 0) {
            LOGGER.warn("This company doesn't exist, choose an other ID !");
            return false;
        }
        return true;
    }

}