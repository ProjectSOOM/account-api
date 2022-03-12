package com.soom.account_api.global;

import com.soom.account_api.global.data.type.ErrorType;

public interface Violatable {
    ErrorType getViolationError();
}
