package com.excilys.scaltot.cdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.utils.Pagination;

@Service
@Scope("singleton")
public class PaginationCompanyService {

    @Autowired
    private CrudCompanyService crudCompanyService;

    /**
     * Initialize number of elements and pages.
     *
     * @param paginationCompany
     *            : page
     */
    public void paginationInitialisation(Pagination paginationCompany) {
        paginationCompany.setNumberOfElements(crudCompanyService.getCountOfCompanies());
        paginationCompany.setNumberOfPages(paginationCompany.getNumberOfElements() / paginationCompany.getPageSize());
    }

    /**
     * Retrieves list of companies paginated.
     *
     * @param paginationCompany
     *            : page
     * @return list of companies
     */
    public List<Company> findByPage(Pagination paginationCompany) {
        return crudCompanyService.findByPage(paginationCompany);
    }

    /**
     * Switch to the next page.
     *
     * @param paginationCompany
     *            : page
     */
    public void nextPage(Pagination paginationCompany) {
        paginationCompany.nextPage();
    }

    /**
     * Switch to the previous page.
     *
     * @param paginationCompany
     *            : page
     */
    public void previousPage(Pagination paginationCompany) {
        paginationCompany.previousPage();
    }

    /**
     * Set the current page.
     *
     * @param paginationCompany
     *            : page
     * @param numOfPage
     *            : number of current page
     */
    public void setCurrentPage(Pagination paginationCompany, int numOfPage) {
        paginationCompany.setCurrentPage(numOfPage);
    }

    /**
     * Set the size of page.
     *
     * @param paginationCompany
     *            : page
     * @param pageSize
     *            : size of page
     */
    public void setPageSize(Pagination paginationCompany, int pageSize) {
        paginationCompany.setPageSize(pageSize);
    }

    /**
     * Set the filter for the search.
     *
     * @param paginationCompany
     *            : page
     * @param filter
     *            : filter
     */
    public void setFilter(Pagination paginationCompany, String filter) {
        paginationCompany.setFilter(filter);
    }
}
