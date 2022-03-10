package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.sign.data.dto.StudentSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.TeacherSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.WithdrawalInfoDto;
import com.soom.account_api.domain.sign.data.type.SignupPolicyType;
import com.soom.account_api.domain.sign.exception.PolicyViolationException;
import com.soom.account_api.domain.sign.policy.SignupPolicy;
import com.soom.account_api.global.entity.StudentEntity;
import com.soom.account_api.global.entity.TeacherEntity;
import com.soom.account_api.global.repository.AccountRepository;
import com.soom.account_api.global.repository.StudentRepository;
import com.soom.account_api.global.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SignupPolicy signupPolicy;

    @Override
    public void signUp(final StudentSignupInfoDto dto) {
        checkPolicy(dto);
        final String encodedPassword = passwordEncoder.encode(dto.authInfo().password());
        final StudentEntity entity = new StudentEntity(
                dto.authInfo().email(), encodedPassword, //AuthInfo
                dto.profileInfo().name(), dto.profileInfo().birth(), //ProfileInfo
                dto.studentInfo().admissionYear(), dto.studentInfo().schoolNumber(), dto.studentInfo().department() //StudentInfo
        );
        studentRepository.save(entity);
    }

    @Override
    public void signUp(final TeacherSignupInfoDto dto) {
        checkPolicy(dto);
        final String encodedPassword = passwordEncoder.encode(dto.authInfo().password());

        final TeacherEntity entity = new TeacherEntity(
                dto.authInfo().email(), encodedPassword, //AuthInfo
                dto.profileInfo().name(), dto.profileInfo().birth(), //ProfileInfo
                dto.teacherInfo().code(), dto.teacherInfo().teacher() // TeacherInfo
        );
        teacherRepository.save(entity);
    }

    private void checkPolicy(final TeacherSignupInfoDto dto) {
        checkPolicy(dto.authInfo().email(), dto.authInfo().password(), dto.profileInfo().name(), dto.profileInfo().birth());
        if(!signupPolicy.checkTeacherCode(dto.teacherInfo().code())) throw new PolicyViolationException(SignupPolicyType.TEACHER_CODE_POLICY);
    }

    private void checkPolicy(final StudentSignupInfoDto dto) {
        checkPolicy(dto.authInfo().email(), dto.authInfo().password(), dto.profileInfo().name(), dto.profileInfo().birth());
        if(!signupPolicy.checkStudentAdmissionYear(dto.studentInfo().admissionYear())) throw new PolicyViolationException(SignupPolicyType.TEACHER_CODE_POLICY);
        if(!signupPolicy.checkStudentSchoolNumber(dto.studentInfo().schoolNumber())) throw new PolicyViolationException(SignupPolicyType.TEACHER_CODE_POLICY);
    }

    private void checkPolicy(String email, String password, String name, LocalDate birth) {
        if(!signupPolicy.checkEmailPolicy(email)) throw new PolicyViolationException(SignupPolicyType.EMAIL_POLICY);
        if(!signupPolicy.checkPasswordPolicy(password)) throw new PolicyViolationException(SignupPolicyType.PASSWORD_POLICY);
        if(! signupPolicy.checkNamePolicy(name)) throw new PolicyViolationException(SignupPolicyType.NAME_POLICY);
        if(!signupPolicy.checkBirthPolicy(birth)) throw new PolicyViolationException(SignupPolicyType.BIRTH_POLICY);
    }

    @Override
    public void withdrawal(WithdrawalInfoDto dto) {
        //TODO
    }
}
