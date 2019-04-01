package pl.tutors.rest.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import pl.tutors.domain.Course;
import pl.tutors.domain.User;
import pl.tutors.domain.UserDetails;
import pl.tutors.domain.dictionary.Level;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateTutorProfileDTO {
    private long lat;
    private long lng;
    private long range;
    private long commuteRate;
    private boolean tutorsPlaceAvailable;
}
