package com.excilys.scaltot.cdb.validation;

import java.time.LocalDate;
import java.util.Optional;

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
    public static boolean isGood(final Optional<LocalDate> firstDate, final Optional<LocalDate> secondDate) {
    	if (firstDate.get().isAfter(secondDate.get())) {
            LOGGER.warn("It's seems like discontinued date is more recent than introducing date \n");
            return false;
        }
    	return true;
    }
    
    
    /**
     * Return boolean if the both dates of computer aren't null.
     * @param firstDate :
     * @param secondDate :
     * @return boolean
     */
    public static boolean bothDatesNotNull(final Optional<LocalDate> firstDate, final Optional<LocalDate> secondDate) {
    	if (firstDate.isPresent() && secondDate.isPresent()){
    		return true;
    	} else {
    		LOGGER.info("One of selected date is null");
    		return false;
    	}
    }
    
}
