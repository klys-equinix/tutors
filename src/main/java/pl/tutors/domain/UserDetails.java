package pl.tutors.domain;

import com.google.i18n.phonenumbers.NumberParseException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.tutors.exception.CustomException;
import pl.tutors.util.PhoneUtil;
import pl.tutors.util.validators.Pesel;
import pl.tutors.util.validators.PhoneNumberE164;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;


/**
 * Class with user details
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetails {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @PhoneNumberE164
    @Setter(AccessLevel.NONE)
    private String phoneNumber;

    public void setPhone(String phone) {
        try {
            this.phoneNumber = PhoneUtil.formatE164(phone);
        } catch (NumberParseException e) {
            throw new CustomException("phoneNumber.validation.invalid");
        }
    }
}
