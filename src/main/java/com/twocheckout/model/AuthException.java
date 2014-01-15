package com.twocheckout.model;

public class AuthException {
    private String errorMsg;
    private String errorCode;
    private String httpStatus;

    public String getMessage() {
        return errorMsg;
    }
    public String getCode() {
        return errorCode;
    }
    public String getStatus() { return httpStatus; }
}
