package com.excilys.scaltot.cdb.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.scaltot.cdb.entities.Computer;
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

        crudComputerImpl.create(computer);

    }

    /**
     * Find CRUD's operation.
     *
     * @param id : id
     * @return computer entity find with id gave in parameter
     */
    public Optional<Computer> find(long id) {

        return crudComputerImpl.find(id);

    }

    /**
     * Delete CRUD's operation.
     *
     * @param id : id
     * @return long id
     */
    public long delete(long id) {

        return crudComputerImpl.delete(id);

    }

    /**
     * Update CRUD's operation.
     *
     * @param computer : computer
     * @return computer
     */
    public Optional<Computer> update(Optional<Computer> computer) {

        return crudComputerImpl.update(computer);

    }


    /**
     * Return the number of computer in database.
     * @return long
     */
    public long getCountOfComputers() {

        return crudComputerImpl.getCountOfElements();

    }

    /**
     * Return the list of computer in database filtered by name.
     * @param nameFilter : filter
     * @return list of computers
     */
    public List<Computer> getComputersFiltered(String nameFilter) {

        return crudComputerImpl.getComputersFiltered(nameFilter);

    }

    /**
     * Retrieves computers paginated by limit ( 10 here ).
     *
     * @param pagination : pagination
     * @return list of computers paginated
     */
    public List<Computer> findByPageFilter(Pagination pagination) {

        return crudComputerImpl.findByPageFilter(pagination);

    }
}