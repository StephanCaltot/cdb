package com.excilys.scaltot.cdb.entities.computer;

import java.time.LocalDate;

import com.excilys.scaltot.cdb.entities.company.Company;

/**
 * @author Caltot Stéphan
 *
 * 1 mars 2017
 */
public class ComputerDto {

    private long id;
    private String name;
    private LocalDate dateWichIsIntroduced;
    private LocalDate dateWichIsDiscontinued;
    private Company company;

    /**
     * @return the company
     */
    public Company getCompany() {
        return company;
    }
    /**
     * @param company : the company to set
     */
    public void setCompany(Company company) {
        this.company = company;
    }
    /**
     * @return the dateWichIsDiscontinued
     */
    public LocalDate getDateWichIsDiscontinued() {
        return dateWichIsDiscontinued;
    }
    /**
     * @param dateWichIsDiscontinued : the dateWichIsDiscontinued to set
     */
    public void setDateWichIsDiscontinued(LocalDate dateWichIsDiscontinued) {
        this.dateWichIsDiscontinued = dateWichIsDiscontinued;
    }
    /**
     * @return the dateWichIsIntroduced
     */
    public LocalDate getDateWichIsIntroduced() {
        return dateWichIsIntroduced;
    }
    /**
     * @param dateWichIsIntroduced : the dateWichIsIntroduced to set
     */
    public void setDateWichIsIntroduced(LocalDate dateWichIsIntroduced) {
        this.dateWichIsIntroduced = dateWichIsIntroduced;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name : the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    /**
     * @param id : the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
}
