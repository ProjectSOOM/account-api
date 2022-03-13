package com.soom.account_api.global.jwt.advice;

import com.soom.account_api.global.error.data.response.ErrorResponse;
import com.soom.account_api.global.error.data.type.ErrorType;
import com.soom.account_api.global.jwt.exception.JwtDecodeException;
import com.soom.account_api.global.error.service.ErrorService;
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
    private final ErrorService errorService;

    //JWT 오류 핸들링
    @ExceptionHandler(JwtDecodeException.class)
    public ResponseEntity<ErrorResponse> handling(JwtDecodeException e) {
        return ResponseEntity
                .badRequest()
                .body(errorService.getErrorResponse(e.getErrorType()));
    }
}
