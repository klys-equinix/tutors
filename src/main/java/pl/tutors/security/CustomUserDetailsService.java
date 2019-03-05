package pl.tutors.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.tutors.exception.UserNotActivatedException;
import pl.tutors.repository.UserRepository;

import java.util.stream.Collectors;

@Component("userDetailsService")
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        log.debug("Authenticating {}", email);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        val user = userRepository.findOneByEmailIgnoreCase(email);
        return user.map(u -> {
            if (!u.isActivated() || u.getAttemptedLogins() > 3) {
                throw new UserNotActivatedException(email);
            }
            return new User(
                    email,
                    u.getPassword(),
                    u.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
        }).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s was not found in the database", email)));
    }
}
