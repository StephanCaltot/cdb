package com.excilys.scaltot.cdb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.persistence.impl.CrudCompanyImpl;
import com.excilys.scaltot.cdb.spring.BeanConfig;
import com.excilys.scaltot.cdb.utils.Pagination;

@Service
public class CrudCompanyService {

    @Autowired
    private CrudCompanyImpl crudCompanyImpl;// = new AnnotationConfigApplicationContext(BeanConfig.class).getBean(CrudCompanyImpl.class);

    /**
     * Return company find by id.
     * @param id : id
     * @return company
     */
    public Optional<Company> find(long id) {
        return crudCompanyImpl.find(id);
    }

    /**
     * Return all companies.
     * @return list of companies
     */
    public List<Company> findAll() {
        return crudCompanyImpl.findAll();
    }

    /**
     * Return list of companies paginated.
     * @param pagination : page
     * @return list of companies
     */
    public List<Company> findByPage(Pagination pagination) {
        return crudCompanyImpl.findByPageFilter(pagination);
    }

    /**
     * Return number of companies.
     * @return long
     */
    public long getCountOfCompanies() {
        return crudCompanyImpl.getCountOfElements();
    }

    /**
     * Delete one company with computer associated.
     * @param companyId : id of company
     * @return boolean
     */
    public boolean delete(long companyId) {
        return crudCompanyImpl.delete(companyId);
    }
}
