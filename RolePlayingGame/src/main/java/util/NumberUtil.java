package util;

import java.security.SecureRandom;


public abstract class NumberUtil {

	/**
	 * Generate a secure random integer
	 * 
	 * @return int
	 */
	public static int generateSecureRandom() {
		SecureRandom number = new SecureRandom();
		int rand = number.nextInt();
		return rand > 0 ? rand : -1 * rand;
	}

}
