package com.excilys.scaltot.cdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.services.interfaces.PaginationService;
import com.excilys.scaltot.cdb.utils.Pagination;

@Service
@Scope("singleton")
public class PaginationServiceImpl implements PaginationService {

    @Autowired
    private CrudCompanyServiceImpl crudCompanyServiceImpl;
    
    @Autowired
    private CrudComputerServiceImpl crudComputerServiceImpl;

    /**
     * Initialize number of elements and pages.
     *
     * @param pagination : page
     */
    public void paginationInitialisation(Pagination pagination) {
        pagination.setNumberOfElements(crudCompanyServiceImpl.getCountOfCompanies());
        pagination.setNumberOfPages(pagination.getNumberOfElements() / pagination.getPageSize());
    }

    /**
     * Retrieves list of companies paginated.
     *
     * @param pagination : page
     * @return list of companies
     */
    public List<Company> findCompanyByPage(Pagination pagination) {
        return crudCompanyServiceImpl.findByPage(pagination);
    }

    /**
     * Retrieves list of computers paginated.
     *
     * @param pagination : page
     * @return list of computers
     */
    public List<Computer> findComputerByPage(Pagination pagination) {
        return crudComputerServiceImpl.findByPageFilter(pagination);
    }
    
    /**
     * Switch to the next page.
     *
     * @param pagination : page
     */
    public void nextPage(Pagination pagination) {
        pagination.nextPage();
    }

    /**
     * Switch to the previous page.
     *
     * @param pagination : page
     */
    public void previousPage(Pagination pagination) {
        pagination.previousPage();
    }

    /**
     * Set the current page.
     *
     * @param pagination : page
     * @param numOfPage : number of current page
     */
    public void setCurrentPage(Pagination pagination, int numOfPage) {
        pagination.setCurrentPage(numOfPage);
    }

    /**
     * Set the size of page.
     *
     * @param pagination : page
     * @param pageSize : size of page
     */
    public void setPageSize(Pagination pagination, int pageSize) {
        pagination.setPageSize(pageSize);
    }

    /**
     * Set the filter for the search.
     *
     * @param pagination : page
     * @param filter : filter
     */
    public void setFilter(Pagination pagination, String filter) {
        pagination.setFilter(filter);
    }
}
