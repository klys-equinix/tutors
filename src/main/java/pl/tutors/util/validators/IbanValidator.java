package pl.tutors.util.validators;


import org.apache.commons.validator.routines.IBANValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IbanValidator implements ConstraintValidator<Iban, String> {

    @Override
    public void initialize(Iban arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isValid(String iban, ConstraintValidatorContext arg1) {
        return IBANValidator.DEFAULT_IBAN_VALIDATOR.isValid(iban);
    }


}
