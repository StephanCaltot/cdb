package entities.company;

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
	 * @param pName : the name to set
	 */
	public void setId(int pId){
		this.id = pId;
	}


	/**
	 * Builder Pattern for company
	 * @author screetts
	 */
	public static class CompanyBuilder {
		@SuppressWarnings("unused")
		private int id;
	    private String name;

	    public CompanyBuilder(String name) {
	      this.name = name;
	    }
	    
	    public CompanyBuilder id (int pId){
	    	this.id = pId;
	    	return this;
	    }

	    public Company build() {
	      return new Company(this);
	    }
	}
}
