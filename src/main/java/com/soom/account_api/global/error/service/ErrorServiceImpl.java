package com.soom.account_api.global.error.service;

import com.soom.account_api.global.error.data.response.ErrorResponse;
import com.soom.account_api.global.error.data.type.ErrorType;
import com.soom.account_api.global.error.property.ErrorResponseProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ErrorServiceImpl implements ErrorService {
    private final ErrorResponseProperty errorResponseProperty;

    @Override
    public ErrorResponse getErrorResponse(ErrorType type) {
        return errorResponseProperty.getProperties().get(type.getPropertyName());
    }
}
