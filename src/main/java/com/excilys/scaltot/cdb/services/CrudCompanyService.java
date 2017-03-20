package com.excilys.scaltot.cdb.services;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.persistence.impl.CrudCompanyImpl;
import com.excilys.scaltot.cdb.utils.Pagination;

public enum CrudCompanyService {

    INSTANCE;

    /**
     * Return company find by id.
     * @param id : id
     * @return company
     */
    public Optional<Company> find(long id) {
        return CrudCompanyImpl.INSTANCE.find(id);
    }

    /**
     * Return all companies.
     * @return list of companies
     */
    public List<Company> findAll() {
        return CrudCompanyImpl.INSTANCE.findAll();
    }

    /**
     * Return list of companies paginated.
     * @param pagination : page
     * @return list of companies
     */
    public List<Company> findByPage(Pagination pagination) {
        return CrudCompanyImpl.INSTANCE.findByPageFilter(pagination);
    }

    /**
     * Return number of companies.
     * @return long
     */
    public long getCountOfCompanies() {
        return CrudCompanyImpl.INSTANCE.getCountOfElements();
    }

    /**
     * Delete one company with computer associated.
     * @param companyId : id of company
     * @return boolean
     */
    public boolean delete(long companyId) {
        return CrudCompanyImpl.INSTANCE.delete(companyId);
    }
}
