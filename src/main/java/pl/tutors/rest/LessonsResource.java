package pl.tutors.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.tutors.domain.Lesson;
import pl.tutors.domain.Offer;
import pl.tutors.rest.dtos.OfferDTO;
import pl.tutors.service.lesson.LessonService;
import pl.tutors.service.offer.OfferService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonsResource {
    private final LessonService lessonService;

    @GetMapping
    public List<Lesson> getAllByStudent() {
        return lessonService.findAllByStudent();
    }
}
