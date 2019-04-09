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
import pl.tutors.domain.TutorProfile;
import pl.tutors.domain.User;
import pl.tutors.repository.UserRepository;
import pl.tutors.rest.dtos.RegistrationUserDTO;
import pl.tutors.service.UserManagementFacade;

@Service
@RequiredArgsConstructor
public class Bootstrap implements InitializingBean {

    private final UserManagementFacade userManagementFacade;
    private final UserRepository userRepository;

    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                User user = userManagementFacade.registerUser(RegistrationUserDTO.builder().email("mock@mock.pl").password("password").build());
                userManagementFacade.registerUser(RegistrationUserDTO.builder().email("mock1@mock.pl").password("password").build());
                user.setTutorProfile(
                        TutorProfile.builder()
                                .lat(52.237049)
                                .lng(21.017532)
                                .range(10)
                                .commuteRate(1)
                                .build()
                );
                userRepository.save(user);
            }
        });
    }
}
