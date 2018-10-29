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

	/**
	 * Check whether the passed response is a success (code 200) or not.
	 * 
	 * @param httpResponse the response being checked
	 * @return true if the response is a success, false otherwise
	 * @throws RestClientCommunicationException
	 */
	public static boolean checkSuccess(HttpResponse httpResponse) throws RestClientCommunicationException {
		return checkSuccess(httpResponse, 200);
	}

	/**
	 * Check whether the passed response is a success or not. Also checks if the returned HTTP code is the expected
	 * one, logs a warning otherwise.
	 * 
	 * @param httpResponse the response being checked
	 * @return true if the response is a success, false otherwise
	 * @throws RestClientCommunicationException
	 */
	public static boolean checkSuccess(HttpResponse httpResponse, int expectedCode) throws RestClientCommunicationException {
		return checkSuccess(httpResponse, IS_TO_STRING, expectedCode);
	}

	/**
	 * Check whether the passed response is a success or not.<br/>
	 * In case of success (codes 2XX), also checks if the return code is the one expected. If it's not logs a warning.<br/>
	 * In case of error (codes 3XX, 4XX, 5XX, etc), logs the response and throws an exception.
	 * 
	 * @param httpResponse the response being checked
	 * @param errorResponseReader a function able to read response {@link InputStream} into a {@link String}.
	 * In most case it's just a call to {@link IOUtils#toString()}, but in some other cases, it's handy to be
	 * more flexible (in case of compressed content for instance)
	 * @param expectedCode the expected HTTP code
	 * @return true if the response is a success, false otherwise
	 * @throws RestClientCommunicationException if code isn't 2XX
	 */
	public static boolean checkSuccess(HttpResponse httpResponse, Function<InputStream, String> errorResponseReader,
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
						+ errorResponseReader.apply(httpResponse.getEntity().getContent()));
				throw new RestClientCommunicationException(
						String.format("Error on REST call : redirected (code %1$d)", responseCode));
			// 4XX : Client error
			} else if (responseCode > 399 && responseCode < 500) {
				LOGGER.error("Error " + responseCode + " : "
						+ errorResponseReader.apply(httpResponse.getEntity().getContent()));
				throw new RestClientCommunicationException(
						String.format("Error on REST call : client error (code %1$d)", responseCode));
			// 5XX or someting else : Server error
			} else {
				LOGGER.error("Error " + responseCode + " : "
						+ errorResponseReader.apply(httpResponse.getEntity().getContent()));
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
