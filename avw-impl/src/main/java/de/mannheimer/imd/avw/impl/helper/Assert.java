package de.mannheimer.imd.avw.impl.helper;

/**
 * @author Dennis Ahaus
 * 
 */
public class Assert {

	public static boolean isNull(Object o) {

		if (o == null) {
			return true;
		}

		throw new IllegalArgumentException("Object is not null!");
	}

	public static boolean notNull(Object o) {

		if (o != null) {
			return true;
		}

		throw new IllegalArgumentException("Object is null!");
	}

}
