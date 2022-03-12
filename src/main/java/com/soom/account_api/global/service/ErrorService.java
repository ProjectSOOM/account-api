package com.soom.account_api.global.service;

import com.soom.account_api.global.data.response.ErrorResponse;
import com.soom.account_api.global.data.type.ErrorType;

public interface ErrorService {
    ErrorResponse getErrorResponse(ErrorType type);
}
