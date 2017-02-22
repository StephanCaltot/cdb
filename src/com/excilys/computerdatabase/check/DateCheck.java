package com.excilys.computerdatabase.check;

import java.time.DateTimeException;
import java.util.Date;

/**
 * Verifying date initialization  
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class DateCheck {
	
	
	
	/**
	 * Check if first date is before second date and throws an exception
	 * @param firstDate
	 * @param secondDate
	 * @throws DateTimeException
	 */
	public static void isGood(Date firstDate, Date secondDate) throws DateTimeException{
		if (firstDate.after(secondDate)) throw new DateTimeException("The discontinued date is more recent than introducing date \n");
	}
	
}