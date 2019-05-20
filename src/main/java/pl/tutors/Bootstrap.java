package pl.tutors;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import pl.tutors.domain.Course;
import pl.tutors.domain.Offer;
import pl.tutors.domain.TutorProfile;
import pl.tutors.domain.User;
import pl.tutors.domain.UserDetails;
import pl.tutors.domain.dictionary.Discipline;
import pl.tutors.domain.dictionary.Level;
import pl.tutors.repository.UserRepository;
import pl.tutors.rest.dtos.RegistrationUserDTO;
import pl.tutors.service.UserManagementFacade;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class Bootstrap implements InitializingBean {

    private final UserManagementFacade userManagementFacade;
    private final UserRepository userRepository;

    private static final double lat = 52.237049;
    private static final double lng = 21.017528;

    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        Random random = new Random();
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                User user = userManagementFacade.registerUser(RegistrationUserDTO.builder().email("mock@mock.pl").password("password").build());
                User user2 = userManagementFacade.registerUser(RegistrationUserDTO.builder().email("mock1@mock.pl").password("password").build());
                user.setTutorProfile(
                        TutorProfile.builder()
                                .lat(52.237049)
                                .lng(21.017532)
                                .range(3)
                                .commuteRate(1)
                                .courses(
                                        Collections.singletonList(
                                                Course.builder()
                                                        .level(Level.ELEMENTARY)
                                                        .discipline(Discipline.POLISH)
                                                        .customName("k1")
                                                        .hourlyRate(10l)
                                                        .offers(
                                                                Collections.singletonList(
                                                                        Offer.builder()
                                                                                .createdBy(user2)
                                                                                .hour("12:00")
                                                                                .day("Poniedziałek")
                                                                                .createdBy(user2)
                                                                                .build()
                                                                )
                                                        ).build()
                                        )
                                )
                                .build()
                );
                user.setDetails(UserDetails.builder().firstName("a").addressText("Adres 1").lastName("b").phoneNumber("333333333").build());
                user2.setTutorProfile(
                        TutorProfile.builder()
                                .lat(52.238049)
                                .lng(21.015532)
                                .range(2.5)
                                .commuteRate(10)
                                .courses(Collections.singletonList(
                                        Course.builder()
                                                .customName("Kurs 1")
                                                .discipline(Discipline.POLISH)
                                                .level(Level.ELEMENTARY)
                                                .build()
                                ))
                                .build()
                );
                user2.setDetails(UserDetails.builder().firstName("c").lastName("d").addressText("Adres 2").phoneNumber("333333333").build());
                userRepository.save(user);


                IntStream.range(0, 20).forEach(i -> {
                    var u = userManagementFacade.registerUser(
                            RegistrationUserDTO.builder()
                                    .email("mock+" + i + "@mock.pl")
                                    .password("password")
                                    .build()
                    );

                    u.setDetails(
                            UserDetails.builder()
                                    .firstName(generateRandomString())
                                    .lastName(generateRandomString())
                                    .addressText("Adres Testowy " + i)
                                    .phoneNumber(getRandomStringFromValues("0123456789", 9))
                                    .build()
                    );
                    var sign = i / 2 == 0 ? 1 : -1;
                    u.setTutorProfile(
                            TutorProfile.builder()
                                    .lat(52.237049 + (random.nextDouble() / 5 * sign))
                                    .lng(21.017532 + (random.nextDouble() / 5 * sign))
                                    .range(5)
                                    .commuteRate(i)
                                    .courses(
                                            Collections.singletonList(
                                                    Course.builder()
                                                            .level(Level.values()[i % 3])
                                                            .discipline(Discipline.values()[i % 6])
                                                            .customName("K" + i)
                                                            .hourlyRate(i * 10)
                                                            .build()
                                            )
                                    )
                                    .build()
                    );
                    userRepository.save(user);
                });
            }
        });
    }

    public static String generateRandomString() {
        return getRandomStringFromValues("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 8);
    }

    private static String getRandomStringFromValues(String allowedValues, Integer strLength) {
        StringBuilder stringBuilder = new StringBuilder();
        IntStream.range(0, strLength)
                .map(i -> ThreadLocalRandom.current().nextInt(0, allowedValues.length()))
                .forEach(v -> stringBuilder.append(allowedValues.charAt(v)));
        return stringBuilder.toString();
    }
}
