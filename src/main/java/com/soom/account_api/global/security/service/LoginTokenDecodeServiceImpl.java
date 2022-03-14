package com.soom.account_api.global.security.service;

import com.soom.account_api.domain.sign.service.LoginTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginTokenDecodeServiceImpl implements LoginTokenDecodeService {
    private final LoginTokenService loginTokenService;

    @Override
    public Long id(String token) {
        return loginTokenService.id(token);
    }
}
