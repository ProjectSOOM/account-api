package com.soom.account_api.global.account.exception;

import lombok.Getter;

@Getter
public class UnknownSchoolTypeException extends RuntimeException {
    private final String name;
    public UnknownSchoolTypeException(String name) {
        super("해당 이름을 가진 학교를 찾을 수 없습니다!");
        this.name = name;
    }
}
