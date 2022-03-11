package com.soom.account_api.domain.code.service;

public interface TeacherCodeService {
    String token();

    String code(String token);
}
