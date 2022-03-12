package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.sign.data.dto.StudentSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.TeacherSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.WithdrawalInfoDto;

public interface AccountService {
    void signUp(StudentSignupInfoDto dto);
    void signUp(TeacherSignupInfoDto dto);

    void withdrawal(WithdrawalInfoDto dto);

    Long authorize(String email, String password);
}
