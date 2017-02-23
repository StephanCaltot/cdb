package com.excilys.computerdatabase.entities.company;

import java.util.logging.Logger;

/**
 * Class representing a Company implementing ICompany
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class Company {
	
	
	private final static Logger LOGGER = Logger.getLogger(Company.class.getName());

	
	private long id;
	private String name;

	
	
	/**
	 * Private constructor whith no parameters
	 */
	private Company (){

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
	 * @param pName : the name to set
	 */
	public void setId(long id){
		this.id = id;
	}

	
	
	/**
	 * Displays one "Company" entity
	 */
	@Override
	public String toString() {
		return "Company " + getId() +   " and named " + getName();
	}
	
	
	/**
	 * Builder Pattern for company
	 * @author screetts
	 */
	public static class Builder {
		
		private Company company;

	    public Builder() {
	      this.company = new Company();
	    }
	   
	    
	    public Builder withId (long id){
	    	this.company.id = id;
	    	return this;
	    }
	    
	    
	    public Builder withName (String name){
	    	this.company.name = name;
	    	return this;
	    }
	    

	    public Company build() {
	    	
	    	return company;
	    	
//	    	if ( CompanyValidator.check(company) ) {
//	    		return company;
//	    	}
//	    	
//	    	else {
//	    		LOGGER.warning("COMPANY BUILDER RETURN NULL OBJECT");
//	    		company = null;
//	    		return company;
//	    	}
	    }
	}
}
