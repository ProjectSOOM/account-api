package com.soom.account_api.global.data.response;

import com.soom.account_api.global.data.type.ErrorType;

public record ErrorResponse(
        ErrorType type,
        String message,
        String solution
) {
}
