package pl.tutors.util;

import com.google.common.collect.ImmutableList;

public class PeselNumberUtil {
	private static ImmutableList<Integer> wages = ImmutableList.of().of(1, 3, 7, 9, 1, 3, 7 ,9 ,1 ,3);

	public static boolean validate(String pesel) {

		if (pesel == null || pesel.length() != 11)
			return false;

		int count = 0;

		try {
			for (int i = 0; i < 10; i++)
				count += Integer.parseInt(pesel.substring(i, i + 1)) * wages.get(i);
		} catch (NumberFormatException e) {

			return false;
		}

		int checksumNumber = Integer.parseInt(pesel.substring(10, 11));

		count %= 10;
		count = 10 - count;
		count %= 10;

		return (count == checksumNumber);

	}

}
