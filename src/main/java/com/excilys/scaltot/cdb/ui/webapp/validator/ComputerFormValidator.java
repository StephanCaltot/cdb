package com.excilys.scaltot.cdb.ui.webapp.validator;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.excilys.scaltot.cdb.entities.computer.ComputerDto;
import com.excilys.scaltot.cdb.validation.StringValidator;

/**
 * @author Caltot Stéphan
 *
 * 4 avr. 2017
 */
@Component
public class ComputerFormValidator implements Validator {
    static final Logger LOGGER = LoggerFactory.getLogger(ComputerFormValidator.class.getName());

    @Override
    public boolean supports(Class<?> clazz) {
        return ComputerDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ComputerDto computer = (ComputerDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "computerName", "NotEmpty.computerForm.name");

        if (!StringValidator.isFormed(Optional.of(computer.getComputerName()))) {
            errors.rejectValue("computerName", "ForbiddenCaracters.computerForm.name");
        }

        if (StringUtils.isNotBlank(computer.getDateWichIsDiscontinued()) && StringUtils.isNotBlank(computer.getDateWichIsIntroduced())) {
            LocalDate introduced   = LocalDate.parse(computer.getDateWichIsIntroduced());
            LocalDate discontinued = LocalDate.parse(computer.getDateWichIsDiscontinued());

            if (discontinued.isBefore(introduced)) {
                errors.rejectValue("dateWichIsDiscontinued", "NotBeforeIntroduced.computerForm.discontinued");
            }
        }
    }
}