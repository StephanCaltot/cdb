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
public class StringCheck {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateCheck.class.getName());

    /**
     * Check if the string is null or empty.
     *
     * @param string
     *            :
     * @throws Exception
     * @return boolean
     */
    public static Boolean isFormed(Optional<String> string) {
        if (string.get().matches("[a-zA-Z0-9-_ ./+]*") && !string.get().equals("") && !string.get().matches("[ ]*")) {
            return true;
        } else {
            LOGGER.warn("The string is empty, null or content forbidden characters \n");
            return false;
        }
    }
}
