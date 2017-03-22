package com.excilys.scaltot.cdb.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (computer.get().getDateWichIsIntroduced() != null) {
            computerDto.setDateWichIsIntroduced(computer.get().getDateWichIsIntroduced().toString());
        }

        if (computer.get().getDateWichIsDiscontinued() != null) {
            computerDto.setDateWichIsDiscontinued(computer.get().getDateWichIsDiscontinued().toString());
        }

        if (computer.get().getManufacturer().getId() != 0) {
            computerDto.setCompanyId(computer.get().getManufacturer().getId());
            computerDto.setCompanyName(computer.get().getManufacturer().getName());
        }

        return computerDto;
    };

    /**
     * Changes computer list to computer DTO.
     * @param computers : computers
     * @return computerDtolist
     */
    public static List<ComputerDto> computerListToComputerDto(List<Computer> computers) {
        List<ComputerDto> computersDto = new ArrayList<>();
        ComputerDto computerDto;

        for (Computer computer: computers) {
            computerDto = new ComputerDto();
            computerDto.setId(computer.getId());
            computerDto.setName(computer.getName());
            if (computer.getDateWichIsIntroduced() != null) {
                computerDto.setDateWichIsIntroduced(computer.getDateWichIsIntroduced().toString());
            }

            if (computer.getDateWichIsDiscontinued() != null) {
                computerDto.setDateWichIsDiscontinued(computer.getDateWichIsDiscontinued().toString());
            }

            if (computer.getManufacturer().getId() != 0) {
                computerDto.setCompanyId(computer.getManufacturer().getId());
                computerDto.setCompanyName(computer.getManufacturer().getName());
            }
            computersDto.add(computerDto);
        }

        return computersDto;
    };
}
