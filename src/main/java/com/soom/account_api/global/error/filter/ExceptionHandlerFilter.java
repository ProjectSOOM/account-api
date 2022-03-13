package com.soom.account_api.global.error.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soom.account_api.global.account.exception.UnknownAccountException;
import com.soom.account_api.global.error.data.response.ErrorResponse;
import com.soom.account_api.global.error.data.type.ErrorType;
import com.soom.account_api.global.error.property.ErrorResponseProperty;
import com.soom.account_api.global.error.service.ErrorService;
import com.soom.account_api.global.jwt.exception.JwtDecodeException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.CharEncoding;
import org.apache.http.entity.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    private final ErrorService errorService;
    private final ErrorResponseProperty property;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtDecodeException e) {
            sendErrorResponse(HttpStatus.BAD_REQUEST, getErrorType(e.getCause()), response);

        } catch (UnknownAccountException e) {
            sendErrorResponse(HttpStatus.BAD_REQUEST, ErrorType.UNKNOWN_ACCOUNT, response);
        }
    }

    private void sendErrorResponse(HttpStatus status, ErrorType error, HttpServletResponse response) {
        response.setCharacterEncoding(CharEncoding.UTF_8);
        response.setStatus(status.value());
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        try {
            response.getWriter().write(writeErrorResponse(errorService.getErrorResponse(error)));
        } catch (IOException e) {
            //NOTE 논리적으로 발생불가능한 오류
            e.printStackTrace();
        }
    }

    private String writeErrorResponse(ErrorResponse errorResponse) {
        try {
            return new ObjectMapper().writeValueAsString(errorResponse);
        } catch (JsonProcessingException e) {
            //NOTE 논리적으로 발생불가능한 오류
            e.printStackTrace();
        }
        return property.getFatalMessage();
    }

    private ErrorType getErrorType(Throwable cause) {
        return switch (cause) {
            //TODO 다른방식의 Mapping 생각해보기
            case IllegalArgumentException e -> ErrorType.MISSING_JWT_TOKEN;
            case ExpiredJwtException e -> ErrorType.EXPIRED_JWT_TOKEN;
            case UnsupportedJwtException e -> ErrorType.WRONG_JWT_TOKEN;
            case MalformedJwtException e -> ErrorType.WRONG_JWT_TOKEN;
            case SignatureException e -> ErrorType.WRONG_JWT_TOKEN;
            default -> ErrorType.UNKNOWN_ERROR;
        };
    }
}
