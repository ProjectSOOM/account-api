package com.soom.account_api.domain.sign.exception;

import com.soom.account_api.global.account.data.PolicyType;
import lombok.Getter;

@Getter
public class PolicyViolationException extends RuntimeException {
    private final PolicyType policyType;

    public PolicyViolationException(PolicyType policyTypes) {
        super(String.format("\"%s\"정책을 위반하였습니다!", policyTypes.getName()));
        this.policyType = policyTypes;
    }
}
