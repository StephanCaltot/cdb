package com.excilys.scaltot.cdb.entities.company;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.scaltot.cdb.validation.StringCheck;

/**
 * @author Caltot Stéphan
 *
 *         20 févr. 2017
 */
public interface CompanyValidator {

    Logger LOGGER = LoggerFactory.getLogger(CompanyValidator.class.getName());

    /**
     * Method checking Company's entity .
     *
     * @param company
     *            :
     * @throws Exception
     *             :
     * @return boolean
     */
    static Boolean check(Optional<Company> company) {
        if (company.get().getId() > 0) {
            if ((company.get().getName() != null && StringCheck.isFormed(Optional.of(company.get().getName())))) {
                return true;
            } else {
                LOGGER.warn("The company's name is null or doesn't matches with authorized characters");
                return false;
            }
        }
        LOGGER.warn("Wrong ID : The id of company can't be egal or less than zero");
        return false;
    }
}
