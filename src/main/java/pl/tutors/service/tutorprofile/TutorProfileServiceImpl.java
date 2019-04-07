package pl.tutors.service.tutorprofile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tutors.domain.TutorProfile;
import pl.tutors.domain.User;
import pl.tutors.exception.CustomException;
import pl.tutors.repository.UserRepository;
import pl.tutors.util.SecurityUtil;

@Service
@RequiredArgsConstructor
public class TutorProfileServiceImpl implements TutorProfileService {
    private final UserRepository userRepository;

    @Override
    public User createTutorProfile(TutorProfile tutorProfile) {
        return userRepository.findOneByEmailIgnoreCase(SecurityUtil.getCurrentUserLogin())
                .map(user -> {
                    user.setTutorProfile(
                            tutorProfile
                    );
                    return userRepository.save(user);
                }).orElseThrow(() -> new CustomException("User not found"));
    }
}
