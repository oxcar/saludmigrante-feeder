package com.copili.feeder.util;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashUtils {

	private static final String DEFAULT_SALT = "t0j( #[qw|ve^cp.dW[qsjRLdwYR:>Ic!=S<gFQl }{-wW,ltb`y&gP2Gnw?UsN-";

	public static String hashSHA256( String input ) {
		ShaPasswordEncoder spe = new ShaPasswordEncoder( 256 );
		return spe.encodePassword( input, DEFAULT_SALT );
	}

	public static String bcrypt( String input ) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		return bcpe.encode( input );
	}

}
