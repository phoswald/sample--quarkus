package com.github.phoswald.sample.security;

import io.quarkus.elytron.security.common.BcryptUtil;

public class PasswordUtility {

	public static void main(String[] args) {
		String password = new String(System.console().readPassword("Enter password: "));
		System.out.println("bcrypt hash: " + BcryptUtil.bcryptHash(password));
	}
}
