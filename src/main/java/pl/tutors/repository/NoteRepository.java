package pl.tutors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tutors.domain.User;
import pl.tutors.domain.Note;

import java.util.List;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    List<Note> findAllByUnrestrictedOrAuthorizedUsersContains(boolean unrestricted, User user);

    default List<Note> findAllForUser(User user) {
        return findAllByUnrestrictedOrAuthorizedUsersContains(true, user);
    }
}
