package com.soom.account_api.domain.sign.controller;

import com.soom.account_api.domain.sign.data.request.CheckPasswordRequest;
import com.soom.account_api.domain.sign.data.request.CheckTeacherCodeRequest;
import com.soom.account_api.domain.sign.data.type.SignupPolicyType;
import com.soom.account_api.domain.sign.exception.PolicyViolationException;
import com.soom.account_api.domain.sign.policy.SignupPolicy;
import com.soom.account_api.global.error.data.response.ErrorResponse;
import com.soom.account_api.global.error.service.ErrorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@Tag(name = "회원가입 정책 검사 API", description = "회원가입에 관련된 정책들을 미리 검증해볼 수 있는 API 입니다.")
@RequestMapping("/api/v2/account/signup/policy")
@RequiredArgsConstructor
public class SignupPolicyController {
    private final SignupPolicy signupPolicy;
    private final ErrorService errorService;

    @GetMapping("/email/{email}") @Operation(summary = "정책검증 - 이메일", description = "회원가입용 이메일 정책 검증을 시행합니다.")
    public ResponseEntity<?> checkEmail(@PathVariable final String email) {
        if(!signupPolicy.checkEmailPolicy(email))
            throw new PolicyViolationException(SignupPolicyType.EMAIL_POLICY);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password") @Operation(summary = "정책검증 - 비밀번호", description = "회원가입용 비밀번호 정책 검증을 시행합니다.")
    public ResponseEntity<?> checkPassword(@RequestBody final CheckPasswordRequest request) {
        if(!signupPolicy.checkPasswordPolicy(request.password()))
            throw new PolicyViolationException(SignupPolicyType.PASSWORD_POLICY);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/name/{name}") @Operation(summary = "정책검증 - 이름", description = "회원가입용 이름 정책 검증을 시행합니다.")
    public ResponseEntity<?> checkName(@PathVariable final String name) {
        if(!signupPolicy.checkNamePolicy(name))
            throw new PolicyViolationException(SignupPolicyType.NAME_POLICY);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/birth/{birth}") @Operation(summary = "정책검증 - 생년월일", description = "회원가입용 생년월일 정책 검증을 시행합니다.")
    public ResponseEntity<?> checkBirth(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate birth) {
        if(!signupPolicy.checkBirthPolicy(birth))
            throw new PolicyViolationException(SignupPolicyType.BIRTH_POLICY);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/teacher-code") @Operation(summary = "정책검증 - 교사코드", description = "회원가입용 교사코드 정책 검증을 시행합니다.")
    public ResponseEntity<?> checkTeacherCode(@RequestBody final CheckTeacherCodeRequest request) {
        if(!signupPolicy.checkTeacherCode(request.teacherCode()))
            throw new PolicyViolationException(SignupPolicyType.TEACHER_CODE_POLICY);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/admission-year/{admissionYear}") @Operation(summary = "정책검증 - 입학년도", description = "회원가입용 입학년도 정책 검증을 시행합니다.")
    public ResponseEntity<?> checkAdmissionYear(@PathVariable final Integer admissionYear) {
        if(!signupPolicy.checkStudentAdmissionYear(admissionYear))
            throw new PolicyViolationException(SignupPolicyType.STUDENT_ADMISSION_YEAR_POLICY);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/schoolNumber/{schoolNumber}") @Operation(summary = "정책검증 - 학번", description = "회원가입용 학번 정책 검증을 시행합니다.")
    public ResponseEntity<?> checkSchoolNumber(@PathVariable final Integer schoolNumber) {
        if(!signupPolicy.checkStudentSchoolNumber(schoolNumber))
            throw new PolicyViolationException(SignupPolicyType.STUDENT_SCHOOL_NUMBER_POLICY);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(PolicyViolationException.class)
    public ResponseEntity<ErrorResponse> handling(PolicyViolationException e) {
        return ResponseEntity.badRequest().body(errorService.getErrorResponse(e.getPolicyType().getViolationError()));
    }
}
