package pl.tutors.service.usercrud;

import pl.tutors.domain.User;
import pl.tutors.domain.UserDetails;
import pl.tutors.exception.CustomException;
import pl.tutors.rest.dtos.AccountResetDTO;
import pl.tutors.rest.dtos.PasswordResetDTO;
import pl.tutors.rest.dtos.RegistrationUserDTO;

import java.util.List;
import java.util.UUID;


public interface UserService {
    User registerUser(RegistrationUserDTO registrationUserDto);
    User addDetailsForUser(UserDetails details, UUID userId) throws CustomException;
    User requestPasswordChange(String email);
    User changePassword(PasswordResetDTO passwordResetDTO, User user) throws CustomException;
    User getUserById(UUID userId);
    User getUserByEmail(String email);
    List<User> findAllUsers();

    void logUsedIp(String userEmail, String usedIp);

    User updateAttemptedLogins(User user);
    User requestAccountReset(String email);
    User resetAccount(AccountResetDTO accountResetDTO) throws CustomException;
}
