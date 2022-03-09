package com.soom.account_api.domain.sign.controller;

import com.soom.account_api.domain.sign.data.dto.*;
import com.soom.account_api.domain.sign.data.request.StudentSignupRequest;
import com.soom.account_api.domain.sign.data.request.TeacherSignupRequest;
import com.soom.account_api.domain.sign.data.request.WithdrawalRequest;
import com.soom.account_api.domain.sign.service.AccountService;
import com.soom.account_api.domain.sign.service.EmailTokenDecodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/account/signup")
@RequiredArgsConstructor
public class SignupController {
    private final AccountService accountService;
    private final EmailTokenDecodeService emailTokenDecodeService;
    //학생 회원가입
    @PostMapping("/student")
    public ResponseEntity<?> signup(@RequestBody final StudentSignupRequest request) {
        final StudentSignupInfoDto dto = getDtoByRequest(request);
        accountService.signUp(dto); //TODO 회원가입 완료정보를 반환하도록 수정
        return ResponseEntity.ok(null);
    }

    //교사 회원가입
    @PostMapping("/teacher")
    public ResponseEntity<?> signup(@RequestBody final TeacherSignupRequest request) {
        final TeacherSignupInfoDto dto = getDtoByRequest(request);
        accountService.signUp(dto); //TODO 회원가입 완료정보를 반환하도록 수정
        return ResponseEntity.ok(null);
    }
    //회원탈퇴
    @DeleteMapping
    public ResponseEntity<?> withdrawal(@RequestBody final WithdrawalRequest request) {
        final WithdrawalInfoDto dto = getDtoByRequest(request);
        accountService.withdrawal(dto);
        return ResponseEntity.noContent().build();
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
