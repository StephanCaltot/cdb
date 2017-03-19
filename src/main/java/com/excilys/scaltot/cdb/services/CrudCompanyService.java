package com.excilys.scaltot.cdb.services;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.repository.Pagination;
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
     * @param pagination : page
     * @return list of companies
     */
    public List<Company> findByPage(Pagination pagination) {
        return CrudServiceCompanyImpl.INSTANCE.findByPageFilter(pagination);
    }

    /**
     * Return number of companies.
     * @return long
     */
    public static long getCountOfCompanies() {
        return CrudServiceCompanyImpl.INSTANCE.getCountOfCompanies();
    }

    /**
     * Delete one company with computer associated.
     * @param companyId
     * @return boolean
     */
    public static boolean delete(long companyId){
    	return CrudServiceCompanyImpl.INSTANCE.delete(companyId);
    }
}
