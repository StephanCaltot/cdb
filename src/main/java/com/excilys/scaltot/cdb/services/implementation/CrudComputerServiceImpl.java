package com.excilys.scaltot.cdb.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.pagination.Pagination;
import com.excilys.scaltot.cdb.persistence.interfaces.CrudComputer;
import com.excilys.scaltot.cdb.services.interfaces.CrudComputerService;

@Service
@Scope("singleton")
@Transactional
public class CrudComputerServiceImpl implements CrudComputerService {

    @Autowired
    private CrudComputer crudComputerImpl;

    /**
     * Create computer.
     * @param computer : computer
     */
    public void create(Optional<Computer> computer) {

        try {
            crudComputerImpl.create(computer);
        } catch (PersistenceException persistenceException) {
            throw new PersistenceException(persistenceException);
        }
    }

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     * @return computer entity find with id gave in parameter
     */
    public Optional<Computer> find(long id) {

        try {
            return crudComputerImpl.find(id);

        } catch (PersistenceException persistenceException) {

            throw new PersistenceException(persistenceException);

        }
    }

    /**
     * Delete CRUD's operation.
     *
     * @param id : id
     */
    public void delete(long id) {

       try {
           crudComputerImpl.delete(id);
       } catch (PersistenceException persistenceException) {
           throw new PersistenceException(persistenceException);
       }
   }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     */
    public void update(Optional<Computer> computer) {

        try {
            crudComputerImpl.update(computer);
       } catch (PersistenceException persistenceException) {
           throw new PersistenceException(persistenceException);
       }
    }

    /**
     * Retrieves all computers without any pagination.
     *
     * @return list of computers
     */
    public List<Computer> findAll() {

        try {

            return crudComputerImpl.findAll();

        } catch (PersistenceException persistenceException) {

            throw new PersistenceException(persistenceException);

        }
    }

    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfComputers() {

        try {

            return crudComputerImpl.getCountOfElements();

        } catch (PersistenceException persistenceException) {

            throw new PersistenceException(persistenceException);

        }
    }

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     */
    public List<Computer> getComputersFiltered(String nameFilter) {

        try {

            return crudComputerImpl.getComputersFiltered(nameFilter);

        } catch (PersistenceException persistenceException) {

            throw new PersistenceException(persistenceException);

        }
    }

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param pagination : pagination
     * @return list of computers paginated
     */
    public List<Computer> findByPageFilter(Pagination pagination) {

        try {

            return crudComputerImpl.findByPageFilter(pagination);

        } catch (PersistenceException persistenceException) {

            throw new PersistenceException(persistenceException);

        }
    }
}