package com.excilys.scaltot.cdb.entities.company;

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
}
