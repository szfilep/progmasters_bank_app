package hu.progmasters.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransferAmountValidator implements ConstraintValidator<TransferAmount, Object> {

    @Override
    public void initialize(TransferAmount constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object number, ConstraintValidatorContext context) {
        if (number instanceof Integer) {
            Integer num = (Integer) number;
            return (num > 0 && num <= 1000);
        } else {
            return false;
        }
    }
}