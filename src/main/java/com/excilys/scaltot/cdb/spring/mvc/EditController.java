package com.excilys.scaltot.cdb.spring.mvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.scaltot.cdb.entities.company.Company;
import com.excilys.scaltot.cdb.entities.company.CompanyDto;
import com.excilys.scaltot.cdb.entities.computer.Computer;
import com.excilys.scaltot.cdb.entities.computer.ComputerDto;
import com.excilys.scaltot.cdb.mappers.MapperCompanyDto;
import com.excilys.scaltot.cdb.mappers.MapperComputerDto;
import com.excilys.scaltot.cdb.services.interfaces.CrudCompanyService;
import com.excilys.scaltot.cdb.services.interfaces.CrudComputerService;
import com.excilys.scaltot.cdb.validation.DateValidator;

/**
 * @author Caltot Stéphan
 *
 * 23 mars 2017
 */
@Controller
@RequestMapping("/editComputer")
public class EditController {

    @Autowired
    private CrudComputerService crudComputerServiceImpl;
    @Autowired
    private CrudCompanyService crudCompanyServiceImpl;
    private List<CompanyDto> companies;
    private ComputerDto computerDto;

    /**
     * Set computer properties on the edit page.
     * @param model : model
     * @param id : id
     * @return page redirection
     */
    @RequestMapping(method = RequestMethod.GET)
    public String doGet(ModelMap model,
            @RequestParam(value = "id", defaultValue = "0") final int id) {

      companies = MapperCompanyDto.companyListToCompanyDto(crudCompanyServiceImpl.findAll());
      computerDto = MapperComputerDto.computerToComputerDto(crudComputerServiceImpl.find(id));
      model.addAttribute("companies", companies);
      model.addAttribute("computerDto", computerDto);
      return "editComputer";
    }
    /**
     * Post method for edit.
     * @param model : model
     * @param id : id
     * @param introduced : introduced
     * @param discontinued : discontinued
     * @param companyId : company id
     * @param computerName computer name
     * @return page redirection
     */
    @RequestMapping(method = RequestMethod.POST)
    public String doPost(ModelMap model,
            @RequestParam(value = "id", defaultValue = "") final int id,
            @RequestParam(value = "introduced", defaultValue = "") final String introduced,
            @RequestParam(value = "discontinued", defaultValue = "") final String discontinued,
            @RequestParam(value = "companyId", defaultValue = "") final int companyId,
            @RequestParam(value = "computerName", defaultValue = "") final String computerName) {

        Computer.ComputerBuilder computerBuilder = new Computer.ComputerBuilder().withId(id).withName(computerName);
        LocalDate introducedDate = null;
        if (StringUtils.isNotBlank(introduced) && DateValidator.formatIsValid(Optional.of(introduced))) {
            introducedDate = LocalDate.parse(introduced);
            computerBuilder.withDateWichIsIntroduced(introducedDate);
        }
        LocalDate discontinuedDate = null;
        if (StringUtils.isNotBlank(discontinued) && DateValidator.formatIsValid(Optional.of(discontinued))) {
            if (DateValidator.isRealTime(Optional.ofNullable(introducedDate), Optional.ofNullable(LocalDate.parse(discontinued)))) {
                discontinuedDate = LocalDate.parse(discontinued);
                computerBuilder.withDateWichIsDiscontinued(discontinuedDate);
            }
        }

        Company company = null;

        if (companyId != 0) {
            company = new Company.CompanyBuilder().withId(companyId).build();
            computerBuilder.withManufacturer(company);
        }
        crudComputerServiceImpl.update(Optional.ofNullable(computerBuilder.build()));

        return "redirect:springcdb";
    }
}
