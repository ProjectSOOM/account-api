package com.soom.account_api.domain.sign.policy;

import com.soom.account_api.domain.sign.service.SchoolEmailService;
import com.soom.account_api.domain.sign.service.TeacherCodeValidService;
import com.soom.account_api.global.repository.AccountRepository;
import com.soom.account_api.global.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SignupPolicyImpl implements SignupPolicy {
    private final AccountRepository accountRepository;
    private final StudentRepository studentRepository;
    private final SchoolEmailService schoolEmailService;
    private final TeacherCodeValidService teacherCodeValidService;

    @Override
    public boolean checkEmailPolicy(final String email) {
        return schoolEmailService.isSchoolEmail(email) //학교이메일이어야하며.
                && !accountRepository.existsByEmail(email); //기존에 사용하지 않았던 이메일이어야한다.
    }

    @Override
    public boolean checkPasswordPolicy(final String password) {
        return password.length() >= 8  //길이가 8자 이상
                && password.length() <= 20 //20자 이하여야 하며,
                && password.matches("^[a-zA-Z0-9!@#]*$"); //알파벳과 숫자, !@# 으로만 구성되어야한다.
    }

    @Override
    public boolean checkNamePolicy(final String name) {
        return name.length() <= 10 //이름은 10글자 이하여야하며,
                && name.matches("^[가-힣]+$"); //한글로만 구성되어야한다.
    }

    @Override
    public boolean checkBirthPolicy(final LocalDate birth) {
        return birth.isBefore(LocalDate.now()); //생일은 현재 날짜보다 이전(과거)이어야한다.
    }

    @Override
    public boolean checkTeacherCode(final String code) {
        return teacherCodeValidService.valid(code); //교사코드의 검증은 ValidService 에 위임한다.
    }

    @Override
    public boolean checkStudentAdmissionYear(final Integer admissionYear) {
        return LocalDate.now().getYear() >= admissionYear; //입학년도는 현재년도보다 클 수 없다.
    }

    @Override
    public boolean checkStudentSchoolNumber(final Integer schoolNumber) {
        return schoolNumber >= 1101 && schoolNumber <= 6999 //1학년 1반 1번부터 6학년 9반 99번까지
                && !studentRepository.existsBySchoolNumber(schoolNumber); //기존에 존재하지 않는 학번이어야한다.
    }
}
