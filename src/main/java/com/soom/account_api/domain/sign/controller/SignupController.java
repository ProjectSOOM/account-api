package com.soom.account_api.domain.sign.controller;

import com.soom.account_api.domain.sign.data.dto.*;
import com.soom.account_api.domain.sign.data.request.StudentSignupRequest;
import com.soom.account_api.domain.sign.data.request.TeacherSignupRequest;
import com.soom.account_api.domain.sign.data.request.WithdrawalRequest;
import com.soom.account_api.domain.sign.data.type.SignupPolicyType;
import com.soom.account_api.domain.sign.exception.PolicyViolationException;
import com.soom.account_api.domain.sign.service.AccountService;
import com.soom.account_api.domain.sign.service.EmailTokenDecodeService;
import com.soom.account_api.global.PolicyType;
import com.soom.account_api.global.data.response.ErrorResponse;
import com.soom.account_api.global.data.type.ErrorType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "회원가입 API", description = "회원가입 및 회원탈퇴기능을 제공하는 API 입니다.")
@RequestMapping("/api/v2/account/signup")
@RequiredArgsConstructor
public class SignupController {
    private final AccountService accountService;
    private final EmailTokenDecodeService emailTokenDecodeService;
    //학생 회원가입
    @PostMapping("/student") @Operation(summary = "회원가입 - 학생 회원가입", description = "학생신분으로 회원가입을 진행합니다.")
    public ResponseEntity<?> signup(@RequestBody final StudentSignupRequest request) {
        final StudentSignupInfoDto dto = getDtoByRequest(request);
        accountService.signUp(dto); //TODO 회원가입 완료정보를 반환하도록 수정
        return ResponseEntity.ok(null);
    }

    //교사 회원가입
    @PostMapping("/teacher") @Operation(summary = "회원가입 - 교사 회원가입", description = "교사신분으로 회원가입을 진행합니다.")
    public ResponseEntity<?> signup(@RequestBody final TeacherSignupRequest request) {
        final TeacherSignupInfoDto dto = getDtoByRequest(request);
        accountService.signUp(dto); //TODO 회원가입 완료정보를 반환하도록 수정
        return ResponseEntity.ok(null);
    }
    //회원탈퇴
    @DeleteMapping @Operation(summary = "회원탈퇴", description = "회원 탈퇴를 진행합니다.")
    public ResponseEntity<?> withdrawal(@RequestBody final WithdrawalRequest request) {
        final WithdrawalInfoDto dto = getDtoByRequest(request);
        accountService.withdrawal(dto);
        return ResponseEntity.noContent().build();
    }

    //정책위반 핸들링
    @ExceptionHandler(PolicyViolationException.class)
    public ResponseEntity<ErrorResponse> handling(PolicyViolationException e) {
        final PolicyType policy = e.getPolicyType();
        final ErrorResponse response = new ErrorResponse(
                //회원가입 도중 일어난 정책위반이므로, 위반한 정책이 회원가입관련 정책임을 유추할 수 있다.
                getErrorTypeByPolicy((SignupPolicyType) policy),
                policy.getName() + "정책을 위반하였습니다!",
                policy.getName() + "정책을 준수하여 다시 요청하세요!"
        );
        return ResponseEntity.badRequest().body(response);
    }

    private ErrorType getErrorTypeByPolicy(SignupPolicyType policy) {
        return switch (policy) {
            case BIRTH_POLICY -> ErrorType.BIRTH_POLICY_VIOLATION;
            case NAME_POLICY -> ErrorType.NAME_POLICY_VIOLATION;
            case EMAIL_POLICY -> ErrorType.EMAIL_POLICY_VIOLATION;
            case PASSWORD_POLICY -> ErrorType.PASSWORD_POLICY_VIOLATION;
            case TEACHER_CODE_POLICY -> ErrorType.TEACHER_CODE_POLICY_VIOLATION;
        };
    }

    private WithdrawalInfoDto getDtoByRequest(WithdrawalRequest request) {
        final String email = emailTokenDecodeService.decode(request.emailToken());
        return new WithdrawalInfoDto(email, request.password());
    }

    private StudentSignupInfoDto getDtoByRequest(final StudentSignupRequest request) {
        final String email = emailTokenDecodeService.decode(request.emailToken());
        return new StudentSignupInfoDto(
                new AccountAuthInfoDto(email, request.password()),
                new AccountProfileInfoDto(request.name(), request.birth()),
                new StudentInfoDto(request.admissionYear(), request.schoolNumber(), request.department())
        );
    }

    private TeacherSignupInfoDto getDtoByRequest(final TeacherSignupRequest request) {
        final String email = emailTokenDecodeService.decode(request.emailToken());
        return new TeacherSignupInfoDto(
                new AccountAuthInfoDto(email, request.password()),
                new AccountProfileInfoDto(request.name(), request.birth()),
                new TeacherInfoDto(request.code(), request.teacher())
        );
    }
}
