package com.excilys.scaltot.cdb.cli.mvc;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.scaltot.cdb.cli.ScannerSystemIn;
import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.services.CrudCompanyService;
import com.excilys.scaltot.cdb.services.CrudComputerService;
import com.excilys.scaltot.cdb.services.PaginationCompanyService;
import com.excilys.scaltot.cdb.services.PaginationComputerService;
import com.excilys.scaltot.cdb.utils.Pagination;

/**
 * Controler for Command line interface.
 *
 * @author Caltot Stéphan
 *
 *         21 févr. 2017
 */
public class ControllerCli {

    private ViewCli viewCli;
    private List<Computer> computers;
    private List<Company> companies;
    private long offsetCompany = 0;
    private Scanner scan;
    private Pagination paginationComputer = new Pagination();
    private Pagination paginationCompany = new Pagination();
    @Autowired
    private CrudComputerService crudComputerService;
    @Autowired
    private CrudCompanyService crudCompanyService;
    @Autowired
    private PaginationComputerService paginationComputerService;
    @Autowired
    private PaginationCompanyService paginationCompanyService;
    private long offset = 0;

    /**
     * Controler's constructor setting crudService for company and computer.
     *
     * @throws SQLException :
     */
    public ControllerCli() {
        paginationComputerService.paginationInitialisation(paginationComputer);
        paginationCompanyService.paginationInitialisation(paginationCompany);
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

        computers = paginationComputerService.findByPage(paginationComputer);
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
            case "7":
                deleteCompany();
            case "d":
                viewCli.displayMenu();
                break;
            case "n":
                paginationComputerService.nextPage(paginationComputer);
                computers = paginationComputerService.findByPage(paginationComputer);
                viewCli.displayAllComputers(computers);
                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
                break;
            case "p":
                paginationComputerService.previousPage(paginationComputer);
                computers = paginationComputerService.findByPage(paginationComputer);
                viewCli.displayAllComputers(computers);
                break;
            case "q":
                offset = -1;
                break;
            case "Q":
                offset = -1;
                break;
            default:
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
        companies = paginationCompanyService.findByPage(paginationCompany);
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
            case "7":
                deleteCompany();
            case "d":
                viewCli.displayMenu();
                break;
            case "n":
                paginationCompanyService.nextPage(paginationCompany);
                companies = paginationCompanyService.findByPage(paginationCompany);
                viewCli.displayAllCompanies(companies);
                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
                break;
            case "p":
                paginationCompanyService.previousPage(paginationCompany);
                companies = paginationCompanyService.findByPage(paginationCompany);
                viewCli.displayAllCompanies(companies);
                break;
            case "q":
                offsetCompany = -1;
                break;
            case "Q":
                offsetCompany = -1;
                break;
            default:
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
        Optional<Computer> computerOptional = crudComputerService.find(computerId);
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
        if (crudComputerService.delete(computerId)) {
            viewCli.displayInfo(Optional.of("\nComputer (" + computerId + ") deleted successfully !\n\n"));
        }
        viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
    }

    /**
     * Method : deleting one company and all computer associated.
     *
     * @throws SQLException
     *             :
     * @throws PersistenceException
     *             :
     */
    public void deleteCompany() {

        long companyId = 0;
        do {
            viewCli.displayInfo(Optional.of("\nPlease enter the company's id you want delete : "));
            companyId = scan.nextInt();
        } while (companyId <= 0);
        if (crudCompanyService.delete(companyId)) {
            viewCli.displayInfo(Optional.of("\nCompany (" + companyId + ") deleted successfully !\n\n"));
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
            case "7":
                deleteCompany();
            case "q":
                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
                viewCli.displayInfo(Optional.of("\n     CLI closed, good bye !\n"));
                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
                break;
            case "Q":
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
