package com.pension.authorization.config;


public final class Paths {
	public static final String[] EXCLUDED_PATHS={"/actuator/**","/authenticate", "/authorize","/swagger-ui.html","/swagger-resources","/v2/api-docs","/webjars/**","/**"};
	public static final String[] URL_PATHS={"/auth/docs"};
	public static final String[] REDIRECT_PATHS={"/swagger-ui.html"};
}
