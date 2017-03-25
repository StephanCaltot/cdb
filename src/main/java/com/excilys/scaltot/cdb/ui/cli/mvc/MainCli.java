package com.excilys.scaltot.cdb.ui.cli.mvc;

import java.util.Optional;

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
     * @param args : ARGS list
     */
    public static void main(String[] args) {

        ViewCli viewCli = new ViewCli(Optional.of(new ControllerCli()));

        viewCli.welcome();
        viewCli.displayMenu();
    }

}
