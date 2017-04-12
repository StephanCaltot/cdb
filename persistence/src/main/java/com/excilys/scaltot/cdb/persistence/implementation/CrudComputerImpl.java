package com.excilys.scaltot.cdb.persistence.implementation;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.scaltot.cdb.entities.Computer;
import com.excilys.scaltot.cdb.entities.QCompany;
import com.excilys.scaltot.cdb.entities.QComputer;
import com.excilys.scaltot.cdb.pagination.Pagination;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudComputer;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

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
    public final static int LIMIT_DEFAULT = 10;

    private static QCompany qCompany = QCompany.company;
    private static QComputer qComputer = QComputer.computer;
    
    private SessionFactory sessionFactory;

    private Supplier<HibernateQueryFactory> queryFactory =
            () -> new HibernateQueryFactory(sessionFactory.getCurrentSession());

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }    

    /**
     * Create CRUD's operation.
     *
     * @param computer : computer
     */
    public Optional<Computer> create(Optional<Computer> computer) {

        LOGGER.warn("Creating computer...");

        if (!computer.isPresent()) {
            throw new IllegalArgumentException("You are trying to create a null computer !");
        }

        sessionFactory.getCurrentSession().save(computer.get());
        
        return computer;

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
        
        return Optional.of(queryFactory.get().select(qComputer).from(qComputer).where(qComputer.id.eq(id)).fetchOne());
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

        queryFactory.get().delete(qComputer).where(qComputer.id.eq(id)).execute();

        return id;
    }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     * @return computer
     */
    public Optional<Computer> update(Optional<Computer> computer) {

        LOGGER.warn("Updating computer with id " + computer.get().getId() + "...");

        if (!computer.isPresent()) {
            throw new IllegalArgumentException("You are trying to update a null computer !");
        }

        queryFactory.get().update(qComputer)
        .where(qComputer.id.eq(computer.get().getId()))
        .set(qComputer.name, computer.get().getName())
        .set(qComputer.dateWichIsIntroduced, computer.get().getDateWichIsIntroduced())
        .set(qComputer.dateWichIsDiscontinued, computer.get().getDateWichIsDiscontinued())
        .set(qComputer.manufacturer, computer.get().getManufacturer())
        .execute();

        return computer;
    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfElements() {

        LOGGER.warn("Getting number of computer...");
        
        return (long) queryFactory.get().from(qComputer).fetchCount();
    }

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     */
    public List<Computer> getComputersFiltered(String nameFilter) {

        LOGGER.warn("Getting computer filtered by name beggining by " + nameFilter +  "...");

        return queryFactory.get()
            .select(qComputer)
            .from(qComputer)
            .leftJoin(qCompany)
            .on(qCompany.id.eq(qComputer.manufacturer.id))
            .where(qComputer.name.like(nameFilter + "%"))
            .fetch();

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
            pageSize = LIMIT_DEFAULT;
        }
        if (offset < 0) {
            offset = 0;
        }

        return queryFactory.get()
            .select(qComputer)
            .from(qComputer)
            .leftJoin(qCompany)
            .on(qCompany.id.eq(qComputer.manufacturer.id))
            .where(qComputer.name.like(filter + "%"))
            .limit(pageSize)
            .offset(offset)
            .fetch();
    }
}
