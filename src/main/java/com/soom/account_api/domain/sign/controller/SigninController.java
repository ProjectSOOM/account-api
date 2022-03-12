package com.soom.account_api.domain.sign.controller;

import com.soom.account_api.domain.sign.data.dto.SigninInfoDto;
import com.soom.account_api.domain.sign.data.request.LoginRequest;
import com.soom.account_api.domain.sign.data.request.RefreshLoginRequest;
import com.soom.account_api.domain.sign.data.response.LoginToken;
import com.soom.account_api.domain.sign.service.AccountService;
import com.soom.account_api.domain.sign.service.LoginTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "로그인 API", description = "로그인 기능을 제공하는 API 입니다.")
@RequestMapping("/api/v2/account/signin")
@RequiredArgsConstructor
public class SigninController {
    AccountService accountService;
    LoginTokenService loginTokenService;

    //로그인 토큰 발급
    @PostMapping@Operation(summary = "로그인 - 로그인 토큰 발급", description = "이메일과 비밀번호를 통해 로그인 토큰을 발급합니다.")
    public ResponseEntity<LoginToken> login(@RequestBody final LoginRequest request) {
        //LoginRequest 의 정보를 통해 인증과정을 진행하고, 계정의 id를 가져온다.
        final Long accountId = accountService.signin(new SigninInfoDto(request.email(), request.password()));
        //가져온 id를 통해 로그인 토큰을 발급한다.
        final LoginToken response = loginTokenService.token(accountId);
        return ResponseEntity.ok(response);
    }

    //로그인 토큰 재발급
    @PostMapping("/refresh") @Operation(summary = "로그인 - 로그인 토큰 재발급", description = "재발급 토큰을 통해 로그인 토큰을 발급합니다.")
    public ResponseEntity<LoginToken> refreshLogin(@RequestBody final RefreshLoginRequest request) {
        //재발급 토큰을 해석하여, 계정의 id를 가져온다.
        final Long accountId = loginTokenService.getIdByRefreshToken(request.refreshToken());
        //가져온 id를 통해 로그인 토큰을 발급한다.
        final LoginToken response = loginTokenService.token(accountId);
        return ResponseEntity.ok(response);
    }
}
