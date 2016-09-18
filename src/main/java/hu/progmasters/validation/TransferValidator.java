package hu.progmasters.validation;

import hu.progmasters.domain.Transfer;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TransferValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Transfer.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Transfer transfer = (Transfer) target;

        if (transfer.getFrom().getBalance() - transfer.getAmount() < 0){
            errors.rejectValue("amount", "account.balanceNotEnough");
        }
    }
}
