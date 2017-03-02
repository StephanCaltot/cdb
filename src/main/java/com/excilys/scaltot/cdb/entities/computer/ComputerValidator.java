package com.excilys.scaltot.cdb.entities.computer;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.entities.company.CompanyValidator;
import com.excilys.scaltot.cdb.validation.DateCheck;
import com.excilys.scaltot.cdb.validation.StringCheck;

/**
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public interface ComputerValidator {

    Logger LOGGER = LoggerFactory.getLogger(CompanyValidator.class.getName());

    /**
     * Static method checking all form's rules for computer.
     *
     * @param computer
     *            :
     * @return boolean
     */
    static Boolean check(Optional<Computer> computer) {
        if (computer.get().getId() > 0) {
            if (computer.get().getManufacturer() == null) {
                if (!computer.get().getName().equals(null)
                        && StringCheck.isFormed(Optional.of(computer.get().getName()))
                        && DateCheck.bothDatesNotNull(Optional.of(computer.get().getDateWichIsIntroduced()),
                                Optional.of(computer.get().getDateWichIsDiscontinued()))) {
                    return DateCheck.isRealTime(Optional.of(computer.get().getDateWichIsIntroduced()),
                            Optional.of(computer.get().getDateWichIsDiscontinued()));
                }
            } else {
                if (!computer.get().getName().equals(null)
                        && StringCheck.isFormed(Optional.of(computer.get().getName()))
                        && CompanyValidator.check(Optional.of(computer.get().getManufacturer()))
                        && DateCheck.bothDatesNotNull(Optional.of(computer.get().getDateWichIsIntroduced()),
                                Optional.of(computer.get().getDateWichIsDiscontinued()))) {
                    return DateCheck.isRealTime(Optional.of(computer.get().getDateWichIsIntroduced()),
                            Optional.of(computer.get().getDateWichIsDiscontinued()));
                }
            }
        }
        LOGGER.warn("Wrong ID : The id of computer can't be egal or less than zero");
        return false;
    }
}
