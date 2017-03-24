package com.excilys.scaltot.cdb.services.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudCompany;
import com.excilys.scaltot.cdb.services.interfaces.CrudCompanyService;
import com.excilys.scaltot.cdb.utils.Pagination;

@Service
@Scope("singleton")
@Transactional
public class CrudCompanyServiceImpl implements CrudCompanyService {

    @Autowired
    private CrudCompany crudCompanyImpl;

    /**
     * Return company find by id.
     * @param id : id
     * @return company
     * @throws SQLException
     */
    public Optional<Company> find(long id) {
        try {
            return crudCompanyImpl.find(id);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Return all companies.
     * @return list of companies
     */
    public List<Company> findAll() {
        try {
            return crudCompanyImpl.findAll();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Return list of companies paginated.
     * @param pagination : page
     * @return list of companies
     */
    public List<Company> findByPage(Pagination pagination) {
        try {
            return crudCompanyImpl.findByPageFilter(pagination);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Return number of companies.
     * @return long
     */
    public long getCountOfCompanies() {
        try {
            return crudCompanyImpl.getCountOfElements();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Delete one company with computer associated.
     * @param companyId : id of company
     * @return boolean
     */
    public boolean delete(long companyId) {
         try {
            if (!crudCompanyImpl.delete(companyId)) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }
}
