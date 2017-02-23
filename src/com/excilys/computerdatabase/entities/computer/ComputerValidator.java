package com.excilys.computerdatabase.entities.computer;

import java.util.logging.Logger;

import com.excilys.computerdatabase.check.DateCheck;
import com.excilys.computerdatabase.check.StringCheck;
import com.excilys.computerdatabase.entities.company.CompanyValidator;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface ComputerValidator{
	
	public final static Logger LOGGER = Logger.getLogger(CompanyValidator.class.getName());

	
	/**
	 * Static method checking Computer entity
	 * @param pComputer
	 * @throws Exception
	 */
	public static Boolean check(Computer computer) {
		if(computer.getName().equals(null) || !StringCheck.isFormed(computer.getName()) ) {
			return false;
		}
		else {
				
			if (DateCheck.isGood(computer.getDateWichIsIntroduced(), computer.getDateWichIsDiscontinued())){
				return true;
			}
			else {
				return false;
			}
		}
	}
}

