package com.excilys.scaltot.cdb.services.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.Company;
import com.excilys.scaltot.cdb.pagination.Pagination;

/**
 * @author Caltot Stéphan
 *
 * 22 mars 2017
 */
public interface CrudCompanyService {

    /**
     * Retrieves one company by id.
     * @param id : id of company
     * @return company with id gave a parameter
     * @throws SQLException : SQLException
     */
    Optional<Company> find(long id) throws SQLException;

    /**
     * Retrieves all companies.
     * @return list of company
     */
    List<Company> findAll();

    /**
     * Retrieves all companies paginated.
     * @param pagination : pagination
     * @return list of company paginated
     */
    List<Company> findByPage(Pagination pagination);

    /**
     * Get number of all companies.
     * @return number of companies
     */
    long getCountOfCompanies();

    /**
     * Delete a company.
     * @param companyId : id of company
     * @return false if companyId doesn't exist , true if deletion was done.
     */
    long delete(long companyId);
}
