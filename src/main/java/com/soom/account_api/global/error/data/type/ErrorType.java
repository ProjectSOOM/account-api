package com.soom.account_api.global.error.data.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum ErrorType {
    UNKNOWN_ERROR("unknown-error"),

    //토큰 사용시
    MISSING_JWT_TOKEN("missing-jwt-token"),
    EXPIRED_JWT_TOKEN("expired-jwt-token"),
    WRONG_JWT_TOKEN("wrong-jwt-token"),

    //회원가입시
    BIRTH_POLICY_VIOLATION("birth-policy-violation"),
    NAME_POLICY_VIOLATION("name-policy-violation"),
    EMAIL_POLICY_VIOLATION("email-policy-violation"),
    PASSWORD_POLICY_VIOLATION("password-policy-violation"),
    TEACHER_CODE_POLICY_VIOLATION("teacher-code-policy-violation"),
    STUDENT_ADMISSION_YEAR_POLICY_VIOLATION("student-admission-policy-violation"),
    STUDENT_SCHOOL_NUMBER_POLICY_VIOLATION("student-school-number"),

    //로그인시
    WRONG_EMAIL("wrong-email"),
    WRONG_PASSWORD("wrong-password"),

    //메일인증시
    UNKNOWN_AUTHORIZE_CODE("unknown-authorize-code"),
    UNKNOWN_EMAIL("unknown-email")
    ;

    private final String propertyName;

    @JsonCreator
    public static ErrorType of(String name) {
        return Arrays.stream(values())
                .filter(type -> type.name().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름을 가진 ErrorType 을 찾을 수 없습니다 - " + name));
    }
}
