package pl.tutors.util;

import com.google.common.collect.ImmutableList;

public class NipUtil {

    private static ImmutableList<Integer> wages = ImmutableList.of(6, 5, 7, 2, 3, 4, 5, 6, 7);

    public static boolean validate(String nip) {
        if (nip == null)
            return false;

        nip = nip.replace("-", "");

        if(nip.length() != 10)
            return false;
        int count = 0;

        try {
            for (int i = 0; i < 9; i++)
                count += Integer.parseInt(nip.substring(i, i + 1)) * wages.get(i);
        } catch (NumberFormatException e) {

            return false;
        }

        int checksumNumber = Integer.parseInt(nip.substring(9, 10));

        count %= 11;
//        count = 10 - count;
//        count %= 10;

        return (count == checksumNumber);

    }
}
