package net.novoj;

/**
 * Number utils for demoing purposes.
 *
 * @author Jan Novotný (novotny@fg.cz), FG Forrest a.s. (c) 2022
 */
public class NumberUtils {

	/**
	 * Converts 4 bytes to integer.
	 */
	public static int bytesToInt(byte[] futureInt) {
		int theInt = 0;
		for (byte b : futureInt) {
			theInt = (theInt << 8) + (b & 0xFF);
		}
		return theInt;
	}

}
