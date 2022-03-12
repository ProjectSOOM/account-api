package com.soom.account_api.domain.sign.service;

import com.soom.account_api.global.data.type.SchoolType;

public interface SchoolEmailService {
    boolean isSchoolEmail(String email);

    SchoolType getSchoolByEmail(String email);
}
