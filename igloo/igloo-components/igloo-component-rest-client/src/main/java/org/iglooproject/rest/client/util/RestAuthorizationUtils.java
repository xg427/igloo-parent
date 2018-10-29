package org.iglooproject.rest.client.util;

public final class RestAuthorizationUtils {

	public static final String BEARER = "Bearer ";
	public static final String BASIC = "Basic ";

	/**
	 * Extract token from header "Authorization: Bearer [token]"
	 */
	public static String extractToken(String authorizationHeader) {
		if (!authorizationHeader.startsWith(BEARER)) {
			throw new IllegalArgumentException("Unexpected 'Authorization' header format. Expected format : "
				+ "'Authorization: Bearer [token]'");
		}
		return authorizationHeader.substring(BEARER.length(), authorizationHeader.length()).trim();
	}

	private RestAuthorizationUtils () { }
}
