package com.soom.account_api.domain.sign.policy;

import com.soom.account_api.domain.sign.data.dto.StudentSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.TeacherSignupInfoDto;
import com.soom.account_api.domain.sign.data.type.SignupPolicyType;
import com.soom.account_api.domain.sign.exception.PolicyViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class SignupPolicyFacadeImpl implements SignupPolicyFacade{
    private final SignupPolicy signupPolicy;

    @Override
    public void checkTeacherPolicy(final TeacherSignupInfoDto dto) {
        checkCommonPolicy(dto.authInfo().email(), dto.authInfo().password(), dto.profileInfo().name(), dto.profileInfo().birth());
        if(!signupPolicy.checkTeacherCode(dto.teacherInfo().code())) throw new PolicyViolationException(SignupPolicyType.TEACHER_CODE_POLICY);
    }

    @Override
    public void checkStudentPolicy(final StudentSignupInfoDto dto) {
        checkCommonPolicy(dto.authInfo().email(), dto.authInfo().password(), dto.profileInfo().name(), dto.profileInfo().birth());
        if(!signupPolicy.checkStudentAdmissionYear(dto.studentInfo().admissionYear())) throw new PolicyViolationException(SignupPolicyType.STUDENT_ADMISSION_YEAR_POLICY);
        if(!signupPolicy.checkStudentSchoolNumber(dto.studentInfo().schoolNumber())) throw new PolicyViolationException(SignupPolicyType.STUDENT_SCHOOL_NUMBER_POLICY);
    }

    private void checkCommonPolicy(final String email, final String password, final String name, final LocalDate birth) {
        if(!signupPolicy.checkEmailPolicy(email)) throw new PolicyViolationException(SignupPolicyType.EMAIL_POLICY);
        if(!signupPolicy.checkPasswordPolicy(password)) throw new PolicyViolationException(SignupPolicyType.PASSWORD_POLICY);
        if(!signupPolicy.checkNamePolicy(name)) throw new PolicyViolationException(SignupPolicyType.NAME_POLICY);
        if(!signupPolicy.checkBirthPolicy(birth)) throw new PolicyViolationException(SignupPolicyType.BIRTH_POLICY);
    }
}
