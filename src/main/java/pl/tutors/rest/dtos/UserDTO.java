package pl.tutors.rest.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.tutors.domain.User;
import pl.tutors.domain.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {
    @NotNull
    private UUID id;

    @NotBlank
    @Email(message = "{email.validation.invalid}")
    private String email;

    private Set<String> roles = new HashSet<>();
    private boolean activated;
    private boolean verified;
    private int smsResendTries;
    private UserDetails details;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.activated = user.isActivated();
        this.details = user.getDetails();
    }
}
