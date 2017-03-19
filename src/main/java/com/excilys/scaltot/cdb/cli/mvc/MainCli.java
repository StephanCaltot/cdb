package com.excilys.scaltot.cdb.cli.mvc;

import java.util.Optional;

import com.excilys.scaltot.cdb.exceptions.PersistenceException;

/**
 * Main of Command line interface.
 *
 * @author Caltot Stéphan
 *
 *         21 févr. 2017
 */
public class MainCli {

    /**
     * Main for launch and handle CLI.
     *
     * @param args :
     * @throws Exception :
     * @throws PersistenceException :
     */
    public static void main(String[] args) {

        ViewCli viewCli = new ViewCli(Optional.of(new ControllerCli()));

        viewCli.welcome();
        viewCli.displayMenu();
    }

}
