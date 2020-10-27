package com.nativa.ngp.config;

public class SecurityConstants {

	public static final String SECRET = "NgpWebApiRest";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users/sign-up";
	public static final Long EXPIRATION_TIME = 180000L;
	
//	public static void main(String[] args) {
//		System.out.println(TimeUnit.MILLISECONDS.convert(180, TimeUnit.SECONDS));
//	}
	
}
