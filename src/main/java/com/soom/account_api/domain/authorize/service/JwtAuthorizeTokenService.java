package com.soom.account_api.domain.authorize.service;

import com.soom.account_api.domain.authorize.property.AuthorizeJwtProperty;
import com.soom.account_api.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtAuthorizeTokenService implements AuthorizeTokenService {
    private final AuthorizeJwtProperty authorizeJwtProperty;

    @Override
    public String token(final String email) {
        final Map<String, Object> claim = new HashMap<>();
        claim.put("email", email);
        return JwtUtil.encode(authorizeJwtProperty.getSecret(), authorizeJwtProperty.getExpiredSeconds(), claim);
    }

    @Override
    public String email(final String token) {
        return JwtUtil.decode(authorizeJwtProperty.getSecret(), token).get("email", String.class);
    }
}
