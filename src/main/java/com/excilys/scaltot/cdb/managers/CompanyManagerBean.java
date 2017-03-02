package com.excilys.scaltot.cdb.managers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.services.CrudCompanyService;

/**
 * @author Caltot Stéphan
 *
 * 1 mars 2017
 */
public class CompanyManagerBean {
    public static final Logger LOGGER = LoggerFactory.getLogger(CompanyManagerBean.class);
    private int numberForEachPage = 10;
    private int currentPage = 0;
    private long numberOfCompanies;

    /**
     * @return the numberOfComputers
     */
    public long getNumberOfComputers() {
        return numberOfCompanies;
    }

    /**
     * @param numberOfCompanies : the numberOfCompanies to set
     */
    public void setNumberOfComputers(long numberOfCompanies) {
        this.numberOfCompanies = numberOfCompanies;
    }

    /**
     * @return the currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage : the currentPage to set
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return the numberForEachPage
     */
    public int getNumberForEachPage() {
        return numberForEachPage;
    }

    /**
     * @param numberForEachPage : the numberForEachPage to set
     */
    public void setNumberForEachPage(int numberForEachPage) {
        this.numberForEachPage = numberForEachPage;
    }

    /**
     * Return numbers of companies.
     * @return long
     */
    public long getNbCompanies() {
        numberOfCompanies = CrudCompanyService.getCountOfCompanies();
        System.out.println(numberOfCompanies);
        return numberOfCompanies;
    }

    /**
     * @return the numberOfPages
     */
    public long getNumberOfPages() {
        return numberOfCompanies  / numberForEachPage;
    }

    /**
     * Return list of companies.
     *
     * @return list of companies
     */
    public List<Company> getDisplayedCompanies() {
        return CrudCompanyService.findAll();
    }
}
