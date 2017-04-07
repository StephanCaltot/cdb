package com.excilys.scaltot.cdb.persistence.interfaces;

import java.util.List;

import com.excilys.scaltot.cdb.entities.Company;

/**
 * @author Caltot Stéphan
 *
 * 20 mars 2017
 */
public interface CrudCompany extends CrudService<Company> {

    /**
     * Retrieves all company.
     * @return list company
     */
    List<Company> findAll();

}
