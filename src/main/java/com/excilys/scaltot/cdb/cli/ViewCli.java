package com.excilys.scaltot.cdb.cli;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;

/**
 * View fir Command line interface.
 *
 * @author Caltot Stéphan
 *
 *         21 févr. 2017
 */
public class ViewCli {

    private ControllerCli controlerCli;
    private ControllerCompany controllerCompany;
    private ControllerComputer controllerComputer;

    public static final String FORMAT_COMPUTER = "%3s | %15.15s |%10s | %10s | %10s%n";

    public static final String FORMAT_COMPANY = "%3s | %15.15s%n";

    public static final String FOOTER = "                                                    ";

    /**
     * View constructor setting controller.
     *
     * @param controlerCli
     *            :
     */
    public ViewCli(Optional<ControllerCli> controlerCli) {
        this.controlerCli = controlerCli.get();
        controlerCli.get().setViewCli(Optional.of(this));
    }

    /**
     * @return the controllerCompany
     */
    public ControllerCompany getControllerCompany() {
        return controllerCompany;
    }

    /**
     * @param controllerCompany : the controllerCompany to set
     */
    public void setControllerCompany(ControllerCompany controllerCompany) {
        this.controllerCompany = controllerCompany;
    }

    /**
     * @return the controllerComputer
     */
    public ControllerComputer getControllerComputer() {
        return controllerComputer;
    }

    /**
     * @param controllerComputer : the controllerComputer to set
     */
    public void setControllerComputer(ControllerComputer controllerComputer) {
        this.controllerComputer = controllerComputer;
    }

    /**
     * Designed display for CLI home.
     */
    public void welcome() {
        System.out.println("####################################################");
        System.out.println("#                                                  #");
        System.out.println("#                                                  #");
        System.out.println("#          Welcome to the CLI for                  #");
        System.out.println("#                computers                         #");
        System.out.println("#                                                  #");
        System.out.println("#                                                  #");
        System.out.println("####################################################\n\n");
    }

    /**
     * Designed display for CLI menu.
     *
     * @throws PersistenceException
     *             :
     */
    public void displayMenu() {
        System.out.println("\n\n");
        System.out.println("----------------------> Menu <----------------------\n");
        System.out.println("----------------------------------------------------");
        System.out.println("    * Press [1] to display All computers ");
        System.out.println("    * Press [2] to display All companies ");
        System.out.println("    * Press [3] to show computer details ");
        System.out.println("    * Press [4] to create computer ");
        System.out.println("    * Press [5] to update computer ");
        System.out.println("    * Press [6] to delete computer ");
        System.out.println("    * Press [q] to quit command line interface");
        System.out.println("----------------------------------------------------\n\n");

        try {
            controlerCli.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method displaying computers formated.
     *
     * @param computers
     *            :
     */
    public void displayAllComputers(List<Computer> computers) {
        System.out.println("\n");
        System.out.printf(FORMAT_COMPUTER, "ID", "NAME", "INTRODUCED", "DISCONTINUED", "COMPANY_ID");
        for (Computer computer : computers) {
            System.out.printf(FORMAT_COMPUTER, computer.getId(), computer.getName(), computer.getDateWichIsIntroduced(),
                    computer.getDateWichIsDiscontinued(),
                    ((computer.getManufacturer() == null) ? "No company" : computer.getManufacturer().getId()));

        }
        System.out.println("\n");
    }

    /**
     * Method displaying companies formated.
     *
     * @param companies
     *            :
     */
    public void displayAllCompanies(List<Company> companies) {
        System.out.println("\n");
        System.out.printf(FORMAT_COMPANY, "ID", "NAME");
        System.out.println("---------------------");
        for (Company company : companies) {
            System.out.printf(FORMAT_COMPANY, company.getId(), company.getName());
        }
        System.out.println("\n");
    }

    /**
     * Method displaying details for on computer.
     *
     * @param computer
     *            :
     */
    public void displayComputersDetails(Optional<Computer> computer) {
        if (computer.isPresent()) {
            System.out.println("\n" + computer.get().toString() + "\n");
        }
    }

    /**
     * Displaying information given in parameter.
     *
     * @param info
     *            :
     */
    public void displayInfo(Optional<String> info) {
        if (info.isPresent()) {
            System.out.print(info.get());
        }
    }
}
