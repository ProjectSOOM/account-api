package com.soom.account_api.domain.sign.advice;

import com.soom.account_api.domain.sign.controller.SigninController;
import com.soom.account_api.domain.sign.data.type.SignupPolicyType;
import com.soom.account_api.domain.sign.exception.AccountAuthorizeException;
import com.soom.account_api.domain.sign.exception.PolicyViolationException;
import com.soom.account_api.global.data.response.ErrorResponse;
import com.soom.account_api.global.data.type.ErrorType;
import com.soom.account_api.global.property.ErrorResponseProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = SigninController.class)
@RequiredArgsConstructor
public class SignupControllerAdvice {
    private final ErrorResponseProperty errorResponseProperty;

    @ExceptionHandler(PolicyViolationException.class)
    public ResponseEntity<ErrorResponse> handling(PolicyViolationException e) {
        return ResponseEntity.badRequest().body(getErrorResponse((SignupPolicyType)e.getPolicyType()));
    }

    @ExceptionHandler(AccountAuthorizeException.class)
    public ResponseEntity<ErrorResponse> handling(AccountAuthorizeException e) {
        return ResponseEntity.badRequest().body(
                switch (e.getType()) {
                    case EMAIL -> getErrorResponse(ErrorType.WRONG_EMAIL);
                    case PASSWORD -> getErrorResponse(ErrorType.WRONG_PASSWORD);
                    default -> getErrorResponse(ErrorType.UNKNOWN_ERROR);
                });
    }

    private ErrorResponse getErrorResponse(SignupPolicyType cause) {
        return switch (cause) {
            case BIRTH_POLICY ->  getErrorResponse(ErrorType.BIRTH_POLICY_VIOLATION);
            case NAME_POLICY ->  getErrorResponse(ErrorType.NAME_POLICY_VIOLATION);
            case EMAIL_POLICY ->  getErrorResponse(ErrorType.EXPIRED_JWT_TOKEN);
            case PASSWORD_POLICY ->  getErrorResponse(ErrorType.PASSWORD_POLICY_VIOLATION);
            case TEACHER_CODE_POLICY ->  getErrorResponse(ErrorType.TEACHER_CODE_POLICY_VIOLATION);
            default -> getErrorResponse(ErrorType.UNKNOWN_ERROR);
        };
    }

    //TODO Error 관리서비스 만들어서 거기로 빼내기
    private ErrorResponse getErrorResponse(ErrorType type) {
        return errorResponseProperty.getProperties().get(type.getPropertyName());
    }
}
