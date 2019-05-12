package pl.tutors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.tutors.domain.Course;
import pl.tutors.domain.Offer;

import java.util.Optional;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findByOffersContaining(Offer offer);
}
