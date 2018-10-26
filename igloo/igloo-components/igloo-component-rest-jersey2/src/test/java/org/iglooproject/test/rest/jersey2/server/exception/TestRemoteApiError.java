package org.iglooproject.test.rest.jersey2.server.exception;

import org.iglooproject.rest.jersey2.util.exception.IRemoteApiError;

public enum TestRemoteApiError implements IRemoteApiError {

	UNKNOWN_SERVER_ERROR(90001, "unknown server error");

	private int code;

	private String message;

	private TestRemoteApiError(int code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
