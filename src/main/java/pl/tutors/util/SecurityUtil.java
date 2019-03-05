package pl.tutors.util;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class SecurityUtil {
    public static String getCurrentUserLogin() {
        val auth = SecurityContextHolder.getContext().getAuthentication();
        val principal = auth != null ? auth.getPrincipal() : null;

        if (principal != null) {
            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            } else if (principal instanceof String) {
                return (String) principal;
            }
        }

        return null;
    }
}
