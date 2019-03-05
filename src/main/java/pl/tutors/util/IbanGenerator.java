package pl.tutors.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.stream.Collectors;

public final class IbanGenerator {

    public static final int INDIVIDUAL_ACCOUNT_NUMBER_LENGTH = 12;

    public static String generateRandom(String country, String bank, String mnemonic) {
        return generate(
                country,
                bank,
                mnemonic,
                RandomStringUtils.randomNumeric(INDIVIDUAL_ACCOUNT_NUMBER_LENGTH)
        );
    }

    public static String generate(String country, String bank, String mnemonic, String account) {
        return assemble(
                country,
                getChecksum(country, bank, mnemonic, account),
                bank,
                mnemonic,
                account
        );
    }

    private static String getChecksum(String country, String bank, String mnemonic, String account) {
        String rawNumber = assemble(country, "00", bank, mnemonic, account);
        rawNumber = rawNumber.substring(4) + rawNumber.substring(0, 4);
        rawNumber = swapLettersToNumbers(rawNumber);

        BigDecimal number = new BigDecimal(rawNumber);
        BigDecimal remainder = number.remainder(BigDecimal.valueOf(97));
        BigDecimal checksum = BigDecimal.valueOf(98).subtract(remainder);
        return checksum.toString();
    }

    private static String swapLettersToNumbers(String str) {
        return str.chars()
                .map(Character::getNumericValue)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    private static String assemble(String country, String checksum, String bank, String mnemonic, String account) {
        return String.format("%.2s%.2s%.8s%.4s%.12s",
                StringUtils.leftPad(country, 2, '0'),
                StringUtils.leftPad(checksum, 2, '0'),
                StringUtils.leftPad(bank, 8, '0'),
                StringUtils.leftPad(mnemonic, 4, '0'),
                StringUtils.leftPad(account, 12, '0')
        );
    }
}
