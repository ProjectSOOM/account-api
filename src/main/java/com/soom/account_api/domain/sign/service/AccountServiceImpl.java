package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.sign.data.dto.SigninInfoDto;
import com.soom.account_api.domain.sign.data.dto.StudentSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.TeacherSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.WithdrawalInfoDto;
import com.soom.account_api.domain.sign.exception.AccountAuthorizeException;
import com.soom.account_api.domain.sign.policy.SignupPolicyFacade;
import com.soom.account_api.global.entity.AccountEntity;
import com.soom.account_api.global.entity.StudentEntity;
import com.soom.account_api.global.entity.TeacherEntity;
import com.soom.account_api.global.repository.AccountRepository;
import com.soom.account_api.global.repository.StudentRepository;
import com.soom.account_api.global.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.soom.account_api.domain.sign.exception.AccountAuthorizeException.AuthorizeType.EMAIL;
import static com.soom.account_api.domain.sign.exception.AccountAuthorizeException.AuthorizeType.PASSWORD;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SignupPolicyFacade signupPolicyFacade;

    @Override
    public Long signup(final StudentSignupInfoDto dto) {
        signupPolicyFacade.checkStudentPolicy(dto);
        final String encodedPassword = passwordEncoder.encode(dto.authInfo().password());
        final StudentEntity entity = new StudentEntity(
                dto.authInfo().email(), encodedPassword, //AuthInfo
                dto.profileInfo().name(), dto.profileInfo().birth(), dto.profileInfo().school(), //ProfileInfo
                dto.studentInfo().admissionYear(), dto.studentInfo().schoolNumber(), dto.studentInfo().department() //StudentInfo
        );
        return studentRepository.save(entity).getId();
    }

    @Override
    public Long signup(final TeacherSignupInfoDto dto) {
        signupPolicyFacade.checkTeacherPolicy(dto);
        final String encodedPassword = passwordEncoder.encode(dto.authInfo().password());
        final TeacherEntity entity = new TeacherEntity(
                dto.authInfo().email(), encodedPassword, //AuthInfo
                dto.profileInfo().name(), dto.profileInfo().birth(), dto.profileInfo().school(), //ProfileInfo
                dto.teacherInfo().code(), dto.teacherInfo().teacher() // TeacherInfo
        );
        return teacherRepository.save(entity).getId();
    }

    @Override
    public void withdrawal(WithdrawalInfoDto dto) {
        Long id = signin(new SigninInfoDto(dto.email(), dto.password()));
        accountRepository.deleteById(id);
    }

    @Override
    public Long signin(SigninInfoDto dto) {
        final AccountEntity entity = accountRepository.findByEmail(dto.email())
                .orElseThrow(() -> new AccountAuthorizeException("이메일을 찾을 수 없습니다!", EMAIL, dto.email()));
        if(!passwordEncoder.matches(dto.password(), entity.getEncodedPassword()))
            throw new AccountAuthorizeException("비밀번호가 잘못되었습니다!", PASSWORD, dto.password());
        return entity.getId();
    }
}
