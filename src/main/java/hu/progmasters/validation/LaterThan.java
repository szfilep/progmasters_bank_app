package hu.progmasters.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LaterThanValidator.class)
public @interface LaterThan {

    String message() default "date.not.valid";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String value();
}
