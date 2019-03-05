package pl.tutors.util.validators;


import pl.tutors.util.IdentityCardUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdentityCardNumberValidator implements ConstraintValidator<IdentityCardNumber, String> {

	@Override
	public void initialize(IdentityCardNumber arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String identityCardNumber, ConstraintValidatorContext arg1) {

		return IdentityCardUtil.validate(identityCardNumber);
	}

	

}
