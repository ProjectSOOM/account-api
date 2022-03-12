package com.soom.account_api.domain.sign.exception;

import lombok.Getter;

@Getter
public class WrongSchoolEmailException extends RuntimeException{
    private final String email;

    public WrongSchoolEmailException(String email) {
        super("해당이메일은 학교이메일이 아닙니다!");
        this.email = email;
    }
}
