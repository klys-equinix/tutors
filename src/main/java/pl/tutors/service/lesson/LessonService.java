package pl.tutors.service.lesson;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tutors.domain.Lesson;
import pl.tutors.domain.Offer;
import pl.tutors.repository.LessonRepository;
import pl.tutors.service.UserManagementFacade;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final UserManagementFacade userManagementFacade;
    private final LessonRepository lessonRepository;


    @Transactional(readOnly = true)
    public List<Lesson> findAllByStudent() {
        return lessonRepository.findAllByStudent(userManagementFacade.getCurrentUser());
    }
}
