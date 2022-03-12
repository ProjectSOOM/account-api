package com.soom.account_api.domain.sign.exception;

import com.soom.account_api.global.Violatable;
import com.soom.account_api.global.data.type.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class AccountAuthorizeException extends RuntimeException{
    private final AuthorizeType type;
    private final String data;

    public AccountAuthorizeException(String msg, AuthorizeType type, String data) {
        super(msg);
        this.type = type;
        this.data = data;
    }

    @Getter
    @RequiredArgsConstructor
    public enum AuthorizeType implements Violatable {
        EMAIL(ErrorType.WRONG_EMAIL), PASSWORD(ErrorType.WRONG_PASSWORD);

        private final ErrorType violationError;

        @Override
        public ErrorType getViolationError() {
            return violationError;
        }
    }
}
