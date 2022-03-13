package com.soom.account_api.global.error.service;

import com.soom.account_api.global.error.data.response.ErrorResponse;
import com.soom.account_api.global.error.data.type.ErrorType;

public interface ErrorService {
    ErrorResponse getErrorResponse(ErrorType type);
}
