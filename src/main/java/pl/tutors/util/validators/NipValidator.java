package pl.tutors.util.validators;

import pl.tutors.util.NipUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NipValidator implements ConstraintValidator<Nip, String> {

    @Override
    public void initialize(Nip arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isValid(String nip, ConstraintValidatorContext arg1) {

        return NipUtil.validate(nip);
    }


}
