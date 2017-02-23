package com.excilys.computerdatabase.check;

import java.util.logging.Logger;

/**
 * Verifying string format 
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class StringCheck {
	
	
	private final static Logger LOGGER = Logger.getLogger(DateCheck.class.getName());

	
	
	/**
	 * Check if the string is null or empty
	 * @param string
	 * @throws Exception
	 */
	public static Boolean isFormed(String string) {		
		if (string.matches("[a-zA-Z0-9-_ ]*") && !string.equals("") && !string.matches("[ ]*")) {
			return true;
		}
		else {
			LOGGER.warning("The string is empty, null or content forbidden characters \n");
			return false;
		}

	}

	
}
