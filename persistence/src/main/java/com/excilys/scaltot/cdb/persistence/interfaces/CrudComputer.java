package com.excilys.scaltot.cdb.persistence.interfaces;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;

/**
 * @author Caltot Stéphan
 *
 * 20 mars 2017
 */
public interface CrudComputer extends CrudService<Computer> {

    /**
     * Method which permits to create a computer.
     * @param computer : computer
     * @return 
     * @throws PersistenceException : PersistenceException
     */
    Optional<Computer> create(Optional<Computer> computer) throws PersistenceException;

    /**
     * Method which permits to update a computer.
     * @param computer : computer
     * @return computer
     * @throws PersistenceException : PersistenceException
     */
    Optional<Computer> update(Optional<Computer> computer) throws PersistenceException;

    /**
     * Method which permits to retrieves a list of computer filtered.
     * @param filter : filter
     * @return list of computer
     * @throws PersistenceException : PersistenceException
     */
    List<Computer> getComputersFiltered(String filter) throws PersistenceException;

}
