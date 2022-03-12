package com.soom.account_api.global;

import com.soom.account_api.global.data.type.ErrorType;

public interface PolicyType extends Violatable {
    String getName();
    ErrorType getViolationError();
}
