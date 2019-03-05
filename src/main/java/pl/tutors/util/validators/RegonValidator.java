package pl.tutors.util.validators;

import pl.tutors.util.RegonUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegonValidator implements ConstraintValidator<Regon, String> {

    @Override
    public void initialize(Regon arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isValid(String regon, ConstraintValidatorContext arg1) {
        return RegonUtil.validate(regon);
    }
}
