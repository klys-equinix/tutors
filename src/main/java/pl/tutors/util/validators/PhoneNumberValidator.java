package pl.tutors.util.validators;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberE164, String> {
    @Override
    public void initialize(PhoneNumberE164 phoneNumber) {
    }

    @Override
    public boolean isValid(String number, ConstraintValidatorContext arg1) {
        if(number == null)
            return false;
        Pattern p = Pattern.compile("^\\+?[1-9]\\d{1,14}$");
        Matcher m = p.matcher(number);
        return !StringUtils.isEmpty(number) && m.find();
    }
}
