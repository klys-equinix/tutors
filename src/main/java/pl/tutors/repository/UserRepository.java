package pl.tutors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tutors.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findOneByEmailIgnoreCase(String email);
}
