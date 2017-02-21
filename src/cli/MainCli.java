package cli;

/**
 * @author Caltot Stéphan
 *
 * 21 févr. 2017
 */
public class MainCli {

	
	public static void main(String[] args) throws Exception {
		
		ControlerCli controlerCli = new ControlerCli();
		ViewCli viewCli = new ViewCli(controlerCli);
		controlerCli.setViewCli(viewCli);
		
		viewCli.welcome();
		viewCli.displayMenu();
		controlerCli.execute();
	}
	
}
