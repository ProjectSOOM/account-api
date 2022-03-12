package com.soom.account_api.domain.sign.controller;

import com.soom.account_api.domain.sign.data.request.CheckPasswordRequest;
import com.soom.account_api.domain.sign.data.request.CheckTeacherCodeRequest;
import com.soom.account_api.domain.sign.data.type.SignupPolicyType;
import com.soom.account_api.domain.sign.exception.PolicyViolationException;
import com.soom.account_api.domain.sign.policy.SignupPolicy;
import com.soom.account_api.global.data.response.ErrorResponse;
import com.soom.account_api.global.data.type.ErrorType;
import com.soom.account_api.global.service.ErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v2/account/signup/policy")
@RequiredArgsConstructor
public class SignupPolicyController {
    private final SignupPolicy signupPolicy;
    private final ErrorService errorService;

    @GetMapping("/email/{email}")
    public ResponseEntity<?> checkEmail(@PathVariable final String email) {
        if(!signupPolicy.checkEmailPolicy(email))
            throw new PolicyViolationException(SignupPolicyType.EMAIL_POLICY);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password")
    public ResponseEntity<?> checkPassword(@RequestBody final CheckPasswordRequest request) {
        if(!signupPolicy.checkPasswordPolicy(request.password()))
            throw new PolicyViolationException(SignupPolicyType.PASSWORD_POLICY);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> checkName(@PathVariable final String name) {
        if(!signupPolicy.checkNamePolicy(name))
            throw new PolicyViolationException(SignupPolicyType.NAME_POLICY);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/birth/{birth}")
    public ResponseEntity<?> checkBirth(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate birth) {
        if(!signupPolicy.checkBirthPolicy(birth))
            throw new PolicyViolationException(SignupPolicyType.BIRTH_POLICY);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/teacher-code")
    public ResponseEntity<?> checkTeacherCode(@RequestBody final CheckTeacherCodeRequest request) {
        if(!signupPolicy.checkTeacherCode(request.teacherCode()))
            throw new PolicyViolationException(SignupPolicyType.TEACHER_CODE_POLICY);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admission-year/{admissionYear}")
    public ResponseEntity<?> checkAdmissionYear(@PathVariable final Integer admissionYear) {
        if(!signupPolicy.checkStudentAdmissionYear(admissionYear))
            throw new PolicyViolationException(SignupPolicyType.STUDENT_ADMISSION_YEAR_POLICY);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/schoolNumber/{schoolNumber}")
    public ResponseEntity<?> checkSchoolNumber(@PathVariable final Integer schoolNumber) {
        if(!signupPolicy.checkStudentSchoolNumber(schoolNumber))
            throw new PolicyViolationException(SignupPolicyType.STUDENT_SCHOOL_NUMBER_POLICY);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(PolicyViolationException.class)
    public ResponseEntity<ErrorResponse> handling(PolicyViolationException e) {
        return ResponseEntity.badRequest().body(
                switch ((SignupPolicyType)e.getPolicyType()) {
                    //TODO 다른방식의 Mapping 생각해보기
                    case BIRTH_POLICY -> errorService.getErrorResponse(ErrorType.BIRTH_POLICY_VIOLATION);
                    case NAME_POLICY -> errorService.getErrorResponse(ErrorType.NAME_POLICY_VIOLATION);
                    case EMAIL_POLICY -> errorService.getErrorResponse(ErrorType.EXPIRED_JWT_TOKEN);
                    case PASSWORD_POLICY -> errorService.getErrorResponse(ErrorType.PASSWORD_POLICY_VIOLATION);
                    case TEACHER_CODE_POLICY -> errorService.getErrorResponse(ErrorType.TEACHER_CODE_POLICY_VIOLATION);
                    case STUDENT_SCHOOL_NUMBER_POLICY -> errorService.getErrorResponse(ErrorType.STUDENT_SCHOOL_NUMBER_POLICY_VIOLATION);
                    case STUDENT_ADMISSION_YEAR_POLICY -> errorService.getErrorResponse(ErrorType.STUDENT_ADMISSION_YEAR_POLICY_VIOLATION);
                    default -> errorService.getErrorResponse(ErrorType.UNKNOWN_ERROR);
                });
    }
}
