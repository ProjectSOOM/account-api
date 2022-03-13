package com.soom.account_api.domain.sign.policy;

import java.time.LocalDate;

public interface SignupPolicy {
    boolean checkEmailPolicy(String email);
    boolean checkPasswordPolicy(String password);

    boolean checkNamePolicy(String name);
    boolean checkBirthPolicy(LocalDate birth);

    boolean checkTeacherCode(String code);

    boolean checkStudentAdmissionYear(Integer admissionYear);
    boolean checkStudentSchoolNumber(Integer schoolNumber);
}
