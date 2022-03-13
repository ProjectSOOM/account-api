package com.soom.account_api.domain.profile.service;

import com.soom.account_api.domain.profile.data.dto.UpdateProfileInfoDto;
import com.soom.account_api.domain.profile.data.dto.UpdateStudentProfileInfoDto;
import com.soom.account_api.domain.profile.data.dto.UpdateTeacherProfileInfoDto;
import com.soom.account_api.global.account.data.dto.AccountDto;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {
    AccountDto getById(Long accountId);

    AccountDto updateById(Long accountId, UpdateProfileInfoDto request);
    AccountDto updateById(Long accountId, UpdateTeacherProfileInfoDto request);
    AccountDto updateById(Long accountId, UpdateStudentProfileInfoDto request);
}
