package pl.tutors.util.validators;

import pl.tutors.util.PeselNumberUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeselValidator implements ConstraintValidator<Pesel, String> {

	@Override
	public void initialize(Pesel arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String pesel, ConstraintValidatorContext arg1) {
		return PeselNumberUtil.validate(pesel);
	}
	
	
}
