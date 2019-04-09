package pl.tutors.service.tutorprofile;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tutors.domain.QUser;
import pl.tutors.domain.TutorProfile;
import pl.tutors.domain.User;
import pl.tutors.exception.CustomException;
import pl.tutors.query.UserProfileQuery;
import pl.tutors.repository.UserRepository;
import pl.tutors.util.SecurityUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.querydsl.core.types.dsl.MathExpressions.acos;
import static com.querydsl.core.types.dsl.MathExpressions.cos;
import static com.querydsl.core.types.dsl.MathExpressions.radians;
import static com.querydsl.core.types.dsl.MathExpressions.sin;

@Service
@RequiredArgsConstructor
public class TutorProfileServiceImpl implements TutorProfileService {
    private final UserRepository userRepository;

    private final static Long RADIUS_OF_EARTH = 6371L;

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

    @Override
    public List<User> readAllUserProfiles(UserProfileQuery query) {
        QUser qUser = QUser.user;
        NumberPath<Double> lat = qUser.tutorProfile.lat;
        NumberPath<Double> lng = qUser.tutorProfile.lng;
        NumberExpression<Double> formula =
                (acos(cos(radians(Expressions.constant(query.lat)))
                        .multiply(cos(radians(lat))
                                .multiply(cos(radians(lng).subtract(radians(Expressions.constant(query.lng)))
                                        .add(sin(radians(Expressions.constant(query.lat)))
                                                .multiply(sin(radians(lat))))))))
                        .multiply(Expressions.constant(RADIUS_OF_EARTH)));
        return (List<User>)makeCollection(userRepository.findAll(formula.lt(query.lat * 1000)));
    }

    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}
