package de.mannheimer.imd.avw.api.commons;

/**
 * This Assert class is a helper to to validate objects in just one line of code
 * <p>
 * This is mainly to avoid writing redundant code. Each validating method throws
 * a special Exception when the test fails or returns true if validation
 * succeeds.
 * <p>
 * Example:
 * 
 * <pre>
 * <code>
 * String str = null
 * Assert.isNull(str);  // returns true
 * Assert.notNull(str); // throws IllegalArgumentException
 * </code>
 * </pre>
 * 
 * @author Dennis Ahaus
 * 
 */
public class Assert {

	/**
	 * Checks if given object is null.
	 * 
	 * @param object
	 *            The object to test
	 * @return Returns true if the object is null, else throws
	 *         {@link IllegalArgumentException} with default message
	 * @see #isNull(String, Object)
	 */
	public static boolean isNull(Object object) {

		return isNull("Object is NOT null!", object);
	}

	/**
	 * Checks if given object is null.
	 * 
	 * @param object
	 *            The object to test
	 * @param message
	 *            The exception message
	 * @return Returns true if the object is null, else throws
	 *         {@link IllegalArgumentException} with given message
	 */
	public static boolean isNull(String message, Object object) {

		if (object == null) {
			return true;
		}
		throw new IllegalArgumentException(message);
	}

	/**
	 * Checks if given object is NOT null.
	 * 
	 * @param object
	 *            The object to test
	 * @return Returns true if the object is NOT null, else throws
	 *         {@link IllegalArgumentException} with default message
	 * @see #isNull(String, Object)
	 */
	public static boolean notNull(Object object) {

		return notNull("Object is null!", object);
	}

	/**
	 * Checks if given object is NOT null.
	 * 
	 * @param object
	 *            The object to test
	 * @param message
	 *            The exception message
	 * @return Returns true if the object is NOT null, else throws
	 *         {@link IllegalArgumentException} with given message
	 */
	public static boolean notNull(String message, Object object) {

		if (object != null) {
			return true;
		}
		throw new IllegalArgumentException(message);
	}

}
