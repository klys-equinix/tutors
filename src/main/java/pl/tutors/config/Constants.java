package pl.tutors.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String SYSTEM_AUDITOR = "system";

    public static final int MAX_COMPANY_ASSOCIATES = 3;
    public static final int MAX_COMPANY_PROPRIETORS = 2;

    public static final int MIN_NAME_SIZE = 2;
    public static final int MAX_NAME_SIZE = 255;

    public static final int PESEL_LENGTH = 11;
    public static final int ID_CARD_LENGTH = 9;
    public static final int NIP_LENGTH = 10;
    public static final int PHONE_SIZE = 12;
    public static final int ZIP_CODE_SIZE = 6;
    public static final int MAX_ADDRESS_NUM_SIZE = 10;

    public static final int RESET_KEY_LENGTH = 32;
    public static final int VERIFICATION_KEY_LENGTH = 10;

    @UtilityClass
    public static class Roles {
        public static final String USER = "ROLE_USER";
    }
}
