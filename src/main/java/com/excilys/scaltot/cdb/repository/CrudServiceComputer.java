package com.excilys.scaltot.cdb.repository;

import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.computer.Computer;

public interface CrudServiceComputer extends CrudService<Computer> {

    /**
     * Create function for computer.
     *
     * @param computer : computer
     */
    void create(Optional<Computer> computer);

    /**
     * Delete function for computer.
     *
     * @param id : id
     * @return boolean
     */
    Boolean delete(long id);

    /**
     * Update function for computer.
     *
     * @param computer : computer
     */
    void update(Optional<Computer> computer);

    /**
     * Return number of computer in database.
     * @return long
     */
    long getCountOfComputers();

    /**
     * Return list of computers by page and filtered.
     * @param offset ; offset
     * @param numberForEachPage : number of computers for each page
     * @param filter : filter
     * @return list of computers
     */
    List<Computer> findByPageFilter(long offset, long numberForEachPage, String filter);
}
