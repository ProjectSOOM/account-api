package com.soom.account_api.domain.sign.service;

public interface AccountAuthorizeService {
    Long authorize(String email, String password);
}
