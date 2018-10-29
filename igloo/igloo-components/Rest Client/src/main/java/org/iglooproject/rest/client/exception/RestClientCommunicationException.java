package org.iglooproject.rest.client.exception;

/**
 * Exception thrown when something goes wrong during a REST call to a server.
 * 
 * @author jgonzalez
 */
public class RestClientCommunicationException extends Exception {

	private static final long serialVersionUID = -5650397090267566367L;

	public RestClientCommunicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestClientCommunicationException(String message) {
		super(message);
	}

	public RestClientCommunicationException(Throwable cause) {
		super(cause);
	}
}
