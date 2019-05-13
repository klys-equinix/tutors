package pl.tutors.service.tutorprofile;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.tutors.domain.QUser;
import pl.tutors.domain.TutorProfile;
import pl.tutors.domain.User;
import pl.tutors.exception.CustomException;
import pl.tutors.query.UserProfileQuery;
import pl.tutors.repository.UserRepository;
import pl.tutors.rest.dtos.UpdateTutorProfileLocationDTO;
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
    @Transactional
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
        BooleanExpression predicate = formula.lt(query.radius * 2 * 1000);

        if(query.level != null)
            predicate = predicate.and(qUser.tutorProfile.courses.any().level.eq(query.level));

        if(query.discipline != null)
            predicate = predicate.and(qUser.tutorProfile.courses.any().discipline.eq(query.discipline));

        if(query.hourlyRate__goe != null)
            predicate = predicate.and(qUser.tutorProfile.courses.any().hourlyRate.goe(query.hourlyRate__goe));

        if(query.hourlyRate__loe != null)
            predicate = predicate.and(qUser.tutorProfile.courses.any().hourlyRate.loe(query.hourlyRate__loe));

        return (List<User>)makeCollection(userRepository.findAll(predicate));
    }

    @Override
    @Transactional
    public User updateTutorProfileLocation(UpdateTutorProfileLocationDTO updateTutorProfileLocationDTO) {
        return userRepository.findOneByEmailIgnoreCase(SecurityUtil.getCurrentUserLogin())
                .map(user -> {
                    user.getTutorProfile().setLat(updateTutorProfileLocationDTO.getLat());
                    user.getTutorProfile().setLng(updateTutorProfileLocationDTO.getLng());
                    return userRepository.save(user);
                }).orElseThrow(() -> new CustomException("User not found"));
    }

    public static <E> Collection<E> makeCollection(Iterable<E> iter) {
        Collection<E> list = new ArrayList<E>();
        for (E item : iter) {
            list.add(item);
        }
        return list;
    }
}
