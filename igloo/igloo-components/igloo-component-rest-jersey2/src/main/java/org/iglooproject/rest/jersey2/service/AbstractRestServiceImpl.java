package org.iglooproject.rest.jersey2.service;

import org.iglooproject.rest.jersey2.util.exception.IRemoteApiError;
import org.iglooproject.rest.jersey2.util.exception.RemoteApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRestServiceImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRestServiceImpl.class);
	
	protected RemoteApiException getException(IRemoteApiError error) {
		return new RemoteApiException(error);
	}
	
	protected RemoteApiException getException(IRemoteApiError error, Throwable cause) {
		LOGGER.error(error.getCode() + " - " + error.getMessage(), cause);
		
		return new RemoteApiException(error, cause);
	}
}