package pl.tutors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import pl.tutors.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>,
        QuerydslPredicateExecutor<User> {
    Optional<User> findOneByEmailIgnoreCase(String email);
}
