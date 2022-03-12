package com.soom.account_api.domain.sign.controller;

import com.soom.account_api.domain.sign.data.request.LoginRequest;
import com.soom.account_api.domain.sign.data.request.RefreshLoginRequest;
import com.soom.account_api.domain.sign.data.response.LoginToken;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "로그인 API", description = "로그인 기능을 제공하는 API 입니다.")
@RequestMapping("/api/v2/account/signin")
@RequiredArgsConstructor
public class SigninController {
    //로그인 토큰 발급
    @PostMapping
    public ResponseEntity<LoginToken> login(LoginRequest request) {
        return null;
    }
    //로그인 토큰 재발급
    @PostMapping("/refresh")
    public ResponseEntity<LoginToken> refreshLogin(RefreshLoginRequest request) {
        return null;
    }
}
