package com.twocheckout;

public class TwocheckoutException extends Exception {
    private static final long serialVersionUID = 1L;

    protected final String code;

    public TwocheckoutException(String message) {
        super(message, null);
        this.code = "";
    }

    public TwocheckoutException(String message, String errorCode) {
        super(message, null);
        this.code = errorCode;
    }

    public TwocheckoutException(String message, Throwable e) {
        super(message, e);
        this.code = "";
    }

    public String getCode() {
        return this.code;
    }
}
