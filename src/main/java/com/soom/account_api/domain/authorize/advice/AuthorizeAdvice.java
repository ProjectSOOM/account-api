package com.soom.account_api.domain.authorize.advice;

import com.soom.account_api.domain.authorize.exception.UnknownCodeException;
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
public class AuthorizeAdvice {
    private final ErrorService errorService;

    @ExceptionHandler(UnknownCodeException.class)
    public ResponseEntity<ErrorResponse> handling(final UnknownCodeException e) {
        return ResponseEntity.badRequest().body(errorService.getErrorResponse(ErrorType.UNKNOWN_AUTHORIZE_CODE));
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<ErrorResponse> handling(final MailSendException e) {
        return ResponseEntity.badRequest().body(errorService.getErrorResponse(ErrorType.UNKNOWN_EMAIL));
    }

}

