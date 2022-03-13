package com.soom.account_api.domain.profile.exception;

public class UnknownAccountException extends RuntimeException {
    public UnknownAccountException(Long accountId) {
        super("해당 ID 를 가진 계정을 찾을 수 없습니다!");
    }

    public UnknownAccountException(String email) {
        super("해당 Email 를 가진 계정을 찾을 수 없습니다!");
    }
}
