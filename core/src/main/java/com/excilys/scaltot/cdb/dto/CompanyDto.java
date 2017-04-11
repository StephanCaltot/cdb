package com.excilys.scaltot.cdb.dto;

/**
 * @author Caltot Stéphan
 *
 * 1 mars 2017
 */
public class CompanyDto {

    private long id;
    private String name;

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
     * Displays one "Company" entity.
     */
    @Override
    public String toString() {
        return "Company " + getId() + " and named " + getName();
    }

    /**
     * hashCode method for CompanyDTO.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * Equals method for CompanyDTO.
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
        CompanyDto other = (CompanyDto) obj;
        if (id != other.id) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
}
