package com.excilys.computerdatabase.entities.computer;


import java.time.LocalDate;
import java.util.logging.Logger;
import com.excilys.computerdatabase.entities.company.Company;


/**
 * Class representing a Computer implementing IComputer
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class Computer {
	
	
	private final static Logger LOGGER = Logger.getLogger(Computer.class.getName());
	private long id;
	private String name;
	private LocalDate dateWichIsIntroduced;
	private LocalDate dateWichIsDiscontinued;
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
	public void setId(long id) {
		this.id =  id;
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
	public void setName(String name){
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
	    
	    public Builder withDateWichIsIntroduced (LocalDate dateWichIsIntroduced) {
	    	this.computer.dateWichIsIntroduced = dateWichIsIntroduced;
	    	return this;
	    }

	    public Builder withDateWichIsDiscontinued (LocalDate dateWichIsDiscontinued) {
	    	this.computer.dateWichIsIntroduced = dateWichIsDiscontinued;
	    	return this;
	    }

	    public Builder withManufacturer (Company manufacturer) {
	    	this.computer.manufacturer = manufacturer;
	    	return this;
	    }
	    
	    public Builder withId (long id) {
	    	this.computer.id = id;
	    	return this;
	    }
	    
	    
	    public Builder withName (String name) {
	    	this.computer.name = name;
	    	return this;
	    }
	    
	    
	    public Computer build() {
	    	
	    	return computer;
	    	
//	    	if ( ComputerValidator.check(computer) ) {
//	    		return computer;
//	    	}
//	    	
//	    	else {
//	    		LOGGER.warning("COMPUTER BUILDER RETURN NULL OBJECT");
//	    		computer = null;
//	    		return computer;
//	    	}

	    	
	    }
	}
	
}
