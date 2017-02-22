package com.excilys.computerdatabase.entities.computer;

import com.excilys.computerdatabase.check.DateCheck;
import com.excilys.computerdatabase.check.StringCheck;

/**
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public interface ComputerValidator{
	
	
	
	/**
	 * Static method checking Computer entity
	 * @param pComputer
	 * @throws Exception
	 */
	public static void check(Computer computer) throws Exception{
		if(computer.getName().equals(null)) throw new Exception("Name can't be null");
		StringCheck.isFormed(computer.getName());
		if (computer.getDateWichIsIntroduced() != null){
			DateCheck.isGood(computer.getDateWichIsIntroduced(), computer.getDateWichIsDiscontinued());	
		}
	}
}

