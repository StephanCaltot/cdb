package com.excilys.computerdatabase.check;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * Verifying date initialization  
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class DateCheck {
	
	
	private final static Logger LOGGER = Logger.getLogger(DateCheck.class.getName());

	
	
	/**
	 * Check if first date is before second date and throws an exception
	 * @param firstDate
	 * @param secondDate
	 * @throws DateTimeException
	 */
	public static boolean isGood(LocalDate firstDate, LocalDate secondDate) {
		if (!firstDate.equals(null) && !secondDate.equals(null)){
			if (!firstDate.isAfter(secondDate)){
				return true;
			}
			return false;
		}
		else {
			LOGGER.warning("One of computer's dates is null or discontinued date is more recent than introducing date \n");
			return false;
		}
	}
	
}
