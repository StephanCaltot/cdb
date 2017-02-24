package com.excilys.scaltot.cdb.entities.company;

import java.util.logging.Logger;

import com.excilys.scaltot.cdb.check.StringCheck;

/**
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public interface CompanyValidator {

    Logger LOGGER = Logger.getLogger(CompanyValidator.class.getName());

    /**
     * Method checking Company's entity .
     *
     * @param company :
     * @throws Exception
     * @return boolean
     */
    static Boolean check(Company company) {
        return !(company.getName().equals(null) || !StringCheck.isFormed(company.getName()));
    }
}
