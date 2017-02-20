package entities;


import java.util.Date;

import check.ComputerValidator;
import interfaces.ICompany;
import interfaces.IComputer;

/**
 * Class representing a Computer implementing IComputer
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class Computer implements IComputer {
	
	private int id;
	private String name;
	private Date dateWichIsIntroduced;
	private Date dateWichIsDiscontinued;
	private ICompany manufacturer;

	
	/**
	 * Private constructor with all needed parameters ( only "name" is absolutly needed )
	 * @param computerBuilder
	 */
	private Computer (ComputerBuilder computerBuilder) {
		super();
		this.name = computerBuilder.name;
		this.dateWichIsIntroduced = computerBuilder.dateWichIsIntroduced;
		this.dateWichIsDiscontinued = computerBuilder.dateWichIsDiscontinued;
		this.manufacturer = computerBuilder.manufacturer;

	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
	public ICompany getManufacturer() {
		return manufacturer;
	}


	/**
	 * @param manufacturer : the manufacturer (company) to set
	 */
	public void setManufacturer(ICompany manufacturer) {
		this.manufacturer = manufacturer;
	}



	/**
	 * Builder Pattern for computer
	 * @author screetts
	 */
	public static class ComputerBuilder {
		private String name;
		private Date dateWichIsIntroduced;
		private Date dateWichIsDiscontinued;
		private ICompany manufacturer;
		
	    public ComputerBuilder(String name) {
	      this.name = name;
	    }
	    
	    public ComputerBuilder dateWichIsIntroduced (Date dateWichIsIntroduced){
	    	this.dateWichIsIntroduced = dateWichIsIntroduced;
	    	return this;
	    }

	    public ComputerBuilder dateWichIsDiscontinued (Date dateWichIsDiscontinued){
	    	this.dateWichIsIntroduced = dateWichIsDiscontinued;
	    	return this;
	    }

	    public ComputerBuilder manufacturer (ICompany manufacturer){
	    	this.manufacturer = manufacturer;
	    	return this;
	    }
	    
	    
	    public IComputer build() throws Exception {
	    	
	    	IComputer computer = new Computer(this);
	    	ComputerValidator.check(computer);
	    	
	    	return new Computer(this);
	    }
	}
	
}
