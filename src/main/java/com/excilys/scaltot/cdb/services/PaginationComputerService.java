package com.excilys.scaltot.cdb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.utils.Pagination;

@Repository
@Scope("singleton")
public class PaginationComputerService {

    @Autowired
    private CrudComputerService crudComputerService;

    /**
     * Initialize number of elements and pages.
     *
     * @param paginationComputer
     *            : page
     */
    public void paginationInitialisation(Pagination paginationComputer) {
        paginationComputer.setNumberOfElements(crudComputerService.getCountOfComputers());
        paginationComputer.setNumberOfPages(paginationComputer.getNumberOfElements() / paginationComputer.getPageSize());
    }

    /**
     * Retrieves list of computers paginated.
     *
     * @param paginationComputer
     *            : page
     * @return list of computers
     */
    public List<Computer> findByPage(Pagination paginationComputer) {
        return crudComputerService.findByPageFilter(paginationComputer);
    }

    /**
     * Switch to the next page.
     *
     * @param paginationComputer
     *            : page
     */
    public void nextPage(Pagination paginationComputer) {
        paginationComputer.nextPage();
    }

    /**
     * Switch to the previous page.
     *
     * @param paginationComputer
     *            : page
     */
    public void previousPage(Pagination paginationComputer) {
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
    public void setCurrentPage(Pagination paginationComputer, int numOfPage) {
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
    public void setPageSize(Pagination paginationComputer, int pageSize) {
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
    public void setFilter(Pagination paginationComputer, String filter) {
        paginationComputer.setFilter(filter);
    }
}
