package com.excilys.scaltot.cdb.services;

import java.util.List;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.persistence.impl.CrudCompanyImpl;
import com.excilys.scaltot.cdb.utils.Pagination;

public enum PaginationCompanyService {

    INSTANCE;

    /**
     * Initialize number of elements and pages.
     *
     * @param paginationCompany
     *            : page
     */
    public static void paginationInitialisation(Pagination paginationCompany) {
        paginationCompany.setNumberOfElements(CrudCompanyService.INSTANCE.getCountOfCompanies());
        paginationCompany.setNumberOfPages(paginationCompany.getNumberOfElements() / paginationCompany.getPageSize());
    }

    /**
     * Retrieves list of companies paginated.
     *
     * @param paginationCompany
     *            : page
     * @return list of companies
     */
    public static List<Company> findByPage(Pagination paginationCompany) {
        return CrudCompanyImpl.INSTANCE.findByPageFilter(paginationCompany);
    }

    /**
     * Switch to the next page.
     *
     * @param paginationCompany
     *            : page
     */
    public static void nextPage(Pagination paginationCompany) {
        paginationCompany.nextPage();
    }

    /**
     * Switch to the previous page.
     *
     * @param paginationCompany
     *            : page
     */
    public static void previousPage(Pagination paginationCompany) {
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
    public static void setCurrentPage(Pagination paginationCompany, int numOfPage) {
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
    public static void setPageSize(Pagination paginationCompany, int pageSize) {
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
    public static void setFilter(Pagination paginationCompany, String filter) {
        paginationCompany.setFilter(filter);
    }
}
