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
import pl.tutors.rest.dtos.RegistrationUserDTO;
import pl.tutors.service.UserManagementFacade;

@Service
@RequiredArgsConstructor
public class Bootstrap implements InitializingBean {

    private final UserManagementFacade userManagementFacade;

    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;

    @Override
    public void afterPropertiesSet() throws Exception {
        TransactionTemplate tmpl = new TransactionTemplate(txManager);
        tmpl.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                userManagementFacade.registerUser(RegistrationUserDTO.builder().email("mock@mock.pl").password("password").build());
                userManagementFacade.registerUser(RegistrationUserDTO.builder().email("mock1@mock.pl").password("password").build());
            }
        });
    }
}
