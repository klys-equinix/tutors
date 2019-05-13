package pl.tutors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tutors.domain.Lesson;
import pl.tutors.domain.Offer;
import pl.tutors.domain.User;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
    List<Lesson> findAllByStudent(User student);
}
