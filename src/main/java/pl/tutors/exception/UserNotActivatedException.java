package pl.tutors.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotActivatedException extends AuthenticationException {
    public UserNotActivatedException(String email) {
        super(String.format("User %s is not activated", email));
    }
}
