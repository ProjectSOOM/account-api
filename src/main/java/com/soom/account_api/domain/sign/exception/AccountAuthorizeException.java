package com.soom.account_api.domain.sign.exception;

import lombok.Getter;

@Getter
public class AccountAuthorizeException extends RuntimeException{
    private final AuthorizeType type;
    private final String data;

    public AccountAuthorizeException(String msg, AuthorizeType type, String data) {
        super(msg);
        this.type = type;
        this.data = data;
    }

    public enum AuthorizeType {
        EMAIL, PASSWORD
    }
}
