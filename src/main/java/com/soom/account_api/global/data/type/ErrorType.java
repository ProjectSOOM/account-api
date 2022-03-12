package com.soom.account_api.global.data.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum ErrorType {
    UNKNOWN_ERROR("unknwon-error"),

    MISSING_JWT_TOKEN("missing-jwt-token"),
    EXPIRED_JWT_TOKEN("expired-jwt-token"),
    WRONG_JWT_TOKEN("wrong-jwt-token"),

    BIRTH_POLICY_VIOLATION("birth-policy-violation"),
    NAME_POLICY_VIOLATION("name-policy-violation"),
    EMAIL_POLICY_VIOLATION("email-policy-violation"),
    PASSWORD_POLICY_VIOLATION("password-policy-violation"),
    TEACHER_CODE_POLICY_VIOLATION("teacher-code-policy-violation"),

    WRONG_EMAIL("wrong-email"),
    WRONG_PASSWORD("wrong-password")
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
