package com.excilys.scaltot.cdb.cli;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.repository.Pagination;
import com.excilys.scaltot.cdb.repository.impl.CrudServiceCompanyImpl;
import com.excilys.scaltot.cdb.services.CrudCompanyService;
import com.excilys.scaltot.cdb.services.CrudComputerService;

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
    private Pagination<Computer> paginationComputer = new Pagination.PaginationBuilder().withOffset(10).build();
    private Pagination<Company> paginationCompany = new Pagination.PaginationBuilder().withOffset(10).build();
    private long offset = 0;

    /**
     * Controler's constructor setting crudService for company and computer.
     *
     * @throws SQLException :
     */
    public ControllerCli() {
        paginationCompany.setNumberOfElements(CrudServiceCompanyImpl.INSTANCE.getCountOfCompanies());
        paginationCompany.setNumberOfPages(paginationCompany.getNumberOfElements() / paginationCompany.getPageSize());
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

        computers = CrudComputerService.findByPageFilter(paginationComputer);
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
                computers = paginationComputer.nextPage();
                viewCli.displayAllComputers(computers);
                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
                break;
            case "p":
                    computers = paginationComputer.previousPage();
                    viewCli.displayAllComputers(computers);
                break;
            case "q":
                offset = -1;
                break;
            case "Q":
                offset = -1;
                break;
            default:
                //viewCli.displayInfo(Optional.of("You have to press (n) or (p) or (q) to quit"));
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
        companies = new CrudCompanyService().findByPage(paginationComputer);
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
                paginationCompany.nextPage();
                companies = paginationCompany.findByPageFilterCompany();
                viewCli.displayAllCompanies(companies);
                viewCli.displayInfo(Optional.of(ViewCli.FOOTER));
                break;
            case "p":
                paginationCompany.previousPage();
                companies = paginationCompany.findByPageFilterCompany();
                viewCli.displayAllCompanies(companies);
                break;
            case "q":
                offsetCompany = -1;
                break;
            case "Q":
                offsetCompany = -1;
                break;
            default:
                //viewCli.displayInfo(Optional.of("You have to press (n) or (p) or (q) to quit"));
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
        Optional<Computer> computerOptional = CrudComputerService.find(computerId);
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
        if (CrudComputerService.delete(computerId)) {
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
