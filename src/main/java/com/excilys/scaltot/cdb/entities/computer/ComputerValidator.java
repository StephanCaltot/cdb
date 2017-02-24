package com.excilys.scaltot.cdb.entities.computer;

import java.util.logging.Logger;
import com.excilys.scaltot.cdb.check.StringCheck;
import com.excilys.scaltot.cdb.entities.company.CompanyValidator;

/**
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public interface ComputerValidator {

    Logger LOGGER = Logger.getLogger(CompanyValidator.class.getName());

    /**
     * Static method checking Computer entity.
     *
     * @param computer :
     * @throws Exception
     * @return Boolean
     */
    static Boolean check(Computer computer) {
        return !(computer.getName().equals(null) || !StringCheck.isFormed(computer.getName()));
    }
}
