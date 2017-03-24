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
import com.excilys.scaltot.cdb.mappers.MapperCompanyDto;
import com.excilys.scaltot.cdb.services.interfaces.CrudCompanyService;
import com.excilys.scaltot.cdb.services.interfaces.CrudComputerService;
import com.excilys.scaltot.cdb.validation.DateValidator;

/**
 * @author Caltot Stéphan
 *
 * 23 mars 2017
 */
@Controller
@RequestMapping("/addComputer")
public class AddController {

    @Autowired
    private CrudComputerService crudComputerServiceImpl;
    @Autowired
    private CrudCompanyService crudCompanyServiceImpl;
    List<CompanyDto> companies;

    /**
     * Initializes companies for add computer page.
     * @param model : model
     * @return page redirection
     */
    @GetMapping
    public String doGet(ModelMap model) {

        companies = MapperCompanyDto.companyListToCompanyDto(crudCompanyServiceImpl.findAll());

        model.addAttribute("companies", companies);

        return "addComputer";
    }

    /**
     * Post method for add computer.
     * @param model : model
     * @param introduced : introduced
     * @param discontinued : discontinued
     * @param companyId : company id
     * @param computerName : computer name
     * @return page redirection
     */
    @PostMapping
    public String doPost(ModelMap model, @RequestParam MultiValueMap<String, String> parameters) {

        Computer.ComputerBuilder computerBuilder = null;
        
        if (parameters.containsKey("computerName")) {
            String computerName = parameters.get("computerName").get(0);
            computerBuilder = new Computer.ComputerBuilder().withName(computerName);
        }

        LocalDate introducedDate = null;
        String introduced = "";

        if (parameters.containsKey("introduced")) {
            introduced = parameters.get("introduced").get(0);
        }

        if (StringUtils.isNotBlank(introduced) && DateValidator.formatIsValid(Optional.of(introduced))) {
            introducedDate = LocalDate.parse(introduced);
            computerBuilder.withDateWichIsIntroduced(introducedDate);
        }

        LocalDate discontinuedDate = null;
        String discontinued = "";

        if (parameters.containsKey("discontinued")) {
            introduced = parameters.get("discontinued").get(0);
        }

        if (StringUtils.isNotBlank(discontinued) && DateValidator.formatIsValid(Optional.of(discontinued))) {
            if (DateValidator.isRealTime(Optional.ofNullable(introducedDate), Optional.ofNullable(LocalDate.parse(discontinued)))) {
                discontinuedDate = LocalDate.parse(discontinued);
                computerBuilder.withDateWichIsDiscontinued(discontinuedDate);
            }
        }

        Company company = null;

        if (parameters.containsKey("companyId")) {
            company = new Company.CompanyBuilder().withId(Integer.valueOf(parameters.get("companyId").get(0))).build();
            computerBuilder.withManufacturer(company);
        }

        crudComputerServiceImpl.create(Optional.ofNullable(computerBuilder.build()));

        return "redirect:springcdb";
    }
}
