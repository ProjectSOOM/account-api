package com.soom.account_api.global.exception;

public class JwtDecodeException extends JwtUtilException {
    public JwtDecodeException(Exception e) {
        super("jwt 토큰을 디코딩하는 중 오류가 발생하였습니다!", e);
    }
}
