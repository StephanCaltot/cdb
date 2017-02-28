package com.excilys.scaltot.cdb.repository;

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
}
