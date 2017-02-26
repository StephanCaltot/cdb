package com.excilys.scaltot.cdb.entities.company;

/**
 * Class representing a Company implementing ICompany.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public class Company {

    private long id;
    private String name;

    /**
     * Private constructor with no parameters.
     */
    private Company() {

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

    /**
     * Displays one "Company" entity.
     */
    @Override
    public String toString() {
        return "Company " + getId() + " and named " + getName();
    }

    /**
     * Generates hash for company entity.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    /**
     * Equals method on "id".
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
        Company other = (Company) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * Builder Pattern for company.
     *
     * @author screetts
     */
    public static class Builder {

        private Company company;

        /**
         * Builder constructor.
         */
        public Builder() {
            this.company = new Company();
        }

        /**
         *
         * @param id :
         * @return builder
         */
        public Builder withId(long id) {
            this.company.id = id;
            return this;
        }

        /**
         *
         * @param name :
         * @return builder
         */
        public Builder withName(String name) {
            this.company.name = name;
            return this;
        }
        /**
         *
         * @return builder
         */
        public Company build() {

            return company;

        }
    }
}
