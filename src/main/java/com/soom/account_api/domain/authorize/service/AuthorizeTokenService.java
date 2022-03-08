package com.soom.account_api.domain.authorize.service;

public interface AuthorizeTokenService {
    String token(String email);

    String email(String token);
}
