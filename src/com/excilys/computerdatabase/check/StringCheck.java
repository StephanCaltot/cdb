package com.excilys.computerdatabase.check;

/**
 * Verifying string format 
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class StringCheck {
	
	
	
	/**
	 * Check if the string is null or empty
	 * @param string
	 * @throws Exception
	 */
	public static void isFormed(String string) throws Exception{
		if (string.matches("")) throw new IllegalArgumentException("the string is empty or content forbidden characters");
	};

	
}
