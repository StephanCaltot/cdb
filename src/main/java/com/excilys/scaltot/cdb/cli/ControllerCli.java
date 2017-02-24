package com.excilys.scaltot.cdb.cli;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.repository.CrudServiceCompany;
import com.excilys.scaltot.cdb.repository.CrudServiceComputer;

/**
 * Controler for Command line interface.
 * @author Caltot Stéphan
 *
 *         21 févr. 2017
 */
public class ControllerCli {

    private ViewCli viewCli;
    private CrudServiceComputer crudServiceComputer;
    private CrudServiceCompany crudServiceCompany;
    private List<Computer> computers;
    private List<Company> companies;
    private long offset = 0;
    private static int numberForEachpPage = 10;
    private long offsetCompany = 0;
    private Scanner scan;

    /**
     * Controler's constructor setting crudService for company and computer.
     * @throws SQLException
     *             :
     */
    public ControllerCli() throws SQLException {
        crudServiceCompany = new CrudServiceCompany();
        crudServiceComputer = new CrudServiceComputer();
        scan = ScannerSystemIn.getInstance();
    }

    /**
     * @param viewCli
     *            : the view to set.
     */
    public void setViewCli(ViewCli viewCli) {
        this.viewCli = viewCli;
    }

    /**
     * Method : listing computers paginated.
     * @throws Exception
     *             :
     */
    public void listingOfComputers() throws Exception {

        String choice = null;

        computers = crudServiceComputer.findByPage(offset, numberForEachpPage);
        viewCli.displayAllComputers(computers);
        viewCli.displayInfo(ViewCli.FOOTER);
        do {
            viewCli.displayInfo("\nPress (p) to preview and (n) to next : ");

            choice = scan.nextLine();

            switch (choice) {
            case "1":
                listingOfComputers();
                break;
            case "2":
                listingOfCompanies();
                break;
            case "3":
                showComputersDetails();
                break;
            case "6":
                deleteComputer();
                break;
            case "d":
                viewCli.displayMenu();
                break;
            case "n":
                offset += 10;
                computers = crudServiceComputer.findByPage(offset, numberForEachpPage);
                viewCli.displayAllComputers(computers);
                viewCli.displayInfo(ViewCli.FOOTER);
                break;
            case "p":
                if (offset - 10 >= 0) {
                    offset -= 10;
                    computers = crudServiceComputer.findByPage(offset, numberForEachpPage);
                    viewCli.displayAllComputers(computers);
                } else {
                    viewCli.displayInfo("Index too low you have to 'next'");
                }
                break;
            case "q":
                offset = -1;
                break;
            default:
                viewCli.displayInfo("You have to press (n) or (p) or (q) to quit");
                break;
            }

        } while (offset >= 0);

    }

    /**
     * Method : listing companies paginated.
     * @throws Exception :
     */
    public void listingOfCompanies() throws Exception {

        String choice = null;

        companies = crudServiceCompany.findByPage(offsetCompany, numberForEachpPage);
        viewCli.displayAllCompanies(companies);
        viewCli.displayInfo(ViewCli.FOOTER);
        do {
            viewCli.displayInfo("\nPress (p) to preview and (n) to next : ");

            choice = scan.nextLine();

            switch (choice) {
            case "1":
                listingOfComputers();
                break;
            case "2":
                listingOfCompanies();
                break;
            case "3":
                showComputersDetails();
                break;
            case "6":
                deleteComputer();
                break;
            case "d":
                viewCli.displayMenu();
                break;
            case "n":
                offsetCompany += 10;
                companies = crudServiceCompany.findByPage(offsetCompany, numberForEachpPage);
                viewCli.displayAllCompanies(companies);
                viewCli.displayInfo(ViewCli.FOOTER);
                break;
            case "p":
                if (offsetCompany - 10 >= 0) {
                    offsetCompany -= 10;
                    companies = crudServiceCompany.findByPage(offsetCompany, numberForEachpPage);
                    viewCli.displayAllCompanies(companies);
                } else {
                    viewCli.displayInfo("Index too low you have to 'next'");
                }
                break;
            case "q":
                offsetCompany = -1;
                break;
            default:
                viewCli.displayInfo("You have to press (n) or (p) or (q) to quit");
                break;
            }

        } while (offsetCompany >= 0);

    }

    /**
     * Method : showing details for one computer.
     * @throws Exception :
     */
    public void showComputersDetails() throws Exception {
        Computer computer = null;
        long computerId = 0;
        do {
            viewCli.displayInfo("\nPlease enter the computer's id : ");
            computerId = scan.nextInt();
        } while (computerId == 0);
        if (crudServiceComputer.find(computerId).isPresent()) {
//            if (ComputerValidator.check(computer)) {
                computer = crudServiceComputer.find(computerId).get();
//            }

        }
        viewCli.displayComputersDetails(computer);
    }

    /**
     * Method : deleting one computer.
     * @throws SQLException :
     */
    public void deleteComputer() throws SQLException {

        long computerId = 0;
        do {
            viewCli.displayInfo("\nPlease enter the computer's id you want delete : ");
            computerId = scan.nextInt();
        } while (computerId == 0);
        crudServiceComputer.delete(computerId);
        viewCli.displayInfo(ViewCli.FOOTER);
        viewCli.displayInfo("\nComputer (" + computerId + ") deleted successfully !\n\n");
    }

    /**
     * Method : handling menu execution.
     * @throws Exception :
     */
    public void execute() throws Exception {

        String inputValue = "";
        do {
            if (!inputValue.equals("d")) {
                viewCli.displayInfo("\n\nPlease select an option (press 'd' to see full menu)  :  ");
            }
            inputValue = scan.nextLine();

            switch (inputValue) {
            case "1":
                listingOfComputers();
                break;
            case "2":
                listingOfCompanies();
                break;
            case "3":
                showComputersDetails();
                break;
            case "6":
                deleteComputer();
                break;
            case "q":
                viewCli.displayInfo(ViewCli.FOOTER);
                viewCli.displayInfo("\n     CLI closed, good bye !\n");
                viewCli.displayInfo(ViewCli.FOOTER);
                break;
            case "d":
                viewCli.displayMenu();
                break;
            default:
                viewCli.displayInfo("\nYou pressed an unrecognized key, please hit an other again");
                break;
            }
        } while (!inputValue.equals("q"));

    }

}
