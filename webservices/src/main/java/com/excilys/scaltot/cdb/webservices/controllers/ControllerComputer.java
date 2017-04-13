package com.excilys.scaltot.cdb.webservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Produces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.scaltot.cdb.dto.ComputerDto;
import com.excilys.scaltot.cdb.entities.Computer;
import com.excilys.scaltot.cdb.exceptions.PersistenceException;
import com.excilys.scaltot.cdb.mappers.MapperComputerDto;
import com.excilys.scaltot.cdb.services.interfaces.CrudComputerService;

/**
 * @author Caltot Stéphan
 *
 * 11 avr. 2017
 */
@RestController
@RequestMapping(value="/computers")
@Produces("application/json")
public class ControllerComputer {

    private CrudComputerService crudComputerService;

    /**
     * Constructor.
     */
    public ControllerComputer(CrudComputerService crudComputerService) {
        this.crudComputerService = crudComputerService;
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> getComputer(@PathVariable Long id) {
        try {
            Optional<Computer> computer = crudComputerService.find(id);
            if (computer.isPresent()) {
                ComputerDto computerDto = MapperComputerDto.computerToComputerDto(computer);
                return new ResponseEntity<ComputerDto>(computerDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            }
        } catch (PersistenceException persistenceException) {
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException illegalArgumentException) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<?> getComputers() {
        try {
            List<Computer> computers = crudComputerService.getComputersFiltered("");
            List<ComputerDto> computersDto = MapperComputerDto.computerListToComputerDto(computers);
            return new ResponseEntity<List<Computer>>(computers, HttpStatus.OK);
        } catch (PersistenceException persistenceException) {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<?> postComputer(ComputerDto computerDto) {
        Computer computer = MapperComputerDto.computerDtoToComputer(computerDto);
        crudComputerService.create(Optional.of(computer));
        return new ResponseEntity<ComputerDto>(computerDto, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.PUT)
    public ResponseEntity<?> putComputer(ComputerDto computerDto) {
        Computer computer = MapperComputerDto.computerDtoToComputer(computerDto);
        crudComputerService.update(Optional.of(computer));
        return new ResponseEntity<ComputerDto>(computerDto, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> putComputer(@PathVariable Long id)   {
        crudComputerService.delete(id);
        return new ResponseEntity<Long>( id, HttpStatus.OK);
    }
}
