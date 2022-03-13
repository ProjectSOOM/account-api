package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.sign.data.response.LoginToken;
import com.soom.account_api.domain.sign.property.LoginTokenJwtProperty;
import com.soom.account_api.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginTokenServiceImpl implements LoginTokenService {
    private final LoginTokenJwtProperty loginTokenJwtProperty;

    @Override
    public LoginToken token(final Long accountId) {
        final Map<String, Object> claim = new HashMap<>();
        claim.put("id", accountId);
        final String accessToken = JwtUtil.encode(loginTokenJwtProperty.getSecret(), loginTokenJwtProperty.getAccessTokenExpiredSeconds(), claim);
        final String refreshToken = JwtUtil.encode(loginTokenJwtProperty.getSecret(), loginTokenJwtProperty.getRefreshTokenExpiredSeconds(), claim);
        return new LoginToken(accessToken, refreshToken);
    }

    @Override
    public Long id(final String refreshToken) {
        return JwtUtil.decode(loginTokenJwtProperty.getSecret(), refreshToken).get("id", Long.class);
    }
}
