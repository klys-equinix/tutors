package pl.tutors.rest.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import pl.tutors.util.validators.Password;

@Data
@NoArgsConstructor
public class PasswordResetDTO {
    @NotBlank
    @Password
    public String password;
}
