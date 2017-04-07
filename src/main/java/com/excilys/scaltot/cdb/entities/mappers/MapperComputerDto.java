package com.excilys.scaltot.cdb.entities.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.entities.computer.ComputerDto;
import com.excilys.scaltot.cdb.entities.computer.Computer.ComputerBuilder;

/**
 * @author Caltot Stéphan
 *
 * 3 mars 2017
 */
public class MapperComputerDto {
    
    /**
     * Changes computer to computer DTO.
     * @param computer : computer
     * @return computerDto
     */
    public static ComputerDto computerToComputerDto(Optional<Computer> computer) {
        ComputerDto computerDto = new ComputerDto();

        computerDto.setId(computer.get().getId());
        computerDto.setComputerName(computer.get().getName());

        if (computer.get().getDateWichIsIntroduced() != null) {
            computerDto.setDateWichIsIntroduced(computer.get().getDateWichIsIntroduced().toString());
        }

        if (computer.get().getDateWichIsDiscontinued() != null) {
            computerDto.setDateWichIsDiscontinued(computer.get().getDateWichIsDiscontinued().toString());
        }

        if (computer.get().getManufacturer() != null && computer.get().getManufacturer().getId() > 0) {
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
            computerDto.setComputerName(computer.getName());
            if (computer.getDateWichIsIntroduced() != null) {
                computerDto.setDateWichIsIntroduced(computer.getDateWichIsIntroduced().toString());
            }

            if (computer.getDateWichIsDiscontinued() != null) {
                computerDto.setDateWichIsDiscontinued(computer.getDateWichIsDiscontinued().toString());
            }

            if (computer.getManufacturer() != null && computer.getManufacturer().getId() > 0) {
                computerDto.setCompanyId(computer.getManufacturer().getId());
                computerDto.setCompanyName(computer.getManufacturer().getName());
            }
            computersDto.add(computerDto);
        }

        return computersDto;
    };

    /**
     * Changes computerDto to computer.
     * @param computerDto : computerDto
     * @return computer
     */
    public static Computer computerDtoToComputer(ComputerDto computerDto) {
        ComputerBuilder computerBuilder = new Computer.ComputerBuilder();

        if (computerDto.getId() > 0) {
            computerBuilder.withId(computerDto.getId());
        }

        if (computerDto.getComputerName() != null) {
            computerBuilder.withName(computerDto.getComputerName());
        }

        if (StringUtils.isNotBlank(computerDto.getDateWichIsIntroduced())) {
            computerBuilder.withDateWichIsIntroduced(LocalDate.parse(computerDto.getDateWichIsIntroduced()));
        }

        if (StringUtils.isNotBlank(computerDto.getDateWichIsDiscontinued())) {
            computerBuilder.withDateWichIsDiscontinued(LocalDate.parse(computerDto.getDateWichIsDiscontinued()));
        }

        if (StringUtils.isNotBlank(computerDto.getCompanyName()) && computerDto.getCompanyId() > 0) {
            Company company = new Company.CompanyBuilder()
                    .withId(computerDto.getCompanyId())
                    .withName(computerDto.getCompanyName())
                    .build();
            computerBuilder.withManufacturer(company);
        }

        return computerBuilder.build();
    }

}
