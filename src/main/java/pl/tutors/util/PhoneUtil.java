package pl.tutors.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class PhoneUtil {
    /**
     * Default region for libphonenumber, if no region is specified in free text input
     */
    public static final String DEFAULT_REGION = "PL";

    /**
     * Reformats free text phone number into standardized format
     *
     * @param number free text phone number
     * @return       formatted phone number
     * @throws NumberParseException if input number is not a proper phone number
     */
    public static String format(String number, PhoneNumberUtil.PhoneNumberFormat format) throws NumberParseException {
        log.debug("Formatting number {} to format {}", number, format);
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(number, DEFAULT_REGION);
        return phoneUtil.format(phoneNumber, format);
    }

    /**
     * Reformats free text phone number into standardized E164 format
     *
     * @param number free text phone number
     * @return       formatted phone number
     * @throws NumberParseException if input number is not a proper phone number
     */
    public static String formatE164(String number) throws NumberParseException {
        return format(number, PhoneNumberUtil.PhoneNumberFormat.E164);
    }
    /**
     * Reformats free text phone number into standardized international format
     *
     * @param number free text phone number
     * @return       formatted phone number
     * @throws NumberParseException if input number is not a proper phone number
     */
    public static String formatInternational(String number) throws NumberParseException {
        return format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
    }


}
