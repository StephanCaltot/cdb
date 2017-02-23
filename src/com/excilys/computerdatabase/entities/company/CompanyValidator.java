package com.excilys.computerdatabase.entities.company;

import java.util.logging.Logger;

import com.excilys.computerdatabase.check.StringCheck;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface CompanyValidator {

	public final static Logger LOGGER = Logger.getLogger(CompanyValidator.class.getName());

	
	/**
	 * Method checking Company's entity
	 * @param pCompany
	 * @throws Exception
	 */
	public static Boolean check(Company company) {
		if(company.getName().equals(null) || StringCheck.isFormed(company.getName()) ) {
			return false;
		}
		else {
			return true;
		}
	}
}
