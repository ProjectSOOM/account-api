package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.sign.data.dto.StudentSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.TeacherSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.WithdrawalInfoDto;
import com.soom.account_api.global.entity.StudentEntity;
import com.soom.account_api.global.entity.TeacherEntity;
import com.soom.account_api.global.repository.AccountRepository;
import com.soom.account_api.global.repository.StudentRepository;
import com.soom.account_api.global.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public void signUp(StudentSignupInfoDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.authInfo().password());
        StudentEntity entity = new StudentEntity(
                dto.authInfo().email(), encodedPassword, //AuthInfo
                dto.profileInfo().name(), dto.profileInfo().birth(), //ProfileInfo
                dto.studentInfo().admissionYear(), dto.studentInfo().schoolNumber(), dto.studentInfo().department() //StudentInfo
        );
        studentRepository.save(entity);
    }

    @Override
    public void signUp(TeacherSignupInfoDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.authInfo().password());
        TeacherEntity entity = new TeacherEntity(
                dto.authInfo().email(), encodedPassword, //AuthInfo
                dto.profileInfo().name(), dto.profileInfo().birth(), //ProfileInfo
                dto.teacherInfo().code(), dto.teacherInfo().teacher() // TeacherInfo
        );
        teacherRepository.save(entity);
    }

    @Override
    public void withdrawal(WithdrawalInfoDto dto) {
        //TODO
    }
}
