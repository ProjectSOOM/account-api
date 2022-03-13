package com.soom.account_api.domain.sign.service;

public interface EmailTokenDecodeService {
    String decode(String emailToken);
}
