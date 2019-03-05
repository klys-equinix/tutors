package pl.tutors.util;

import com.google.common.collect.ImmutableList;

public class RegonUtil {

    private static ImmutableList<Integer> wagesNineDigits = ImmutableList.of(8, 9, 2, 3, 4, 5, 6, 7);
    private static ImmutableList<Integer> wagesFourteenDigits = ImmutableList.of(2, 4, 8, 5, 0, 9, 7, 3, 6, 1, 2, 4, 8);


    public static boolean validate(String regon) {

        if (regon == null)
            return false;
        regon = regon.replace("-", "");
        if(regon.length() == 9) {
            int count = 0;

            try {
                for (int i = 0; i < 8; i++)
                    count += Integer.parseInt(regon.substring(i, i + 1)) * wagesNineDigits.get(i);
            } catch (NumberFormatException e) {

                return false;
            }

            int checksumNumber = Integer.parseInt(regon.substring(8, 9));

            count %= 11;
//        count = 10 - count;
//        count %= 10;

            return (count == checksumNumber);
        } else if (regon.length() == 14) {
            int count = 0;

            try {
                for (int i = 0; i < 13; i++)
                    count += Integer.parseInt(regon.substring(i, i + 1)) * wagesNineDigits.get(i);
            } catch (NumberFormatException e) {

                return false;
            }

            int checksumNumber = Integer.parseInt(regon.substring(13, 14));

            count %= 11;
//        count = 10 - count;
//        count %= 10;

            return (count == checksumNumber);
        } else {
            return false;
        }

    }
}
