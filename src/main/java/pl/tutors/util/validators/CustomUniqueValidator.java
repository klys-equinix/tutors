package pl.tutors.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tutors.domain.User;
import pl.tutors.repository.UserRepository;
import pl.tutors.service.UserManagementFacade;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Service
public class CustomUniqueValidator implements ConstraintValidator<CustomUnique, String> {

    private CustomUniqueProperty property;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserManagementFacade userManagementFacade;

    @Override
    public void initialize(CustomUnique customUnique) {
        this.property = customUnique.property();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        User currentUser = userManagementFacade.getCurrentUser();
        switch (property) {
            case EMAIL:
                Optional<User> userOptional = userRepository.findOneByEmailIgnoreCase(value);
                return !userOptional.isPresent() ||
                        (currentUser != null && currentUser.getEmail() != null
                                && userOptional.get().getEmail().equalsIgnoreCase(currentUser.getEmail()));
            default:
                return false;
        }
    }
}
