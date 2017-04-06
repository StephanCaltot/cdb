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

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.company.QCompany;
import com.excilys.scaltot.cdb.entities.computer.QComputer;
import com.excilys.scaltot.cdb.pagination.Pagination;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudCompany;
import com.excilys.scaltot.cdb.persistence.utils.CrudServiceConstant;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

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

        return Optional.of((Company) queryFactory.get().from(qCompany).where(qCompany.id.eq(id)).fetchOne());
    }

    /**
     * Retrieves all companies.
     * @return List contains all companies
     */
    public List<Company> findAll() {

        LOGGER.warn("Doing find all company...");

        return (List<Company>) queryFactory.get().selectFrom(qCompany).fetch();

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
        return queryFactory
                .get()
                .select(qCompany)
                .from(qCompany)
                .where(qCompany.name.like(filter))
                .limit(pageSize)
                .offset(offset)
                .fetch();

    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfElements() {

        LOGGER.warn("Getting number of company...");

        return (long) queryFactory.get().from(qCompany).fetchCount();
        
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
        
        queryFactory.get().delete(qComputer).where(qComputer.manufacturer.id.eq(id)).execute();
        queryFactory.get().delete(qCompany).where(qCompany.id.eq(id)).execute();

        return id;

    }


}