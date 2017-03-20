package com.excilys.scaltot.cdb.services;

import java.util.List;

import com.excilys.scaltot.cdb.dao.impl.CrudComputerImpl;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.utils.Pagination;

public enum PaginationComputerService {

    INSTANCE;

    /**
     * Initialize number of elements and pages.
     *
     * @param paginationComputer
     *            : page
     */
    public static void paginationInitialisation(Pagination paginationComputer) {
        paginationComputer.setNumberOfElements(CrudComputerService.INSTANCE.getCountOfComputers());
        paginationComputer
                .setNumberOfPages(paginationComputer.getNumberOfElements() / paginationComputer.getPageSize());
    }

    /**
     * Retrieves list of computers paginated.
     *
     * @param paginationComputer
     *            : page
     * @return list of computers
     */
    public static List<Computer> findByPage(Pagination paginationComputer) {
        return CrudComputerImpl.INSTANCE.findByPageFilter(paginationComputer);
    }

    /**
     * Switch to the next page.
     *
     * @param paginationComputer
     *            : page
     */
    public static void nextPage(Pagination paginationComputer) {
        paginationComputer.nextPage();
    }

    /**
     * Switch to the previous page.
     *
     * @param paginationComputer
     *            : page
     */
    public static void previousPage(Pagination paginationComputer) {
        paginationComputer.previousPage();
    }

    /**
     * Set the current page.
     *
     * @param paginationComputer
     *            : page
     * @param numOfPage
     *            : number of current page
     */
    public static void setCurrentPage(Pagination paginationComputer, int numOfPage) {
        paginationComputer.setCurrentPage(numOfPage);
    }

    /**
     * Set the size of page.
     *
     * @param paginationComputer
     *            : page
     * @param pageSize
     *            : size of page
     */
    public static void setPageSize(Pagination paginationComputer, int pageSize) {
        paginationComputer.setPageSize(pageSize);
    }

    /**
     * Set the filter for the search.
     *
     * @param paginationComputer
     *            : page
     * @param filter
     *            : filter
     */
    public static void setFilter(Pagination paginationComputer, String filter) {
        paginationComputer.setFilter(filter);
    }
}
