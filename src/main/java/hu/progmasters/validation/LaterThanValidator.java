package hu.progmasters.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import hu.progmasters.config.SpringWebConfig;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;


/**
 * Created by koxi on 7/20/2016.
 */
public class LaterThanValidator implements ConstraintValidator<LaterThan, LocalDate> {
    LocalDate date;

    @Override
    public void initialize(LaterThan constraintAnnotation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SpringWebConfig.DATE_FORMAT);
        this.date = LocalDate.parse(constraintAnnotation.value(), formatter);
    }

    @Override
    public boolean isValid(LocalDate dateToValidate, ConstraintValidatorContext constraintContext) {
        if (dateToValidate == null) {
            return true;
        }

        if (dateToValidate.isBefore(date)) {
            return false;
        } else {
            return true;
        }
    }
}
