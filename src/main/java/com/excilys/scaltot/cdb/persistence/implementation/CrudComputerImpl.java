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

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.entities.mappers.MapperComputer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.pagination.Pagination;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudComputer;
import com.excilys.scaltot.cdb.persistence.utils.CrudServiceConstant;
import com.excilys.scaltot.cdb.persistence.utils.DaoProperties;
import com.excilys.scaltot.cdb.persistence.utils.Datasource;

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
     * Create CRUD's operation.
     *
     * @param computer : computer
     * @throws PersistenceException : PersistenceException
     * @return boolean
     */
    public boolean create(Optional<Computer> computer) throws PersistenceException {
        if (!computer.isPresent()) {
            LOGGER.warn("You are trying to create a null computer !\n");
            return false;
        }

        try {

            jdbcTemplateObject.update(DaoProperties.CREATE_COMPUTER, computer.get().getName(), computer.get().getDateWichIsIntroduced(), computer.get().getDateWichIsDiscontinued(), computer.get().getManufacturer().getId());

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

        return true;
    }

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     * @return computer entity find with id gave in parameter
     * @throws PersistenceException : PersistenceException
     */
    public Optional<Computer> find(long id) throws PersistenceException {

        if (id <= 0) {
            LOGGER.warn("You are trying to find a computer with null or negative id !\n");
            return Optional.empty();
        }

        Computer computer = null;

        try {

            computer = jdbcTemplateObject.queryForObject(DaoProperties.FIND_COMPUTER, new MapperComputer(), id);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

        return Optional.of(computer);

    }

    /**
     * Delete CRUD's operation.
     *
     * @param id : id
     * @throws PersistenceException : PersistenceException
     * @return boolean
     */
    public boolean delete(long id) throws PersistenceException {
        if (id <= 0) {
            LOGGER.warn("You are trying to delete a computer with null or negative id !\n");
            return false;
        }

        int res = 0;

        try {

            res = jdbcTemplateObject.update(DaoProperties.DELETE_COMPUTER, id);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

        if (res == 0) {
            LOGGER.warn("Deletion of computer with id" + id + "failed !\n The computer might not exists");
            return false;
        }

        return true;
    }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     * @throws PersistenceException : PersistenceException
     */
    public void update(Optional<Computer> computer) throws PersistenceException {
        if (!computer.isPresent()) {
            LOGGER.warn("You are trying to update a null computer !\n");
            return;
        }

        try {

            jdbcTemplateObject.update(DaoProperties.UPDATE_COMPUTER, computer.get().getName(), computer.get().getDateWichIsIntroduced(), computer.get().getDateWichIsDiscontinued(), computer.get().getManufacturer().getId(), computer.get().getId());

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     * @throws PersistenceException : PersistenceException
     */
    public List<Computer> findAll() throws PersistenceException {

        List<Computer> computers = null;

        try {

            computers = jdbcTemplateObject.query(DaoProperties.FIND_ALL_COMPUTERS, new MapperComputer());

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

        return computers;
    }

    /**
     * Return the number of computer in database.
     * @return long
     * @throws PersistenceException : PersistenceException
     */
    public long getCountOfElements() throws PersistenceException {

        long countOfElements = 0;

        try {

            countOfElements = jdbcTemplateObject.queryForObject(DaoProperties.COUNT_COMPUTER, long.class);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

        return countOfElements;
    }

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     * @throws PersistenceException : PersistenceException
     */
    public List<Computer> getComputersFiltered(String nameFilter) throws PersistenceException {

        List<Computer> computers = null;

        try {

            computers = jdbcTemplateObject.query("select computer.id, computer.name, computer.introduced, computer.discontinued,"
                    + " computer.company_id, company.name company_name from computer"
                    + " left join company on company.id = computer.company_id where company.id like '%" + nameFilter + "%';", new MapperComputer());

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

       return computers;

    }

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param pagination : page
     * @return list of computers paginated
     * @throws PersistenceException : PersistenceException
     */
    public List<Computer> findByPageFilter(Pagination pagination) throws PersistenceException {

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
            return jdbcTemplateObject.query("select computer.id, computer.name, computer.introduced, computer.discontinued,"
                    + " computer.company_id, company.name company_name from computer "
                    + "left join company on company.id = computer.company_id where computer.name like '%" + filter + "%' limit " + pageSize + " offset " + offset + ";", new MapperComputer());
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }
    }

}
