package com.anrisys.teamflow.shared.security;

public interface PasswordEncoder {
	String encode(String password);
	boolean matches(String rawPassword, String encodePassword);
}
