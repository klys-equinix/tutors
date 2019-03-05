package pl.tutors.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import pl.tutors.util.validators.CustomUnique;
import pl.tutors.util.validators.CustomUniqueProperty;
import pl.tutors.util.validators.Password;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationUserDTO {

    @Email(message = "{email.validation.invalid}")
    @CustomUnique(property = CustomUniqueProperty.EMAIL, message = "{user.email.unique}")
    private String email;

    @Password
    private String password;

    private String affiliate;
}
