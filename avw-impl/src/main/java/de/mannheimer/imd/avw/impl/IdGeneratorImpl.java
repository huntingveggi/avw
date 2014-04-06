package de.mannheimer.imd.avw.impl;

import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import de.mannheimer.imd.avw.api.IdGenerator;

/**
 * @author Dennis Ahaus
 * 
 */
@Service
@Scope("prototype")
public class IdGeneratorImpl implements IdGenerator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.mannheimer.imd.avw.api.IdGenerator#createUniqueId()
	 */
	@Override
	public String createUniqueId() {
		return createUniqueId(16);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.mannheimer.imd.avw.api.IdGenerator#createUniqueId(int)
	 */
	@Override
	public String createUniqueId(int length) {

		// define the tokens to create the random chars from
		String tokens = "1Aa2Bb3Cc4Dd5Ee6Ff7Gg8Hh9Ii0Jj1Kk2Ll3Mm4Nn5Oo6Pp7Qq8Rr9Ss0Tt1Uu2Vv3Ww4Xx5Yy6Zz7";

		// build the random string from
		char[] chars = tokens.toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}

		return sb.toString();
	}

}
