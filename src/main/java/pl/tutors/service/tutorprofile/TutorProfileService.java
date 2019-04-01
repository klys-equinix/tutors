package pl.tutors.service.tutorprofile;

import pl.tutors.domain.TutorProfile;
import pl.tutors.domain.User;
import pl.tutors.domain.UserDetails;
import pl.tutors.exception.CustomException;
import pl.tutors.rest.dtos.AccountResetDTO;
import pl.tutors.rest.dtos.CreateTutorProfileDTO;
import pl.tutors.rest.dtos.PasswordResetDTO;
import pl.tutors.rest.dtos.RegistrationUserDTO;

import java.util.List;
import java.util.UUID;


public interface TutorProfileService {
    User createTutorProfile(CreateTutorProfileDTO createTutorProfileDTO);
}
