package com.excilys.scaltot.cdb.services.interfaces;

import java.util.List;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.pagination.Pagination;

/**
 * @author Caltot Stéphan
 *
 * 22 mars 2017
 */
public interface PaginationService {

    /**
     * Initialize number of elements and pages depending of type Company, or Computer.
     *
     * @param pagination : page
     * @param classe : class type
     * @return Pagination page
     */
    Pagination paginationInitialisation(Pagination pagination, Class<?> classe);

    /**
     * Retrieves list of companies paginated.
     *
     * @param pagination : page
     * @return list of companies
     */
    List<Company> findCompanyByPage(Pagination pagination);

    /**
     * Retrieves list of computers paginated.
     *
     * @param pagination : page
     * @return list of computers
     */
    List<Computer> findComputerByPage(Pagination pagination);

    /**
     * Switch to the next page.
     *
     * @param pagination : page
     */
    void nextPage(Pagination pagination);

    /**
     * Switch to the previous page.
     *
     * @param pagination : page
     */
    void previousPage(Pagination pagination);

    /**
     * Set the current page.
     *
     * @param pagination : page
     * @param numOfPage : number of current page
     */
    void setCurrentPage(Pagination pagination, int numOfPage);

    /**
     * Set the size of page.
     *
     * @param pagination : page
     * @param pageSize : size of page
     */
    void setPageSize(Pagination pagination, int pageSize);

    /**
     * Set the filter for the search.
     *
     * @param pagination : page
     * @param filter : filter
     */
    void setFilter(Pagination pagination, String filter);
}
