package com.soom.account_api.domain.authorize.exception;

public class UnknownCodeException extends RuntimeException {
    public UnknownCodeException(String code) {
        super("알 수 없는 코드 : " + code);
    }
}
