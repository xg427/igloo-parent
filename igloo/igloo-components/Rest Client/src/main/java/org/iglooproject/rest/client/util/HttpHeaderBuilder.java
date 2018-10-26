package org.iglooproject.rest.client.util;

import java.util.Base64;
import java.util.Map;

import org.apache.commons.io.Charsets;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.protocol.HTTP;
import org.iglooproject.commons.util.mime.MediaType;

import com.google.common.collect.Maps;

public final class HttpHeaderBuilder {

	private final Map<String, String> headers = Maps.newHashMap();

	public static final HttpHeaderBuilder start() {
		return new HttpHeaderBuilder();
	}

	/**
	 * Cannot be directly instantiated, used {@link HttpHeaderBuilder#start()} instead.
	 */
	private HttpHeaderBuilder() {
	}

	public Map<String, String> build() {
		return headers;
	}

	public HttpHeaderBuilder connDirectiveClose() {
		headers.put(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
		return this;
	}

	public HttpHeaderBuilder contentTypeJson() {
		return this.contentType(MediaType.APPLICATION_JSON.mime());
	}

	public HttpHeaderBuilder contentTypeMultipartFormData() {
		// TODO JGO : find/add the right constant
		return this.contentType("multipart/form-data");
	}

	public HttpHeaderBuilder contentType(String contentType) {
		if (contentType != null) {
			headers.put(HttpHeaders.CONTENT_TYPE, contentType);
		}
		return this;
	}

	public HttpHeaderBuilder acceptJson() {
		return this.accept(MediaType.APPLICATION_JSON.mime());
	}

	public HttpHeaderBuilder accept(String accept) {
		if (accept != null) {
			headers.put(HttpHeaders.ACCEPT, accept);
		}
		return this;
	}

	public HttpHeaderBuilder authorizationBearer(String token) {
		if (token != null) {
			headers.put(HttpHeaders.AUTHORIZATION, RestAuthorizationUtils.BEARER + token);
		}
		return this;
	}

	public HttpHeaderBuilder authorizationBasic(String username, String password) {
		if (username != null && password != null) {
			String raw = username + ":" + password;
			String encoded = Base64.getEncoder().encodeToString(raw.getBytes(Charsets.UTF_8));
			headers.put(HttpHeaders.AUTHORIZATION, RestAuthorizationUtils.BASIC + encoded);
		}
		return this;
	}

	public HttpHeaderBuilder userAgent(String userAgent) {
		if (userAgent != null) {
			headers.put(HttpHeaders.USER_AGENT, userAgent);
		}
		return this;
	}

	public HttpHeaderBuilder header(String name, String value) {
		if (StringUtils.isNotEmpty(name) && StringUtils.isNotEmpty(value)) {
			headers.put(name, value);
		}
		return this;
	}
}
