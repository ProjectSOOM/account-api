package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.sign.data.response.LoginToken;

public interface LoginTokenService {
    LoginToken token(Long accountId);

    Long getIdByRefreshToken(String refreshToken);
}
