package pl.tutors.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.tutors.domain.TutorProfile;
import pl.tutors.domain.UserDetails;
import pl.tutors.exception.CustomException;
import pl.tutors.query.UserProfileQuery;
import pl.tutors.rest.dtos.AccountResetDTO;
import pl.tutors.rest.dtos.CreateTutorProfileDTO;
import pl.tutors.rest.dtos.PasswordResetDTO;
import pl.tutors.rest.dtos.RegistrationUserDTO;
import pl.tutors.rest.dtos.UserDTO;
import pl.tutors.service.UserManagementFacade;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/tutor-profile")
@RequiredArgsConstructor
public class TutorProfileResource {

    private final UserManagementFacade userManagementFacade;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> create(@RequestBody @Valid TutorProfile tutorProfile) {
        return ResponseEntity.ok(new UserDTO(userManagementFacade.createTutorProfile(tutorProfile)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> readAll(
            @Valid UserProfileQuery userProfileQuery
    ) {
        return ResponseEntity.ok(userManagementFacade.readAllUserProfiles(
                userProfileQuery
        ).stream().map(UserDTO::new).collect(Collectors.toList()));
    }

}
