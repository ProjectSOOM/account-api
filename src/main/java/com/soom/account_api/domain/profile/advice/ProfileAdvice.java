package com.soom.account_api.domain.profile.advice;

import com.soom.account_api.domain.authorize.exception.UnknownCodeException;
import com.soom.account_api.global.account.exception.UnknownAccountException;
import com.soom.account_api.global.error.data.response.ErrorResponse;
import com.soom.account_api.global.error.data.type.ErrorType;
import com.soom.account_api.global.error.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class ProfileAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(UnknownCodeException.class)
    public ResponseEntity<ErrorResponse> handling(final UnknownAccountException e) {
        return ResponseEntity.badRequest().body(errorService.getErrorResponse(ErrorType.UNKNOWN_ACCOUNT));
    }
}

