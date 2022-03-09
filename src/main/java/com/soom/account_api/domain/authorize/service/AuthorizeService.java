package com.soom.account_api.domain.authorize.service;

import com.soom.account_api.domain.authorize.data.dto.AuthInfoDto;

public interface AuthorizeService {
    String createAuthCode();
    void sendAuthCode(AuthInfoDto dto);

    void addAuthInfo(AuthInfoDto dto);
    void removeAuthInfo(String code);

    String getEmailByCode(String code);
    String getEmailByToken(String token);
    String getTokenByEmail(String email);

}
