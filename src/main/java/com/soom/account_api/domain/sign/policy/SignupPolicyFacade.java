package com.soom.account_api.domain.sign.policy;

import com.soom.account_api.domain.sign.data.dto.StudentSignupInfoDto;
import com.soom.account_api.domain.sign.data.dto.TeacherSignupInfoDto;

public interface SignupPolicyFacade {
    void checkTeacherPolicy(final TeacherSignupInfoDto dto);
    void checkStudentPolicy(final StudentSignupInfoDto dto);
}
