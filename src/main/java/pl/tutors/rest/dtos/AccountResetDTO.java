package pl.tutors.rest.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
public class AccountResetDTO {
    @Email
    public String email;
    @NotNull
    public UUID resetToken;
    @NotBlank
    public String password;
}
