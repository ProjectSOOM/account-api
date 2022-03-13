package com.soom.account_api.domain.profile.service;

import com.soom.account_api.domain.profile.data.dto.UpdateProfileInfoDto;
import com.soom.account_api.domain.profile.data.dto.UpdateStudentProfileInfoDto;
import com.soom.account_api.domain.profile.data.dto.UpdateTeacherProfileInfoDto;
import com.soom.account_api.domain.profile.template.ProfileTemplate;
import com.soom.account_api.global.account.data.dto.AccountDto;
import com.soom.account_api.global.account.repository.AccountRepository;
import com.soom.account_api.global.account.repository.StudentRepository;
import com.soom.account_api.global.account.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final AccountRepository accountRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ProfileTemplate profileTemplate;

    @Override
    public AccountDto getById(final Long accountId) {
        return profileTemplate.doByIdTemplate(accountId, accountRepository, ignore -> {});
    }

    @Override
    public AccountDto updateById(final Long accountId, final UpdateProfileInfoDto dto) {
        return profileTemplate.doByIdTemplate(accountId, accountRepository, entity -> {
            entity.setBirth(dto.birth());
            entity.setName(dto.name());
        });
    }

    @Override
    public AccountDto updateById(final Long accountId, final UpdateTeacherProfileInfoDto dto) {
        return profileTemplate.doByIdTemplate(accountId, teacherRepository, entity -> entity.setTeacher(dto.teacher()));
    }

    @Override
    public AccountDto updateById(final Long accountId, final UpdateStudentProfileInfoDto dto) {
        return profileTemplate.doByIdTemplate(accountId, studentRepository, entity -> {
            entity.setAdmissionYear(dto.admissionYear());
            entity.setSchoolNumber(dto.schoolNumber());
            entity.setDepartment(dto.department());
        });
    }
}
