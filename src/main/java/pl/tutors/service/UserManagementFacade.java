package pl.tutors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.tutors.domain.User;
import pl.tutors.exception.CustomException;
import pl.tutors.rest.dtos.AccountResetDTO;
import pl.tutors.rest.dtos.PasswordResetDTO;
import pl.tutors.rest.dtos.RegistrationUserDTO;

import java.util.List;
import java.util.UUID;

@Service
public class UserManagementFacade implements UserService, CurrentUserService {
    private UserService userService;
    private CurrentUserService currentUserService;

    @Autowired
    public UserManagementFacade(
            @Qualifier("userServiceImpl") UserService userService,
            @Qualifier("currentUserServiceImpl") CurrentUserService currentUserService)
    {
        this.userService = userService;
        this.currentUserService = currentUserService;
    }

    @Override
    public User getCurrentUser() {
        return currentUserService.getCurrentUser();
    }

    @Override
    public User registerUser(RegistrationUserDTO registrationUserDto) {
        return userService.registerUser(registrationUserDto);
    }

    @Override
    public User requestPasswordChange(String email) {
        return userService.requestPasswordChange(email);
    }

    @Override
    public User changePassword(PasswordResetDTO passwordResetDTO, User user) throws CustomException {
        return userService.changePassword(passwordResetDTO, user);
    }

    @Override
    public User getUserById(UUID userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @Override
    public void logUsedIp(String userEmail, String usedIp) {
        userService.logUsedIp(userEmail, usedIp);
    }

    @Override
    public User updateAttemptedLogins(User user) {
        return userService.updateAttemptedLogins(user);
    }

    @Override
    public User requestAccountReset(String email) {
        return userService.requestAccountReset(email);
    }

    @Override
    public User resetAccount(AccountResetDTO accountResetDTO) throws CustomException {
        return userService.resetAccount(accountResetDTO);
    }

}
