package com.excilys.scaltot.cdb.managers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.repository.Pagination;
import com.excilys.scaltot.cdb.services.CrudComputerService;

/**
 * @author Caltot Stéphan
 *
 *         28 févr. 2017
 */

public class ComputerManagerBean {

    public static final Logger LOGGER = LoggerFactory.getLogger(ComputerManagerBean.class);
    private int numberForEachPage = 10;
    private int currentPage = 0;
    private long numberOfComputers;
    private String filter = "";

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage * numberForEachPage;
    }

    /**
     * Constructor for computer manager bean.
     */
    public ComputerManagerBean() {
    }

    public int getNumberForEachPage() {
        return numberForEachPage;
    }

    public void setNumberForEachPage(int numberForEachPage) {
        this.numberForEachPage = numberForEachPage;
    }

    /**
     * Return numbers of computers.
     * @return long
     */
    public long getNbComputers() {
        numberOfComputers = CrudComputerService.getCountOfComputers();
        return numberOfComputers;
    }

    /**
     * Return list of computers paginated.
     *
     * @return list of computers
     */
    public List<Computer> getDisplayedComputers(Pagination pagination) {
        return CrudComputerService.findByPageFilter(pagination);
    }

    /**
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param filter : the filter to set
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * @return the numberOfPages
     */
    public long getNumberOfPages() {
        return numberOfComputers  / numberForEachPage;
    }
}
