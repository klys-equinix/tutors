package pl.tutors.service.tutorprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tutors.domain.TutorProfile;
import pl.tutors.domain.User;
import pl.tutors.exception.CustomException;
import pl.tutors.repository.UserRepository;
import pl.tutors.rest.dtos.CreateTutorProfileDTO;
import pl.tutors.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class TutorProfileServiceImpl implements TutorProfileService {
    private final UserRepository userRepository;

    @Override
    public User createTutorProfile(CreateTutorProfileDTO createTutorProfileDTO) {
        return userRepository.findOneByEmailIgnoreCase(SecurityUtil.getCurrentUserLogin())
                .map(user -> {
                    user.setTutorProfile(
                            TutorProfile.builder()
                                    .lat(createTutorProfileDTO.getLat())
                                    .lng(createTutorProfileDTO.getLng())
                                    .commuteRate(createTutorProfileDTO.getCommuteRate())
                                    .range(createTutorProfileDTO.getRange())
                                    .tutorsPlaceAvailable(createTutorProfileDTO.isTutorsPlaceAvailable())
                                    .build()
                    );
                    return userRepository.save(user);
                }).orElseThrow(() -> new CustomException("User not found"));
    }
}
