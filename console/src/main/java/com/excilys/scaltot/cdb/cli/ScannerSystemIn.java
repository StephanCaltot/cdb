package com.excilys.scaltot.cdb.cli;

import java.util.Scanner;

/**
 * @author Caltot Stéphan
 *
 *         23 févr. 2017
 */
public class ScannerSystemIn {


    /**
     * Private constructor for ScannerSystemIn singleton.
     */
    private ScannerSystemIn() {

    }

    /**
     * ScannerSystemIn's Holder instantiating new Scanner Syteme.in.
     *
     * @author Caltot Stéphan
     *
     *         23 févr. 2017
     */
    private static class ScannerHolder {
        private static final Scanner INSTANCE = new Scanner(System.in);
    }

    /**
     * Retrieves singleton'instance.
     *
     * @return scanner
     */
    public static Scanner getInstance() {
        return ScannerHolder.INSTANCE;
    }

}
