package com.soom.account_api.domain.sign.advice;

import com.soom.account_api.domain.sign.controller.SigninController;
import com.soom.account_api.domain.sign.controller.SignupController;
import com.soom.account_api.domain.sign.exception.AccountAuthorizeException;
import com.soom.account_api.global.data.response.ErrorResponse;
import com.soom.account_api.global.data.type.ErrorType;
import com.soom.account_api.global.service.ErrorService;
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
                switch (e.getType()) {
                    //TODO 다른방식의 Mapping 생각해보기
                    case EMAIL -> errorService.getErrorResponse(ErrorType.WRONG_EMAIL);
                    case PASSWORD -> errorService.getErrorResponse(ErrorType.WRONG_PASSWORD);
                    default -> errorService.getErrorResponse(ErrorType.UNKNOWN_ERROR);
                });
    }
}
