package com.twocheckout;

public class TwocheckoutException extends Exception {
	private static final long serialVersionUID = 1L;

	public TwocheckoutException(String message) {
		super(message, null);
	}

	public TwocheckoutException(String message, Throwable e) {
		super(message, e);
	}
}
