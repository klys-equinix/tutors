package pl.tutors.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import pl.tutors.domain.User;
import pl.tutors.service.UserManagementFacade;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class LoginEventListener {
    private final UserManagementFacade userManagementFacade;
    private final HttpServletRequest request;
//
//    @EventListener
//    public void onAuthenticationSuccess(final AuthenticationSuccessEvent event) {
//        if (event.getSource() instanceof UsernamePasswordAuthenticationToken) {
//        } else if(event.getSource() instanceof OAuth2Authentication) {
//            final UserDetails userDetails = (UserDetails) event.getAuthentication().getPrincipal();
//            String ipAddress = request.getHeader("X-FORWARDED-FOR");
//            if (ipAddress == null)
//                ipAddress = request.getRemoteAddr();
//            userManagementFacade.logUsedIp(userDetails.getUsername(), ipAddress);
//        }
//    }
//
//    @EventListener
//    public void onAuthorizationFailure(final AuthorizationFailureEvent authorizationFailureEvent) {
//        authorizationFailureEvent.getAuthentication();
//    }
//
//    @EventListener
//    public void onBadCredentialsEvent(final AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
//        Authentication authentication = authenticationFailureBadCredentialsEvent.getAuthentication();
//        User user = userManagementFacade.getUserByEmail((String)authentication.getPrincipal());
//        if(user != null)
//            userManagementFacade.updateAttemptedLogins(user);
//    }
}
