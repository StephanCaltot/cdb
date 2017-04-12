package com.excilys.scaltot.cdb.dto;

import java.io.Serializable;

/**
 * @author Caltot Stéphan
 *
 * 1 mars 2017
 */
public class ComputerDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private long id;
    private String computerName;
    private String dateWichIsIntroduced;
    private String dateWichIsDiscontinued;
    private String companyName;
    private long companyId;


    /**
     * @return the name
     */
    public String getComputerName() {
        return computerName;
    }
    /**
     * @param computerName : the name to set
     */
    public void setComputerName(String computerName) {
        this.computerName = computerName;
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

    /**
     * Displays one "Computer" entity.
     */
    @Override
    public String toString() {
        return "Computer (" + getId() + ") - " + getComputerName()
                + ((getDateWichIsIntroduced() == null) ? ", not introduced yet "
                        : ", introduced in " + getDateWichIsIntroduced())
                + ((getDateWichIsDiscontinued() == null) ? ", not discontinued yet "
                        : ", diconstinued in " + getDateWichIsDiscontinued())
                + ((getCompanyId() == 0) ? ". No company available"
                        : ". Manufactured by company number " + getCompanyId() + " named " + getCompanyName() + " .");
    }

    /**
     * HashCode method for Computer DTO.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (companyId ^ (companyId >>> 32));
        result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
        result = prime * result + ((dateWichIsDiscontinued == null) ? 0 : dateWichIsDiscontinued.hashCode());
        result = prime * result + ((dateWichIsIntroduced == null) ? 0 : dateWichIsIntroduced.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((computerName == null) ? 0 : computerName.hashCode());
        return result;
    }

    /**
     * Equals method for Computer DTO.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ComputerDto other = (ComputerDto) obj;
        if (companyId != other.companyId) {
            return false;
        }
        if (companyName == null) {
            if (other.companyName != null) {
                return false;
            }
        } else if (!companyName.equals(other.companyName)) {
            return false;
        }
        if (dateWichIsDiscontinued == null) {
            if (other.dateWichIsDiscontinued != null) {
                return false;
            }
        } else if (!dateWichIsDiscontinued.equals(other.dateWichIsDiscontinued)) {
            return false;
        }
        if (dateWichIsIntroduced == null) {
            if (other.dateWichIsIntroduced != null) {
                return false;
            }
        } else if (!dateWichIsIntroduced.equals(other.dateWichIsIntroduced)) {
            return false;
        }
        if (id != other.id) {
            return false;
        }
        if (computerName == null) {
            if (other.computerName != null) {
                return false;
            }
        } else if (!computerName.equals(other.computerName)) {
            return false;
        }
        return true;
    }
}
