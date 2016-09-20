package hu.progmasters.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransferAmountValidator.class)
public @interface TransferAmount {

    String message() default "Az összeg 1 és 1000 közé eshet.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String value() default "";
}