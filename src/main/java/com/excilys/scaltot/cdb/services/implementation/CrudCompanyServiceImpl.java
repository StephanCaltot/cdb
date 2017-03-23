package com.excilys.scaltot.cdb.services.implementation;

import java.sql.Connection;
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
import com.excilys.scaltot.cdb.utils.DatabaseManager;
import com.excilys.scaltot.cdb.utils.Pagination;

@Service
@Scope("singleton")
@Transactional
public class CrudCompanyServiceImpl implements CrudCompanyService {

    @Autowired
    private CrudCompany crudCompanyImpl;
    private Connection connection;
    @Autowired
    private DatabaseManager databaseManager;

    /**
     * Return company find by id.
     * @param id : id
     * @return company
     * @throws SQLException 
     */
    public Optional<Company> find(long id) {
        connection = databaseManager.getConnection();
        try {
            return crudCompanyImpl.find(id, connection);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
    }

    /**
     * Return all companies.
     * @return list of companies
     */
    public List<Company> findAll() {
        connection = databaseManager.getConnection();
        try {
            return crudCompanyImpl.findAll(connection);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
    }

    /**
     * Return list of companies paginated.
     * @param pagination : page
     * @return list of companies
     */
    public List<Company> findByPage(Pagination pagination) {
        connection = databaseManager.getConnection();
        try {
            return crudCompanyImpl.findByPageFilter(pagination, connection);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
    }

    /**
     * Return number of companies.
     * @return long
     */
    public long getCountOfCompanies() {
        connection = databaseManager.getConnection();
        try {
            return crudCompanyImpl.getCountOfElements(connection);
        } catch (SQLException e) {
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
    }

    /**
     * Delete one company with computer associated.
     * @param companyId : id of company
     * @return boolean
     */
    public boolean delete(long companyId) {
        connection = databaseManager.getConnection();
         try {
            if (!crudCompanyImpl.delete(companyId, connection)) {
                return false;
            }
            databaseManager.commit();
            return true;
        } catch (SQLException e) {
            databaseManager.rollback();
            throw new PersistenceException(e);
        } finally {
            databaseManager.closeConnection();
        }
    }
}
