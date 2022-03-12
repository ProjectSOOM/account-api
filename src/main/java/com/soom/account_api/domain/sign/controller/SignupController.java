package com.soom.account_api.domain.sign.controller;

import com.soom.account_api.domain.sign.data.dto.*;
import com.soom.account_api.domain.sign.data.request.StudentSignupRequest;
import com.soom.account_api.domain.sign.data.request.TeacherSignupRequest;
import com.soom.account_api.domain.sign.data.request.WithdrawalRequest;
import com.soom.account_api.domain.sign.data.response.StudentSignupResponse;
import com.soom.account_api.domain.sign.data.response.TeacherSignupResponse;
import com.soom.account_api.domain.sign.exception.PolicyViolationException;
import com.soom.account_api.domain.sign.service.AccountService;
import com.soom.account_api.domain.sign.service.EmailTokenDecodeService;
import com.soom.account_api.domain.sign.service.SchoolEmailService;
import com.soom.account_api.global.data.dto.AccountAuthInfoDto;
import com.soom.account_api.global.data.dto.AccountProfileInfoDto;
import com.soom.account_api.global.data.dto.StudentInfoDto;
import com.soom.account_api.global.data.dto.TeacherInfoDto;
import com.soom.account_api.global.data.response.ErrorResponse;
import com.soom.account_api.global.service.ErrorService;
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
    private final SchoolEmailService schoolEmailService;
    private final ErrorService errorService;

    //학생 회원가입
    @PostMapping("/student") @Operation(summary = "회원가입 - 학생 회원가입", description = "학생신분으로 회원가입을 진행합니다.")
    public ResponseEntity<StudentSignupResponse> signup(@RequestBody final StudentSignupRequest request) {
        final StudentSignupInfoDto dto = getDtoByRequest(request);
        final Long accountId = accountService.signup(dto);
        return ResponseEntity.ok(new StudentSignupResponse(accountId));
    }

    //교사 회원가입
    @PostMapping("/teacher") @Operation(summary = "회원가입 - 교사 회원가입", description = "교사신분으로 회원가입을 진행합니다.")
    public ResponseEntity<TeacherSignupResponse> signup(@RequestBody final TeacherSignupRequest request) {
        final TeacherSignupInfoDto dto = getDtoByRequest(request);
        final Long accountId = accountService.signup(dto);
        return ResponseEntity.ok(new TeacherSignupResponse(accountId));
    }

    //회원탈퇴
    @DeleteMapping @Operation(summary = "회원탈퇴", description = "회원 탈퇴를 진행합니다.")
    public ResponseEntity<?> withdrawal(@RequestBody final WithdrawalRequest request) {
        final WithdrawalInfoDto dto = getDtoByRequest(request);
        accountService.withdrawal(dto);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(PolicyViolationException.class)
    public ResponseEntity<ErrorResponse> handling(PolicyViolationException e) {
        return ResponseEntity.badRequest().body(errorService.getErrorResponse(e.getPolicyType().getViolationError()));
    }

    private WithdrawalInfoDto getDtoByRequest(WithdrawalRequest request) {
        final String email = emailTokenDecodeService.decode(request.emailToken());
        return new WithdrawalInfoDto(email, request.password());
    }

    private StudentSignupInfoDto getDtoByRequest(final StudentSignupRequest request) {
        final String email = emailTokenDecodeService.decode(request.emailToken());
        return new StudentSignupInfoDto(
                new AccountAuthInfoDto(email, request.password()),
                new AccountProfileInfoDto(request.name(), request.birth(), schoolEmailService.getSchoolByEmail(email)),
                new StudentInfoDto(request.admissionYear(), request.schoolNumber(), request.department())
        );
    }

    private TeacherSignupInfoDto getDtoByRequest(final TeacherSignupRequest request) {
        final String email = emailTokenDecodeService.decode(request.emailToken());
        return new TeacherSignupInfoDto(
                new AccountAuthInfoDto(email, request.password()),
                new AccountProfileInfoDto(request.name(), request.birth(), schoolEmailService.getSchoolByEmail(email)),
                new TeacherInfoDto(request.code(), request.teacher())
        );
    }
}
