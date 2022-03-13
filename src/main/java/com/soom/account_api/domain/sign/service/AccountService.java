package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.sign.data.dto.SigninInfoDto;
import com.soom.account_api.domain.sign.data.dto.StudentSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.TeacherSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.WithdrawalInfoDto;

public interface AccountService {
    Long signup(StudentSignupInfoDto dto);
    Long signup(TeacherSignupInfoDto dto);

    void withdrawal(WithdrawalInfoDto dto);

    Long signin(SigninInfoDto dto);
}
