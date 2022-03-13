package com.soom.account_api.domain.sign.advice;

import com.soom.account_api.domain.sign.controller.SigninController;
import com.soom.account_api.domain.sign.controller.SignupController;
import com.soom.account_api.domain.sign.exception.AccountAuthorizeException;
import com.soom.account_api.global.error.data.response.ErrorResponse;
import com.soom.account_api.global.error.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = {SigninController.class, SignupController.class})
@RequiredArgsConstructor
public class SignAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(AccountAuthorizeException.class)
    public ResponseEntity<ErrorResponse> handling(AccountAuthorizeException e) {
        return ResponseEntity.badRequest().body(
                errorService.getErrorResponse(e.getType().getViolationError()));
    }
}
