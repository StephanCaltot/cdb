package com.excilys.scaltot.cdb.validation;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verifying date initialization.
 *
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public class DateValidator {

    /**
     * Logger for Date check class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DateValidator.class.getName());

    /**
     * Check if first date is before second date and throws an exception.
     *
     * @param firstDate : introduced date
     * @param secondDate : discontinued date
     * @throws DateTimeException
     * @return boolean
     */
    public static boolean isRealTime(final Optional<LocalDate> firstDate, final Optional<LocalDate> secondDate) {
        if (firstDate.get().isAfter(secondDate.get())) {
            LOGGER.warn("It's seems like discontinued date is more recent than introducing date \n");
            return false;
        }
        return true;
    }

    /**
     * Return boolean if the both dates of computer aren't null.
     *
     * @param firstDate : first date
     * @param secondDate : second date
     * @return boolean
     */
    public static boolean bothDatesNotNull(final Optional<LocalDate> firstDate, final Optional<LocalDate> secondDate) {
        if (firstDate.isPresent() && secondDate.isPresent()) {
            return true;
        } else {
            LOGGER.info("One of selected date is null");
            return false;
        }
    }

    /**
     * Check date's format.
     * @param optionalDate : date
     * @return boolean
     */
    public static boolean formatIsValid(Optional<String> optionalDate) {
        if (optionalDate.isPresent()) {
            String date = optionalDate.get();
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return true;
            }
            LOGGER.warn("The date's format is not valid. Format expected : yyyy-mm-dd");
        }
        return false;
    }

}
