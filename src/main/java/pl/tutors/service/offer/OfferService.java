package pl.tutors.service.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tutors.domain.Lesson;
import pl.tutors.domain.Offer;
import pl.tutors.exception.CustomException;
import pl.tutors.repository.CourseRepository;
import pl.tutors.repository.OfferRepository;
import pl.tutors.rest.dtos.OfferDTO;
import pl.tutors.service.UserManagementFacade;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final UserManagementFacade userManagementFacade;
    private final CourseRepository courseRepository;
    private final OfferRepository offerRepository;

    @Transactional
    public void createOffer(OfferDTO offerDTO) {
        courseRepository.findById(offerDTO.getCourseId()).ifPresentOrElse(
                c -> {
                    var currentUser = userManagementFacade.getCurrentUser();
                    if(c.getOffers().stream().map(Offer::getCreatedBy).anyMatch(u -> u.equals(currentUser)))
                        throw new CustomException("Cannot duplicate offer");
                    c.getOffers().add(
                            Offer.builder()
                                    .day(offerDTO.getDay())
                                    .hour(offerDTO.getHour())
                                    .createdBy(userManagementFacade.getCurrentUser())
                                    .build()
                    );
                    courseRepository.save(c);
                },
                () -> {
                    throw new CustomException("No course with id");
                }
        );
    }

    @Transactional
    public void confirmOffer(UUID offerId) {
        offerRepository.findById(offerId).ifPresentOrElse(
                o -> courseRepository.findByOffersContaining(o).ifPresent(c -> {
                            c.getLessons().add(
                                    Lesson.builder()
                                            .day(o.getDay())
                                            .hour(o.getHour())
                                            .student(o.getCreatedBy())
                                            .build()
                            );
                            c.getOffers().remove(o);
                            courseRepository.save(c);
                        }
                ),
                () -> {
                    throw new CustomException("No course with id");
                }
        );
    }

    @Transactional(readOnly = true)
    public List<Offer> findAllCreatedByMe() {
        return offerRepository.findAllByCreatedBy(userManagementFacade.getCurrentUser());
    }
}
