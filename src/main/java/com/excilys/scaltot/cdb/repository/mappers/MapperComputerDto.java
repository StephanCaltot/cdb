package com.excilys.scaltot.cdb.repository.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.excilys.scaltot.cdb.entities.company.CompanyDto;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.entities.computer.ComputerDto;

/**
 * @author Caltot Stéphan
 *
 * 3 mars 2017
 */
public class MapperComputerDto {
    /**
     * Changes computer to computer DTO.
     * @param computer :
     * @return computerDto
     */
    public static ComputerDto computerToComputerDto(Optional<Computer> computer) {
        ComputerDto computerDto = new ComputerDto();

        computerDto.setId(computer.get().getId());
        computerDto.setName(computer.get().getName());
        computerDto.setDateWichIsIntroduced(computer.get().getDateWichIsIntroduced().toString());
        computerDto.setDateWichIsDiscontinued(computer.get().getDateWichIsDiscontinued().toString());
        computerDto.setCompanyId(computer.get().getManufacturer().getId());
        computerDto.setCompanyName(computer.get().getManufacturer().getName());

        return computerDto;
    };

    /**
     * Changes computer list to computer DTO.
     * @param computers : computers
     * @return computerDtolist
     */
    public static List<ComputerDto> computerListToComputerDto(List<Optional<Computer>> computers) {
        List<ComputerDto> computersDto = new ArrayList<>();
        ComputerDto computerDto = new ComputerDto();

        for (Optional<Computer> computer: computers) {
            computerDto.setId(computer.get().getId());
            computerDto.setName(computer.get().getName());
            computerDto.setDateWichIsIntroduced(computer.get().getDateWichIsIntroduced().toString());
            computerDto.setDateWichIsDiscontinued(computer.get().getDateWichIsDiscontinued().toString());
            computerDto.setCompanyId(computer.get().getManufacturer().getId());
            computerDto.setCompanyName(computer.get().getManufacturer().getName());
            computersDto.add(computerDto);
        }

        return computersDto;
    };
}
