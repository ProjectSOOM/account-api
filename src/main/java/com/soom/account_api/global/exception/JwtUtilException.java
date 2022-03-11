package com.soom.account_api.global.exception;

public class JwtUtilException extends RuntimeException {
    public JwtUtilException(String message, Throwable cause) {
        super(message, cause);
    }
}
