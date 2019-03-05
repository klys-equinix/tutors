package pl.tutors.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import pl.tutors.config.Constants;

/**
 * Utility class used to generate random values
 */
@UtilityClass
public class RandomUtil {
    /**
     * Generates random alphanumeric string. Used to generate reset password key for user
     * @return generated string
     */
    public String generateResetKey() {
        return RandomStringUtils.randomAlphanumeric(Constants.RESET_KEY_LENGTH);
    }

    /**
     * Generates random numeric string. Used as sms password to verify user
     * @return generated string
     */
    public String generateVerificationKey() {
        return RandomStringUtils.randomNumeric(Constants.VERIFICATION_KEY_LENGTH);
    }
}
