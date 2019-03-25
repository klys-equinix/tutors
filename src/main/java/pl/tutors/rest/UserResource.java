package pl.tutors.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tutors.domain.UserDetails;
import pl.tutors.exception.CustomException;
import pl.tutors.rest.dtos.AccountResetDTO;
import pl.tutors.rest.dtos.PasswordResetDTO;
import pl.tutors.rest.dtos.RegistrationUserDTO;
import pl.tutors.rest.dtos.UserDTO;
import pl.tutors.service.UserManagementFacade;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserManagementFacade userManagementFacade;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> registerUser(@RequestBody @Valid RegistrationUserDTO registrationUserDto) {
        return ResponseEntity.ok(new UserDTO(userManagementFacade.registerUser(registrationUserDto)));
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(new UserDTO(userManagementFacade.getCurrentUser()));
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userManagementFacade.findAllUsers().stream().map(UserDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(new UserDTO(userManagementFacade.getUserById(userId)));
    }

    @RequestMapping(value = "/password", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void changePassword(@RequestBody PasswordResetDTO passwordResetDTO) throws CustomException {
        userManagementFacade.changePassword(passwordResetDTO, userManagementFacade.getCurrentUser());
    }

    @RequestMapping(value = "/account/reset", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void requestAccountReset(@RequestParam("email") String email) {
        userManagementFacade.requestAccountReset(email);
    }

    @RequestMapping(value = "/account/reset", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void resetAccount(@RequestBody AccountResetDTO accountResetDTO) throws CustomException {
        userManagementFacade.resetAccount(accountResetDTO);
    }

    @RequestMapping(value = "/{userId}/details", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> addDetailsForUser(@RequestBody @Valid UserDetails details, @PathVariable UUID userId) throws CustomException {
        return ResponseEntity.ok(new UserDTO(userManagementFacade.addDetailsForUser(details, userId)));
    }

}
