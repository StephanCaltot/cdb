package com.excilys.scaltot.cdb.services;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.repository.impl.CrudServiceCompanyImpl;

public class CrudCompanyService {

    /**
     * Return company find by id.
     * @param id : id
     * @return company
     */
    public static Optional<Company> find(long id) {
        return CrudServiceCompanyImpl.INSTANCE.find(id);
    }

    /**
     * Return all companies.
     * @return list of companies
     */
    public static List<Company> findAll() {
        return CrudServiceCompanyImpl.INSTANCE.findAll();
    }

    /**
     * Return list of companies paginated.
     * @param offset :offset
     * @param numberForEachPage : numberForEachPage
     * @return list of companies
     */
    public static List<Company> findByPage(long offset, long numberForEachPage) {
        return CrudServiceCompanyImpl.INSTANCE.findByPage(offset, numberForEachPage);
    }

    /**
     * Return number of companies.
     * @return long
     */
    public static long getCountOfCompanies() {
        return CrudServiceCompanyImpl.INSTANCE.getCountOfCompanies();
    }
}
