package com.excilys.scaltot.cdb.webservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Produces;
import javax.xml.ws.Response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.scaltot.cdb.dto.ComputerDto;
import com.excilys.scaltot.cdb.entities.Computer;
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
    public ComputerDto getComputer(@PathVariable Long id) {
        Optional<Computer> computer = crudComputerService.find(id);
        ComputerDto computerDto = MapperComputerDto.computerToComputerDto(computer);
        return computerDto;
    }

    @RequestMapping(method=RequestMethod.GET)
    public List<ComputerDto> getComputers() {
        List<Computer> computers = crudComputerService.getComputersFiltered("");
        List<ComputerDto> computersDto = MapperComputerDto.computerListToComputerDto(computers);
        return computersDto;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Computer> postComputer(ComputerDto computerDto) {
        Computer computer = MapperComputerDto.computerDtoToComputer(computerDto);
        crudComputerService.create(Optional.of(computer));
        return new ResponseEntity<Computer>(computer, HttpStatus.ACCEPTED   );
    }
    
    @RequestMapping(method=RequestMethod.PUT)
    public void putComputer(ComputerDto computerDto) {
        Computer computer = MapperComputerDto.computerDtoToComputer(computerDto);
        crudComputerService.update(Optional.of(computer));
    }

    @RequestMapping(method=RequestMethod.DELETE)
    public void putComputer(Long id) {
        crudComputerService.delete(id);
    }
}
