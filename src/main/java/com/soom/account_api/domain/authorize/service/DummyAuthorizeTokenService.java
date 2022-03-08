package com.soom.account_api.domain.authorize.service;

import org.springframework.stereotype.Service;

@Service
public class DummyAuthorizeTokenService implements AuthorizeTokenService{
    @Override
    public String token(String email) {
        return email;
    }
}
