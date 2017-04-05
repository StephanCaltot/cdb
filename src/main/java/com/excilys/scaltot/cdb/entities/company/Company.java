package com.excilys.scaltot.cdb.entities.company;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * Class representing a Company implementing ICompany.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */

@Table(schema = "company")
@TableGenerator(name="COMPANY_GEN",
        table="ID_GEN_COMPANY",
        pkColumnName="GEN_KEY",
        valueColumnName="GEN_VALUE",
        pkColumnValue="COMPANY_ID",
        allocationSize=1)
@Entity
public class Company implements Serializable {

    private static final long serialVersionUID = 5909107314153074415L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "COMPANY_GEN")
    private long id;
    private String name;

    /**
     * Private constructor with no parameters.
     */
    public Company() {

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
    public static class CompanyBuilder {

        private Company company;

        /**
         * Builder constructor.
         */
        public CompanyBuilder() {
            this.company = new Company();
        }

        /**
         *
         * @param id :
         * @return builder
         */
        public CompanyBuilder withId(long id) {
            this.company.id = id;
            return this;
        }

        /**
         *
         * @param name :
         * @return builder
         */
        public CompanyBuilder withName(String name) {
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
