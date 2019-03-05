package pl.tutors.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class IdentityCardUtil {

	static final Map<Character, Integer> letterAssignments = new ImmutableMap.Builder<Character, Integer>()
			.put('A', 10)
			.put('B', 11)
			.put('C', 12)
			.put('D', 13)
			.put('E', 14)
			.put('F', 15)
			.put('G', 16)
			.put('H', 17)
			.put('I', 18)
			.put('J', 19)
			.put('K', 20)
			.put('L', 21)
			.put('M', 22)
			.put('N', 23)
			.put('O', 24)
			.put('P', 25)
			.put('Q', 26)
			.put('R', 27)
			.put('S', 28)
			.put('T', 29)
			.put('U', 30)
			.put('V', 31)
			.put('W', 32)
			.put('X', 33)
			.put('Y', 34)
			.put('Z', 35).build();
	static final ImmutableList<Integer> weigths = ImmutableList.of(7, 3, 1, 9, 7, 3, 1, 7, 3);

	public static boolean validate(String identityCardNumber) {
		identityCardNumber = identityCardNumber.toUpperCase();
		
	    if(!identityCardNumber.matches("^[A-Z]{3}\\d{6}$")) {
			
			return false;
		}
		int checkSum = 0;

		for (int i = 0; i < identityCardNumber.length(); i++) {
			if (i < 3) {
				checkSum += weigths.get(i) * letterAssignments.get(identityCardNumber.charAt(i));
			} else {
				checkSum += weigths.get(i) * Character.getNumericValue(identityCardNumber.charAt(i));
			}
		}

		checkSum %= 10;
		return checkSum == 0;

	}
}
