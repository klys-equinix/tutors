package pl.tutors.util.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {PeselValidator.class})
public @interface Pesel {
	String message() default "{pesel.validation.invalid}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
