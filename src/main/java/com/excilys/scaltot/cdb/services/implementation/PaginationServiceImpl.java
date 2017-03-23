package com.excilys.scaltot.cdb.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.services.interfaces.CrudCompanyService;
import com.excilys.scaltot.cdb.services.interfaces.CrudComputerService;
import com.excilys.scaltot.cdb.services.interfaces.PaginationService;
import com.excilys.scaltot.cdb.utils.Pagination;

@Service
@Scope("singleton")
public class PaginationServiceImpl implements PaginationService {

    @Autowired
    private CrudCompanyService crudCompanyServiceImpl;

    @Autowired
    private CrudComputerService crudComputerServiceImpl;

    /**
     * Initialize number of elements and pages.
     *
     * @param pagination : page
     * @param classe : class type
     */
    public Pagination paginationInitialisation(Pagination pagination, Class<?> classe) {
        if (classe.getSimpleName().equals("Computer")) {
            pagination.setNumberOfElements(crudComputerServiceImpl.getCountOfComputers());

        } else if (classe.getSimpleName().equals("Company")) {
            pagination.setNumberOfElements(crudCompanyServiceImpl.getCountOfCompanies());

        }
        pagination.setNumberOfPages(pagination.getNumberOfElements() / pagination.getPageSize());
        return pagination;
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
        long offset = pagination.getOffset();
        long currentPage = pagination.getCurrentPage();
        long pageSize = pagination.getPageSize();
        long numberOfPages = pagination.getNumberOfPages();

        currentPage = (currentPage + 1) <= numberOfPages ? (currentPage + 1) : numberOfPages;
        offset = currentPage * pageSize;

        pagination.setCurrentPage(currentPage);
        pagination.setOffset(offset);
    }

    /**
     * Switch to the previous page.
     *
     * @param pagination : page
     */
    public void previousPage(Pagination pagination) {
        long offset = pagination.getOffset();
        long currentPage = pagination.getCurrentPage();
        long pageSize = pagination.getPageSize();

        currentPage = (currentPage - 1) >= 0 ? (currentPage - 1) : 0;
        offset = currentPage * pageSize;

        pagination.setCurrentPage(currentPage);
        pagination.setOffset(offset);
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
