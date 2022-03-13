package com.soom.account_api.domain.sign.data.type;

import com.soom.account_api.global.PolicyType;
import com.soom.account_api.global.error.data.type.ErrorType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SignupPolicyType implements PolicyType {
    EMAIL_POLICY(ErrorType.EMAIL_POLICY_VIOLATION),
    PASSWORD_POLICY(ErrorType.PASSWORD_POLICY_VIOLATION),
    NAME_POLICY(ErrorType.NAME_POLICY_VIOLATION),
    BIRTH_POLICY(ErrorType.BIRTH_POLICY_VIOLATION),
    TEACHER_CODE_POLICY(ErrorType.TEACHER_CODE_POLICY_VIOLATION),
    STUDENT_SCHOOL_NUMBER_POLICY(ErrorType.STUDENT_SCHOOL_NUMBER_POLICY_VIOLATION),
    STUDENT_ADMISSION_YEAR_POLICY(ErrorType.STUDENT_ADMISSION_YEAR_POLICY_VIOLATION);

    private final ErrorType violationError;

    @Override
    public String getName() {
        return name();
    }

    @Override
    public ErrorType getViolationError() {
        return violationError;
    }

}
