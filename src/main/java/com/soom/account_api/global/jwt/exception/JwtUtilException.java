package com.soom.account_api.global.jwt.exception;

import com.soom.account_api.global.error.data.type.ErrorType;
import lombok.Getter;

@Getter
public class JwtUtilException extends RuntimeException {
    private final ErrorType errorType;

    public JwtUtilException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }
}
