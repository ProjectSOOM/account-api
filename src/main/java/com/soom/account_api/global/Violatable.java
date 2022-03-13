package com.soom.account_api.global;

import com.soom.account_api.global.error.data.type.ErrorType;

public interface Violatable {
    ErrorType getViolationError();
}
