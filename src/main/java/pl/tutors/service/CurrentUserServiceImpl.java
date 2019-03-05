package pl.tutors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tutors.domain.User;
import pl.tutors.repository.UserRepository;
import pl.tutors.util.SecurityUtil;

@Service
@RequiredArgsConstructor
class CurrentUserServiceImpl implements CurrentUserService {
    private final UserRepository userRepository;

    @Transactional
    public User getCurrentUser() {
        return userRepository.findOneByEmailIgnoreCase(SecurityUtil.getCurrentUserLogin()).orElse(null);
    }
}
