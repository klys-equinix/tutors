package pl.tutors.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

@UtilityClass
public class MailUtil {

    /**
     * Transforms email to lower case and removes all whitespaces
     *
     * @param email email to normalize
     * @return normalized email
     */
    public String normalize(String email) {
        return StringUtils.trimAllWhitespace(email.toLowerCase());
    }
}
