    package com.excilys.scaltot.cdb.persistence.implementation;

import java.util.ArrayList;
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
     */
    public void create(Optional<Computer> computer) {

        LOGGER.warn("Creating computer...");

        if (!computer.isPresent()) {
            throw new IllegalArgumentException("You are trying to create a null computer !");
        }

        try {

            jdbcTemplateObject.update(DaoProperties.CREATE_COMPUTER,
                    computer.get().getName(),
                    computer.get().getDateWichIsIntroduced(),
                    computer.get().getDateWichIsDiscontinued(),
                    computer.get().getManufacturer().getId());

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }
    }

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     * @return computer entity find with id gave in parameter
     */
    public Optional<Computer> find(long id) {

        LOGGER.warn("Finding computer with id " + id + "...");

        if (id <= 0) {
            throw new IllegalArgumentException("You are trying to find a computer with null or negative id !");
        }

        Computer computer = null;

        try {

            computer = jdbcTemplateObject.queryForObject(DaoProperties.FIND_COMPUTER, new MapperComputer(), id);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

        return Optional.ofNullable(computer);
    }

    /**
     * Delete CRUD's operation.
     *
     * @param id : id
     * @return long id
     */
    public long delete(long id) {

        LOGGER.warn("Deleting computer with id" + id + "...");

        if (id <= 0) {
            throw new IllegalArgumentException("You are trying to delete a computer with null or negative id !");
        }

        try {

            jdbcTemplateObject.update(DaoProperties.DELETE_COMPUTER, id);

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

        return id;
    }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     * @return computer
     */
    public Optional<Computer> update(Optional<Computer> computer) {

        LOGGER.warn("Updating computer with id" + computer.get().getId() + "...");

        if (!computer.isPresent()) {
            throw new IllegalArgumentException("You are trying to update a null computer !");
        }

        try {

            jdbcTemplateObject.update(DaoProperties.UPDATE_COMPUTER,
                    computer.get().getName(),
                    computer.get().getDateWichIsIntroduced(),
                    computer.get().getDateWichIsDiscontinued(),
                    computer.get().getManufacturer().getId(),
                    computer.get().getId());

        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }

        return computer;
    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     */
    public List<Computer> findAll() {

        LOGGER.warn("Doing find all computer...");

        List<Computer> computers = new ArrayList<>();

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
     */
    public long getCountOfElements() {

        LOGGER.warn("Getting number of computer...");

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
     */
    public List<Computer> getComputersFiltered(String nameFilter) {

        LOGGER.warn("Getting computer filtered by name beggining by " + nameFilter +  "...");

        List<Computer> computers = new ArrayList<>();

        try {

            computers = jdbcTemplateObject.query(DaoProperties.COMPUTER_FILTERED, new MapperComputer(),  nameFilter + "%");

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
     */
    public List<Computer> findByPageFilter(Pagination pagination) {

        LOGGER.warn("Getting computer paginated and filtered by name beggining by " + pagination.getFilter() +  "...");

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
            return jdbcTemplateObject.query(DaoProperties.PAGE_COMPUTER_FILTERED, new MapperComputer(), filter + "%", pageSize, offset);
        } catch (DataAccessException dataAccessException) {
            throw new PersistenceException();
        }
    }
}
