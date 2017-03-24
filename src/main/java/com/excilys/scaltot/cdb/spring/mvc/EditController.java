package com.excilys.scaltot.cdb.spring.mvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @GetMapping
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
    @PostMapping
    public String doPost(ModelMap model, @RequestParam MultiValueMap<String, String> parameters) {

        Computer.ComputerBuilder computerBuilder = null;
        String computerName = "";
        long id = 0;

        if (parameters.containsKey("id") && parameters.containsKey("computerName")) {
            computerName = parameters.get("computerName").get(0);            
            id = Long.parseLong(parameters.get("id").get(0));

            computerBuilder = new Computer.ComputerBuilder().withId(id).withName(computerName);
        }

        LocalDate introducedDate = null;
        String introduced = null;
        if (StringUtils.isNotBlank(introduced) && DateValidator.formatIsValid(Optional.of(introduced))) {
            introduced = parameters.get("introduced").get(0);
            introducedDate = LocalDate.parse(introduced);

            computerBuilder.withDateWichIsIntroduced(introducedDate);
        }

        LocalDate discontinuedDate = null;
        String discontinued = null;
        if (StringUtils.isNotBlank(discontinued) && DateValidator.formatIsValid(Optional.of(discontinued))) {
            if (DateValidator.isRealTime(Optional.ofNullable(introducedDate), Optional.ofNullable(LocalDate.parse(discontinued)))) {
                discontinued = parameters.get("discontinued").get(0);
                discontinuedDate = LocalDate.parse(discontinued);

                computerBuilder.withDateWichIsDiscontinued(discontinuedDate);
            }
        }

        Company company = null;

        if (parameters.containsKey("companyId")) {
            company = new Company.CompanyBuilder().withId(Long.parseLong(parameters.get("companyId").get(0))).build();
            computerBuilder.withManufacturer(company);
        }

        crudComputerServiceImpl.update(Optional.ofNullable(computerBuilder.build()));

        return "redirect:springcdb";
    }
}
