package com.excilys.scaltot.cdb.entities.computer;

import java.time.LocalDate;
import com.excilys.scaltot.cdb.entities.company.Company;

/**
 * Class representing a Computer implementing IComputer.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public class Computer {

    private long id;
    private String name;
    private LocalDate dateWichIsIntroduced;
    private LocalDate dateWichIsDiscontinued;
    private Company manufacturer;

    /**
     *
     * Private constructor with no parameters.
     *
     * @param computerBuilder
     */
    private Computer() {
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id : the id to set
     */
    public void setId(long id) {
        this.id = id;
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
     * @return the manufacturer
     */
    public Company getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer : the manufacturer (company) to set
     */
    public void setManufacturer(Company manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Displays one "Computer" entity.
     */
    @Override
    public String toString() {
        return "Computer (" + getId() + ") - " + getName()
                + ((getDateWichIsIntroduced() == null) ? ", not introduced yet "
                        : ", introduced in " + getDateWichIsIntroduced())
                + ((getDateWichIsDiscontinued() == null) ? ", not discontinued yet "
                        : ", diconstinued in " + getDateWichIsDiscontinued())
                + ((getManufacturer() == null) ? ". No company available"
                        : ". Manufactured by company number " + getManufacturer().getId() + " .");
    }

    /**
     * Generates hash for computer entity.
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
        Computer other = (Computer) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * Builder Pattern for computer.
     *
     * @author screetts
     */
    public static class ComputerBuilder {

        private Computer computer;

        /**
         * Builder constructor.
         */
        public ComputerBuilder() {
            this.computer = new Computer();
        }

        /**
         * add introduced date method.
         * @param dateWichIsIntroduced :
         * @return builder
         */
        public ComputerBuilder withDateWichIsIntroduced(LocalDate dateWichIsIntroduced) {
            this.computer.dateWichIsIntroduced = dateWichIsIntroduced;
            return this;
        }

        /**
         * add discontinued date method.
         * @param dateWichIsDiscontinued :
         * @return builder
         */
        public ComputerBuilder withDateWichIsDiscontinued(LocalDate dateWichIsDiscontinued) {
            this.computer.dateWichIsDiscontinued = dateWichIsDiscontinued;
            return this;
        }

        /**
         * add company method.
         * @param manufacturer :
         * @return builder
         */
        public ComputerBuilder withManufacturer(Company manufacturer) {
            this.computer.manufacturer = manufacturer;
            return this;
        }

        /**
         * add id method.
         * @param id :
         * @return builder
         */
        public ComputerBuilder withId(long id) {
            this.computer.id = id;
            return this;
        }
        /**
         * add name method.
         * @param name :
         * @return builder
         */
        public ComputerBuilder withName(String name) {
            this.computer.name = name;
            return this;
        }

        /**
         * build computer.
         * @return Computer
         */
        public Computer build() {

            return computer;

        }
    }

}
