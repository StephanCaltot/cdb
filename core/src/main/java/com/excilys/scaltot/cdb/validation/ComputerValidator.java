package com.excilys.scaltot.cdb.validation;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.entities.Computer;

/**
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public abstract class ComputerValidator {

    static final Logger LOGGER = LoggerFactory.getLogger(CompanyValidator.class.getName());

    /**
     * Static method checking all form's rules for computer.
     *
     * @param computer : computer
     * @return boolean
     */
    public static Boolean check(Optional<Computer> computer) {
        if (computer.get().getId() > 0) {
            if (computer.get().getManufacturer() == null) {
                if (!computer.get().getName().equals(null)
                        && StringValidator.isFormed(Optional.of(computer.get().getName()))
                        && DateValidator.bothDatesNotNull(Optional.of(computer.get().getDateWichIsIntroduced()),
                                Optional.of(computer.get().getDateWichIsDiscontinued()))) {
                    return DateValidator.isRealTime(Optional.of(computer.get().getDateWichIsIntroduced()),
                            Optional.of(computer.get().getDateWichIsDiscontinued()));
                }
            } else {
                if (!computer.get().getName().equals(null)
                        && StringValidator.isFormed(Optional.of(computer.get().getName()))
                        && CompanyValidator.check(Optional.of(computer.get().getManufacturer()))
                        && DateValidator.bothDatesNotNull(Optional.of(computer.get().getDateWichIsIntroduced()),
                                Optional.of(computer.get().getDateWichIsDiscontinued()))) {
                    return DateValidator.isRealTime(Optional.of(computer.get().getDateWichIsIntroduced()),
                            Optional.of(computer.get().getDateWichIsDiscontinued()));
                }
            }
        }
        LOGGER.warn("Wrong ID : The id of computer can't be egal or less than zero");
        return false;
    }
}
