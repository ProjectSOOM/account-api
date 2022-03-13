package com.soom.account_api.global.jwt.exception;

import com.soom.account_api.global.error.data.type.ErrorType;

public class JwtDecodeException extends JwtUtilException {
    public JwtDecodeException(Exception e, ErrorType error) {
        super(error, "jwt 토큰을 디코딩하는 중 오류가 발생하였습니다!", e);
    }
}
