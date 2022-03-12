package com.soom.account_api.global.service;

import com.soom.account_api.global.data.response.ErrorResponse;
import com.soom.account_api.global.data.type.ErrorType;
import com.soom.account_api.global.property.ErrorResponseProperty;
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
