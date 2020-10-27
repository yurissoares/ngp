package com.nativa.ngp.config;

public class SecurityConstants {

	public static final String SECRET = "LarDosIdososApi";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/usuarios/sign-up";
	public static final Long EXPIRATION_TIME = 60000L; //1 Dia
	
//	public static void main(String[] args) {
//		System.out.println(TimeUnit.MILLISECONDS.convert(30, TimeUnit.SECONDS));
//	}
	
}
