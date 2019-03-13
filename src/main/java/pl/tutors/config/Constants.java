package pl.tutors.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final int RESET_KEY_LENGTH = 32;
    public static final int VERIFICATION_KEY_LENGTH = 10;

    @UtilityClass
    public static class Roles {
        public static final String USER = "ROLE_USER";
    }
}
