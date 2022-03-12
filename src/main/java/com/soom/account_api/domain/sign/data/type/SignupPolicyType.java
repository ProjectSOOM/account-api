package com.soom.account_api.domain.sign.data.type;

import com.soom.account_api.global.PolicyType;

public enum SignupPolicyType implements PolicyType {
    EMAIL_POLICY, PASSWORD_POLICY, NAME_POLICY, BIRTH_POLICY, TEACHER_CODE_POLICY, STUDENT_SCHOOL_NUMBER_POLICY, STUDENT_ADMISSION_YEAR_POLICY;

    @Override
    public String getName() {
        return name();
    }
}
