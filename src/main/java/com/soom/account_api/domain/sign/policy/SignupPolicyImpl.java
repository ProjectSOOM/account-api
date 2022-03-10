package com.soom.account_api.domain.sign.policy;

import com.soom.account_api.domain.sign.service.SchoolEmailValidService;
import com.soom.account_api.global.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SignupPolicyImpl implements SignupPolicy {
    private final AccountRepository accountRepository;
    private final SchoolEmailValidService schoolEmailValidService;

    @Override
    public boolean checkEmailPolicy(String email) {
        return schoolEmailValidService.isSchoolEmail(email) //학교이메일이어야하며.
                && !accountRepository.existsByEmail(email); //기존에 사용하지 않았던 이메일이어야한다.
    }

    @Override
    public boolean checkPasswordPolicy(String password) {
        return password.length() >= 8  //길이가 8자 이상
                && password.length() <= 20 //20자 이하여야 하며,
                && password.matches("^[a-zA-Z0-9!@#]*$"); //알파벳과 숫자, !@# 으로만 구성되어야한다.
    }

    @Override
    public boolean checkNamePolicy(String name) {
        return name.length() <= 10 //이름은 10글자 이하여야하며,
                && name.matches("^[가-힣]+$"); //한글로만 구성되어야한다.
    }

    @Override
    public boolean checkBirthPolicy(LocalDate birth) {
        return birth.isBefore(LocalDate.now());
    }

    @Override
    public boolean checkTeacherCode(String code) {
        //TODO
        return true;
    }

    @Override
    public boolean checkStudentAdmissionYear(Integer admissionYear) {
        return LocalDate.now().getYear() >= admissionYear; //입학년도는 현재년도보다 클 수 없다.
    }

    @Override
    public boolean checkStudentSchoolNumber(Integer schoolNumber) {
        return schoolNumber >= 1101 && schoolNumber <= 6999; //1학년 1반 1번부터 6학년 9반 99번까지
    }
}
