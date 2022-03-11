package com.soom.account_api.domain.code.service;

public interface TeacherCodeService {
    String token();

    boolean valid(String token);
}
