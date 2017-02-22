package com.excilys.computerdatabase.entities.computer;


import java.util.Date;

import com.excilys.computerdatabase.entities.company.Company;


/**
 * Class representing a Computer implementing IComputer
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class Computer {
	
	private long id;
	private String name;
	private Date dateWichIsIntroduced;
	private Date dateWichIsDiscontinued;
	private Company manufacturer;

	
	/**
	 * 
	 * Private constructor with no parameters 
	 * @param computerBuilder
	 */
	private Computer () {
	}


	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	

	/**
	 * 
	 * @param pId : the id to set
	 */
	public void setId(long pId) {
		this.id =  pId;
	}
	
	
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	

	/**
	 * @param pName : the name to set
	 */
	public void setName(String pName){
		this.name = pName;
	}

	
	
	/**
	 * @return the dateWichIsIntroduced
	 */
	public Date getDateWichIsIntroduced() {
		return dateWichIsIntroduced;
	}

	

	/**
	 * @param dateWichIsIntroduced : the dateWichIsIntroduced to set
	 */
	public void setDateWichIsIntroduced(Date dateWichIsIntroduced) {
		this.dateWichIsIntroduced = dateWichIsIntroduced;
	}



	/**
	 * @return the dateWichIsDiscontinued
	 */
	public Date getDateWichIsDiscontinued() {
		return dateWichIsDiscontinued;
	}

	

	/**
	 * @param dateWichIsDiscontinued : the dateWichIsDiscontinued to set
	 */
	public void setDateWichIsDiscontinued(Date dateWichIsDiscontinued) {
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
	 * Displays one "Computer" entity
	 */
	@Override
	public String toString() {
		return "Computer (" + getId() + ") - " + getName()
			+ ((getDateWichIsIntroduced() == null) ? ", not introduced yet ":", introduced in " + getDateWichIsIntroduced())
			+ ((getDateWichIsIntroduced() == null) ? ", not discontinued yet ":", diconstinued in " + getDateWichIsIntroduced())
		    + ((getManufacturer() == null) ? ". No company available": ". Manufactured by company number " + getManufacturer().getId() + " .");
		}
	

	
	
	
	
	/**
	 * Builder Pattern for computer
	 * @author screetts
	 */
	public static class Builder {
		
		private Computer computer;
		
	    public Builder() {
	      this.computer = new Computer();
	    }
	    
	    public Builder withDateWichIsIntroduced (Date dateWichIsIntroduced){
	    	this.computer.dateWichIsIntroduced = dateWichIsIntroduced;
	    	return this;
	    }

	    public Builder withDateWichIsDiscontinued (Date dateWichIsDiscontinued){
	    	this.computer.dateWichIsIntroduced = dateWichIsDiscontinued;
	    	return this;
	    }

	    public Builder withManufacturer (Company manufacturer){
	    	this.computer.manufacturer = manufacturer;
	    	return this;
	    }
	    
	    public Builder withId (long id){
	    	this.computer.id = id;
	    	return this;
	    }
	    
	    
	    public Builder withName (String name){
	    	this.computer.name = name;
	    	return this;
	    }
	    
	    
	    public Computer build() throws Exception {
	    	
	    	ComputerValidator.check(computer);
	    	
	    	return computer;
	    }
	}
	
}
