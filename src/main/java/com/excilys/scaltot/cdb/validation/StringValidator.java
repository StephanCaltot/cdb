package com.excilys.scaltot.cdb.validation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verifying string format.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public class StringValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateValidator.class.getName());

    /**
     * Check if the string is null or empty.
     *
     * @param string : string
     * @return boolean
     */
    public static boolean isFormed(Optional<String> string) {
        if (string.get().matches("[a-zA-Z0-9-_ ./+]*") && !string.get().equals("") && !string.get().matches("[ ]*")) {
            return true;
        } else {
            LOGGER.warn("The string is empty, null or content forbidden characters \n");
            return false;
        }
    }
}
