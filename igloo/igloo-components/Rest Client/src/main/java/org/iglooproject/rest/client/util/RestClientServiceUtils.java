package org.iglooproject.rest.client.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.iglooproject.rest.client.exception.RestClientCommunicationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RestClientServiceUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestClientServiceUtils.class);

	private static final Function<InputStream, String> IS_TO_STRING = new IsToStringFunction();

	public static boolean checkSuccess(HttpResponse httpResponse) throws RestClientCommunicationException {
		return checkSuccess(httpResponse, 200);
	}

	public static boolean checkSuccess(HttpResponse httpResponse, int expectedCode) throws RestClientCommunicationException {
		return checkSuccess(httpResponse, IS_TO_STRING, expectedCode);
	}

	// TODO JGO : renommer le paramètres Function + java doc bien explicite sur le sujet
	public static boolean checkSuccess(HttpResponse httpResponse, Function<InputStream, String> readResponseFunction,
		int expectedCode) throws RestClientCommunicationException {
		int responseCode = httpResponse.getStatusLine().getStatusCode();
		
		try {
			// 2XX : OK
			if (responseCode > 199 && responseCode < 300) {
				if (expectedCode != responseCode) {
					LOGGER.warn("Expected status was {}, but REST call returned {}", expectedCode, responseCode);
				}
				return true;
			// 3XX : Too many redirects
			} else if (responseCode > 299 && responseCode < 400) {
				LOGGER.error("Redirect " + responseCode + " : "
						+ readResponseFunction.apply(httpResponse.getEntity().getContent()));
				throw new RestClientCommunicationException(
						String.format("Error on REST call : redirected (code %1$d)", responseCode));
			// 4XX : Client error
			} else if (responseCode > 399 && responseCode < 500) {
				LOGGER.error("Error " + responseCode + " : "
						+ readResponseFunction.apply(httpResponse.getEntity().getContent()));
				throw new RestClientCommunicationException(
						String.format("Error on REST call : client error (code %1$d)", responseCode));
			// 5XX : Server error
			} else {
				LOGGER.error("Error " + responseCode + " : "
						+ readResponseFunction.apply(httpResponse.getEntity().getContent()));
				throw new RestClientCommunicationException(
						String.format("Error on REST call : server error (code %1$d)", responseCode));
			}
		} catch (UnsupportedOperationException | IOException e) {
			throw new RestClientCommunicationException("Unexpected error during REST call", e);
		}
	}

	private static class IsToStringFunction implements Function<InputStream, String> {
		@Override
		public String apply(InputStream is) {
			try {
				return IOUtils.toString(is);
			} catch (IOException e) {
				LOGGER.error("Error while reading REST call response", e);
				return null;
			}
		}
	}

	private RestClientServiceUtils() { }
}
