package com.soom.account_api.domain.authorize.service;

import com.soom.account_api.domain.authorize.data.dto.AuthInfoDto;

public interface AuthorizeMailSenderService {
    void send(AuthInfoDto dto);
}
