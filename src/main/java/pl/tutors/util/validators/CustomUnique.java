package pl.tutors.util.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CustomUniqueValidator.class})
public @interface CustomUnique {

    String message();

    CustomUniqueProperty property();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
