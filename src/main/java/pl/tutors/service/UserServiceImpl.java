package pl.tutors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tutors.domain.User;
import pl.tutors.domain.UserDetails;
import pl.tutors.exception.CustomException;
import pl.tutors.repository.UserRepository;
import pl.tutors.rest.dtos.AccountResetDTO;
import pl.tutors.rest.dtos.PasswordResetDTO;
import pl.tutors.rest.dtos.RegistrationUserDTO;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


@Service
class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CurrentUserService currentUserService;
    private MailService mailService;

    @Value("${dev.sms.override:false}")
    private boolean overrideSmsCheck;

    @Autowired
    UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            MailService mailService,
            @Qualifier("currentUserServiceImpl") CurrentUserService currentUserService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUserService = currentUserService;
        this.mailService = mailService;
    }

    @Transactional
    public User registerUser(RegistrationUserDTO registrationUserDto) {
        registrationUserDto.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        return userRepository.save(new User(registrationUserDto));
    }

    @Transactional
    public User addDetailsForUser(UserDetails details, UUID userId) throws CustomException {
        var user = userRepository.findById(userId).orElseThrow(() -> new CustomException("User not found"));
        if (!Objects.equals(user, currentUserService.getCurrentUser()))
            throw new CustomException("user.edit.denied");
        user.setDetails(details);
        return userRepository.save(user);
    }

    @Transactional
    public User requestPasswordChange(String email) {
        var user = userRepository.findOneByEmailIgnoreCase(email).orElseThrow(() -> new EntityNotFoundException("user.notFound"));
        user = createTokenForUser(user);
        return userRepository.save(user);
    }

    @Transactional
    public User changePassword(PasswordResetDTO passwordResetDTO, User user) {
        user.setPassword(passwordEncoder.encode(passwordResetDTO.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }

    @Transactional
    public User getUserByEmail(String email) {
        return userRepository.findOneByEmailIgnoreCase(email).orElse(null);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User requestAccountReset(String email) {
        var user = userRepository.findOneByEmailIgnoreCase(email).orElseThrow(() -> new EntityNotFoundException("user.notFound"));
        user = createTokenForUser(user);
        mailService.sendPasswordResetEmail(user);
        return userRepository.save(user);
    }

    @Transactional
    public User resetAccount(AccountResetDTO accountResetDTO) throws CustomException {
        var user = userRepository.findOneByEmailIgnoreCase(accountResetDTO.getEmail()).orElseThrow(() -> new EntityNotFoundException("user.notFound"));
        checkTokenValidity(accountResetDTO, user);
        redeemToken(accountResetDTO, user);
        return userRepository.save(user);
    }

    @Transactional
    public void logUsedIp(String userEmail, String usedIp) {
        var user = userRepository.findOneByEmailIgnoreCase(userEmail).orElseThrow(() -> new EntityNotFoundException("user.notFound"));
        if(!user.getUsedIps().contains(usedIp)) {
            user.getUsedIps().add(usedIp);
            userRepository.save(user);
            mailService.notifyAboutLogin(user, usedIp);
        }

    }

    @Override
    @Transactional
    public User updateAttemptedLogins(User user) {
        user.setAttemptedLogins(user.getAttemptedLogins() + 1);
        return userRepository.save(user);
    }

    private User createTokenForUser(User user) {
        UUID token = UUID.randomUUID();
        user.setPasswordResetToken(token);
        user.setTokenCreatedAt(LocalDateTime.now());
        return user;
    }

    private void checkTokenValidity(AccountResetDTO passwordResetDTO, User user) throws CustomException {
        if (!user.getPasswordResetToken().equals(passwordResetDTO.getResetToken())
                || user.getTokenCreatedAt().isBefore(LocalDateTime.now().minusDays(1)))
            throw new CustomException("user.resetToken.invalid");
    }

    private void redeemToken(AccountResetDTO passwordResetDTO, User user) {
        user.setPasswordResetToken(null);
        user.setTokenCreatedAt(null);
        user.setPassword(passwordEncoder.encode(passwordResetDTO.getPassword()));
    }

}
