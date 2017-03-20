package com.excilys.scaltot.cdb.entities.computer;

/**
 * @author Caltot Stéphan
 *
 * 1 mars 2017
 */
public class ComputerDto {

    private long id;
    private String name;
    private String dateWichIsIntroduced;
    private String dateWichIsDiscontinued;
    private String companyName;
    private long companyId;


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

    /**
     * @return the dateWichIsIntroduced
     */
    public String getDateWichIsIntroduced() {
        return dateWichIsIntroduced;
    }

    /**
     * @param dateWichIsIntroduced : the dateWichIsIntroduced to set
     */
    public void setDateWichIsIntroduced(String dateWichIsIntroduced) {
        this.dateWichIsIntroduced = dateWichIsIntroduced;
    }

    /**
     * @return the dateWichIsDiscontinued
     */
    public String getDateWichIsDiscontinued() {
        return dateWichIsDiscontinued;
    }

    /**
     * @param dateWichIsDiscontinued : the dateWichIsDiscontinued to set
     */
    public void setDateWichIsDiscontinued(String dateWichIsDiscontinued) {
        this.dateWichIsDiscontinued = dateWichIsDiscontinued;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName : the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the companyId
     */
    public long getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId : the companyId to set
     */
    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
}
