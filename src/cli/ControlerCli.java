package cli;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import dao.CrudServiceCompany;
import dao.CrudServiceComputer;
import interfaces.ICompany;
import interfaces.IComputer;

/**
 * @author Caltot Stéphan
 *
 * 21 févr. 2017
 */
public class ControlerCli {

	private ViewCli viewCli;
	private CrudServiceComputer crudServiceComputer;
	private CrudServiceCompany crudServiceCompany;
	private List<IComputer> computers;
	private int offset = 0;
	
	public ControlerCli () throws SQLException{
		crudServiceCompany = new CrudServiceCompany();
		crudServiceComputer = new CrudServiceComputer();
	}
	
	public void setViewCli (ViewCli pViewCli){
		this.viewCli = pViewCli;
	}

	
	public void listingOfComputers() throws Exception{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String choice = null;
		do{
			viewCli.displayInfo("\nPress (p) to preview and (n) to next : ");
			
			choice = scan.nextLine();
			
			switch(choice){
			case "n" :
				offset += 10;
				computers = crudServiceComputer.findByPage(offset);
				viewCli.displayAllComputers(computers);
				viewCli.displayInfo(ViewCli.FOOTER);
				break;
			case "p":
				if (offset-10 >= 0){
					offset -= 10;
					computers = crudServiceComputer.findByPage(offset);
					viewCli.displayAllComputers(computers);
				}
				else{
					viewCli.displayInfo("Index too low you have to 'next'");
				}
				break;
			default:
				viewCli.displayInfo("You have to press (n) or (p)");
				break;
			}
			
		} while (offset >= 0);
		
	}
	
	public void listingOfCompanies() throws Exception{
		List<ICompany> companies = crudServiceCompany.findAll();
		viewCli.displayAllCompanies(companies);
		viewCli.displayInfo(ViewCli.FOOTER);
	}


	public void showComputersDetails() throws Exception{
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int computerId = 0;
		do{
			viewCli.displayInfo("\nPlease enter the computer's id : ");
			computerId = scan.nextInt();			
		} while (computerId == 0);
		IComputer computer = crudServiceComputer.find(computerId);
		viewCli.displayComputersDetails(computer);
	}
	
	public void deleteComputer() throws SQLException{
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int computerId = 0;
		do{
			viewCli.displayInfo("\nPlease enter the computer's id you want delete : ");
			computerId = scan.nextInt();			
		} while (computerId == 0);
		crudServiceComputer.delete(computerId);
		viewCli.displayInfo(ViewCli.FOOTER);
		viewCli.displayInfo("\nComputer (" + computerId + ") deleted successfully !\n\n");
		}
	
	public void execute () throws Exception{
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String inputValue = "";
		do {
			if (!inputValue.equals("d")) {
				viewCli.displayInfo(ViewCli.FOOTER);
				viewCli.displayInfo("\n\nPlease select an option (press 'd' to see full menu)  :  ");
			}
			inputValue = scan.nextLine();

			switch (inputValue){
			case "1":
				listingOfComputers();
				break;
			case "2":
				listingOfCompanies();
				break;
			case "3":
				showComputersDetails();
				break;
			case "4":
				deleteComputer();
				break;
			case "5":
				break;
			case "6":
				break;
			case "q":
				viewCli.displayInfo(ViewCli.FOOTER);
				viewCli.displayInfo("CLI closed, good bye !");
				viewCli.displayInfo(ViewCli.FOOTER);
				break;
			case "d":
				viewCli.displayMenu();
				break;
			default :
				viewCli.displayInfo(ViewCli.FOOTER);
				viewCli.displayInfo("You pressed an unrecognized key, please hit an other again");
				viewCli.displayInfo(ViewCli.FOOTER);
				break;
			}
		} while(!inputValue.equals("q"));

	}
	
}
