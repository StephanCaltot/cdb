package com.excilys.scaltot.cdb.cli;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.repository.impl.CrudServiceCompanyImpl;
import com.excilys.scaltot.cdb.repository.impl.CrudServiceComputerImpl;

/**
 * Controler for Command line interface.
 *
 * @author Caltot Stéphan
 *
 *         21 févr. 2017
 */
public class ControllerCli {

    private ViewCli viewCli;
    private CrudServiceComputerImpl crudServiceComputer;
    private CrudServiceCompanyImpl crudServiceCompany;
    private List<Computer> computers;
    private List<Company> companies;
    private long offset = 0;
    private static long numberForEachpPage = 10;
    private long offsetCompany = 0;
    private Scanner scan;

    /**
     * Controler's constructor setting crudService for company and computer.
     *
     * @throws SQLException :
     */
    public ControllerCli() {
        crudServiceCompany = CrudServiceCompanyImpl.INSTANCE;
        crudServiceComputer = CrudServiceComputerImpl.INSTANCE;
        scan = ScannerSystemIn.getInstance();
    }

    /**
     * @param viewCli : the view to set.
     */
    public void setViewCli(Optional<ViewCli> viewCli) {
        this.viewCli = viewCli.get();
    }

    /**
     * Method : listing computers paginated.
     *
     * @throws Exception :
     * @throws PersistenceException :
     */
    public void listingOfComputers() {

        String choice = null;

        computers = crudServiceComputer.findByPage(offset, numberForEachpPage);
        viewCli.displayAllComputers(computers);
        viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
        do {

            viewCli.displayInfo(Optional.of("\nPress (p) to preview and (n) to next : "));
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
                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
                break;
            case "p":
                if (offset - 10 >= 0) {
                    offset -= 10;
                    computers = crudServiceComputer.findByPage(offset, numberForEachpPage);
                    viewCli.displayAllComputers(computers);
                } else {
                    viewCli.displayInfo(Optional.of("Index too low you have to 'next'"));
                }
                break;
            case "q":
                offset = -1;
                break;
            default:
                viewCli.displayInfo(Optional.of("You have to press (n) or (p) or (q) to quit"));
                break;
            }

        } while (offset >= 0);

    }

    /**
     * Method : listing companies paginated.
     *
     * @throws Exception :
     * @throws PersistenceException :
     */
    public void listingOfCompanies() {

        String choice = null;

        companies = crudServiceCompany.findByPage(offsetCompany, numberForEachpPage);
        viewCli.displayAllCompanies(companies);
        viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
        do {

            viewCli.displayInfo(Optional.of("\nPress (p) to preview and (n) to next : "));
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
                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
                break;
            case "p":
                if (offsetCompany - 10 >= 0) {
                    offsetCompany -= 10;
                    companies = crudServiceCompany.findByPage(offsetCompany, numberForEachpPage);
                    viewCli.displayAllCompanies(companies);
                } else {
                    viewCli.displayInfo(Optional.of("Index too low you have to 'next'"));
                }
                break;
            case "q":
                offsetCompany = -1;
                break;
            default:
                viewCli.displayInfo(Optional.of("You have to press (n) or (p) or (q) to quit"));
                break;
            }

        } while (offsetCompany >= 0);

    }

    /**
     * Method : showing details for one computer.
     *
     * @throws Exception
     *             :
     * @throws PersistenceException
     *             :
     */
    public void showComputersDetails() {
        long computerId = 0;
        do {
            viewCli.displayInfo(Optional.of("\nPlease enter the computer's id : "));
            computerId = scan.nextInt();
        } while (computerId == 0);
        Optional<Computer> computerOptional = crudServiceComputer.find(computerId);
        if (computerOptional.isPresent()) {
            viewCli.displayComputersDetails(computerOptional);
        }
    }

    /**
     * Method : deleting one computer.
     *
     * @throws SQLException
     *             :
     * @throws PersistenceException
     *             :
     */
    public void deleteComputer() {

        long computerId = 0;
        do {
            viewCli.displayInfo(Optional.of("\nPlease enter the computer's id you want delete : "));
            computerId = scan.nextInt();
        } while (computerId <= 0);
        if (crudServiceComputer.delete(computerId)) {
            viewCli.displayInfo(Optional.of("\nComputer (" + computerId + ") deleted successfully !\n\n"));
        }
        viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
    }

    /**
     * Method : handling menu execution.
     *
     * @throws Exception
     *             :
     * @throws PersistenceException
     *             :
     */
    public void execute() throws Exception {

        String inputValue = "";
        do {
            if (!inputValue.equals("d")) {
                viewCli.displayInfo(Optional.of("\n\nPlease select an option (press 'd' to see full menu)  :  "));
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
                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
                viewCli.displayInfo(Optional.of("\n     CLI closed, good bye !\n"));
                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
                break;
            case "d":
                viewCli.displayMenu();
                break;
            default:
                viewCli.displayInfo(Optional.of("\nYou pressed an unrecognized key, please hit an other again"));
                break;
            }
        } while (!inputValue.equals("q"));
    }
}
