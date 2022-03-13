package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.authorize.service.AuthorizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailTokenDecodeServiceImpl implements EmailTokenDecodeService{
    private final AuthorizeService authorizeService;

    @Override
    public String decode(String emailToken) {
        return authorizeService.getEmailByToken(emailToken);
    }
}
