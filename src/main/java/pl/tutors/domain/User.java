package pl.tutors.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import pl.tutors.rest.dtos.RegistrationUserDTO;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Base class for users
 */

@Entity
@Data
@Table(name = "\"user\"")
@ToString(exclude = {"password", "resetKey", "tutorProfile"})
@EqualsAndHashCode(callSuper = false, of = {"id", "email"})
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    @JsonIgnore
    @Size(min = 60, max = 60)
    @Column(length = 60)
    private String password;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    private boolean activated = true;

    private UUID passwordResetToken;

    private LocalDateTime tokenCreatedAt;

    private int attemptedLogins = 0;

    @Embedded
    private UserDetails details;

    @ElementCollection
    @CollectionTable(name="Ips", joinColumns=@JoinColumn(name="user_id"))
    @Column(name="ip")
    public List<String> usedIps;

    @OneToOne(cascade = CascadeType.ALL)
    private TutorProfile tutorProfile;

    public User(RegistrationUserDTO registrationUserDto) {
        this.email = registrationUserDto.getEmail();
        this.password = registrationUserDto.getPassword();
        this.activated = true;
    }
}
