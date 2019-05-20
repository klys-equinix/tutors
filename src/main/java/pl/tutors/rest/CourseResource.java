package pl.tutors.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tutors.rest.dtos.CourseDTO;
import pl.tutors.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseResource {
    private final CourseService courseService;

    @GetMapping
    public List<CourseDTO> getCourses() {
        return courseService.getCourses();
    }
}
