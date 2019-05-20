package pl.tutors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tutors.domain.Course;
import pl.tutors.repository.CourseRepository;
import pl.tutors.rest.dtos.CourseDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final UserManagementFacade userManagementFacade;

    @Transactional(readOnly = true)
    public List<CourseDTO> getCourses() {
        var currentUser = userManagementFacade.getCurrentUser();
        return currentUser.getTutorProfile().getCourses().stream().map(this::toDto).collect(Collectors.toList());
    }

    private CourseDTO toDto(Course course) {
        return CourseDTO.builder()
                .customName(course.getCustomName())
                .discipline(course.getDiscipline())
                .hourlyRate(course.getHourlyRate())
                .id(course.getId())
                .lessons(course.getLessons())
                .offers(course.getOffers())
                .level(course.getLevel())
                .build();
    }
}
