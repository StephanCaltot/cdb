package com.excilys.computerdatabase.cli;

/**
 * Main of Command line interface
 * @author Caltot Stéphan
 *
 * 21 févr. 2017
 */
public class MainCli {

	
	
	/**
	 * Main for launch and handle CLI
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		ViewCli viewCli = new ViewCli(new ControllerCli());
		
		viewCli.welcome();
		viewCli.displayMenu();
	}
	
}
