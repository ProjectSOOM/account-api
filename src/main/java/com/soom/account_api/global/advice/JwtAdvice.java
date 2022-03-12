package com.soom.account_api.global.advice;

import com.soom.account_api.global.data.response.ErrorResponse;
import com.soom.account_api.global.data.type.ErrorType;
import com.soom.account_api.global.exception.JwtDecodeException;
import com.soom.account_api.global.property.ErrorResponseProperty;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class JwtAdvice {
    private final ErrorResponseProperty errorResponseProperty;

    //JWT 오류 핸들링
    @ExceptionHandler(JwtDecodeException.class)
    public ResponseEntity<ErrorResponse> handling(JwtDecodeException e) {
        return ResponseEntity.badRequest().body(getErrorResponse(e.getCause()));
    }

    private ErrorResponse getErrorResponse(Throwable cause) {
        return switch (cause) {
            case IllegalArgumentException e -> getErrorResponse(ErrorType.MISSING_JWT_TOKEN);
            case ExpiredJwtException e -> getErrorResponse(ErrorType.EXPIRED_JWT_TOKEN);
            case UnsupportedJwtException e -> getErrorResponse(ErrorType.WRONG_JWT_TOKEN);
            case MalformedJwtException e -> getErrorResponse(ErrorType.WRONG_JWT_TOKEN);
            case SignatureException e -> getErrorResponse(ErrorType.WRONG_JWT_TOKEN);
            default -> getErrorResponse(ErrorType.UNKNOWN_ERROR);
        };
    }

    //TODO Error 관리서비스 만들어서 거기로 빼내기
    private ErrorResponse getErrorResponse(ErrorType type) {
        return errorResponseProperty.getProperties().get(type.getPropertyName());
    }
}
