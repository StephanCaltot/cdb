package com.excilys.scaltot.cdb.repository.mappers;

import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.entities.computer.ComputerDto;

/**
 * @author Caltot Stéphan
 *
 * 3 mars 2017
 */
public class MapperPaginationDto {

    /**
     * Changes computer to computer DTO.
     * @param computer
     * @return computerDto
     */
    public static ComputerDto PaginationToDto(Computer computer){
        ComputerDto computerDto = new ComputerDto();

        computerDto.setId(computer.getId());
        computerDto.setName(computer.getName());
        computerDto.setDateWichIsIntroduced(computer.getDateWichIsIntroduced().toString());
        computerDto.setDateWichIsDiscontinued(computer.getDateWichIsDiscontinued().toString());
        computerDto.setCompanyId(computer.getManufacturer().getId());
        computerDto.setCompanyName(computer.getManufacturer().getName());

        return computerDto;
    };
}
