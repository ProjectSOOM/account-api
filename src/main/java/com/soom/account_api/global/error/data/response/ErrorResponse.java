package com.soom.account_api.global.error.data.response;

import com.soom.account_api.global.error.data.type.ErrorType;

public record ErrorResponse(
        ErrorType type,
        String message,
        String solution
) {
}
