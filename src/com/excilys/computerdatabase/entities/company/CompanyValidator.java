package com.excilys.computerdatabase.entities.company;

import com.excilys.computerdatabase.check.StringCheck;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface CompanyValidator {

	
	
	/**
	 * Method checking Company's entity
	 * @param pCompany
	 * @throws Exception
	 */
	public static void check(Company company) throws Exception{
		if(company.getName().equals(null)) throw new Exception("Name can't be null");
		StringCheck.isFormed(company.getName());
	}
}
