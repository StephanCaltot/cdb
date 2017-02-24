package com.excilys.scaltot.cdb.check;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verifying date initialization.
 * @author Caltot Stéphan
 *
 * 20 févr. 2017
 */
public class DateCheck {

    /**
     * Logger for Date check class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateCheck.class.getName());


    /** Check if first date is before second date and throws an exception.
     * @param firstDate : introduced date
     * @param secondDate : discontinued date
     * @throws DateTimeException
     * @return boolean
     */
    public static boolean isGood(final LocalDate firstDate, final LocalDate secondDate) {
        if (!firstDate.equals(null) && !secondDate.equals(null)) {
            return !firstDate.isAfter(secondDate);
        } else {
            LOGGER.warn("One of computer's dates is null or discontinued date is more recent than introducing date \n");
            return false;
            }
        }
    }
