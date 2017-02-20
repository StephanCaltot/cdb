package entities;

import interfaces.ICompany;

/**
 * Class representing a Company implementing ICompany
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class Company implements ICompany {
	
	private int id;
	private String name;

	/**
	 * Private constructor with all needed parameters ( only "name" is absolutly needed )
	 * @param companyBuilder
	 */
	private Company (CompanyBuilder companyBuilder){
		this.setName(companyBuilder.name);
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
	public int getId() {
		return id;
	}


	/**
	 * Builder Pattern for company
	 * @author screetts
	 */
	public static class CompanyBuilder {
	    private String name;

	    public CompanyBuilder(String name) {
	      this.name = name;
	    }

	    public Company build() {
	      return new Company(this);
	    }
	}

}
