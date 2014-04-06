package de.mannheimer.imd.avw.api;

public interface IdGenerator {

	public String createUniqueId(int length);

	/**
	 * Create unique identifier with default length of 16
	 * 
	 * @return
	 */
	public String createUniqueId();

}
