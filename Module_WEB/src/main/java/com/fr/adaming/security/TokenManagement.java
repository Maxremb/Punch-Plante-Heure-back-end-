package com.fr.adaming.security;

import java.security.SecureRandom;
import java.util.Random;


public class TokenManagement {

	public static String generateToken() {
		
		Random random = new SecureRandom();
		
		String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+-!£&~#'@<>,.?!";
		int tokenSize = 20;
		StringBuilder token = new StringBuilder(tokenSize);
		
		
		for (int i=0; i<tokenSize; i++) {
			int n = random.nextInt(alphabet.length());
			token.append(alphabet.charAt(n));
		}
		
		return new String(token);
	}
	
}
