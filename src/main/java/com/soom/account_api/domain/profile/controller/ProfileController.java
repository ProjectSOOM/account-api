package com.soom.account_api.domain.profile.controller;

import com.soom.account_api.domain.profile.data.dto.UpdateProfileInfoDto;
import com.soom.account_api.domain.profile.data.dto.UpdateStudentProfileInfoDto;
import com.soom.account_api.domain.profile.data.dto.UpdateTeacherProfileInfoDto;
import com.soom.account_api.domain.profile.data.request.UpdateProfileRequest;
import com.soom.account_api.domain.profile.data.request.UpdateStudentProfileRequest;
import com.soom.account_api.domain.profile.data.request.UpdateTeacherProfileRequest;
import com.soom.account_api.domain.profile.data.response.GetProfileResponse;
import com.soom.account_api.domain.profile.service.ProfileService;
import com.soom.account_api.global.account.data.dto.AccountDto;
import com.soom.account_api.global.account.data.dto.StudentDto;
import com.soom.account_api.global.account.data.dto.TeacherDto;
import com.soom.account_api.global.account.data.type.AccountType;
import com.soom.account_api.global.security.service.SigninUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "프로필 API", description = "프로필 조회/수정등을 담당하는 API 입니다.")
@RequestMapping("/api/v2/account/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final SigninUserService signinUserService;

    //계정 프로필 조회
    @GetMapping("/{accountId}") @Operation(summary = "계정 - 프로필 조회", description = "이메일 주소를 받아, 해당 주소로 인증코드를 담은 메일을 발송합니다.")
    public ResponseEntity<GetProfileResponse> getProfile(@PathVariable final Long accountId) {
        //아이디를 통해 계정정보를 가져온다
        final AccountDto dto = profileService.getById(accountId);
        //계정정보를 응답객체로 치환하여 반환한다.
        final GetProfileResponse response = getResponseByDto(dto);
        return ResponseEntity.ok(response);
    }

    //계정 프로필 수정
    @PutMapping @Operation(summary = "계정 - 일반 프로필 수정", description = "이름, 생년월일등의 일반 프로필 정보를 수정합니다.")
    public ResponseEntity<?> updateProfile(@RequestBody final UpdateProfileRequest request) {
        //현재 로그인된 유저의 accountId를 가져온다.
        final Long accountId = signinUserService.getSigninUserId();
        //아이디를와 프로필 정보를 통해 해당 계정의 프로필 정보를 수정한다.
        final AccountDto dto = profileService.updateById(accountId, getDtoByRequest(request));
        //수정된 프로필을 반환한다.
        final GetProfileResponse response = getResponseByDto(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/teacher") @Operation(summary = "계정 - 교사 프로필 수정", description = "교사분류 등의 교사 프로필 정보를 수정합니다. (교사계정이 아닐경우 400 반환)")
    public ResponseEntity<?> updateTeacherProfile(@RequestBody final UpdateTeacherProfileRequest request) {
        //현재 로그인된 유저의 accountId를 가져온다.
        final Long accountId = signinUserService.getSigninUserId();
        //아이디를와 프로필 정보를 통해 해당 계정의 프로필 정보를 수정한다.
        final AccountDto dto = profileService.updateById(accountId, getDtoByRequest(request));
        //수정된 프로필을 반환한다.
        final GetProfileResponse response = getResponseByDto(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/student") @Operation(summary = "계정 - 학생 프로필 수정", description = "학번, 입학년도 등의 학생 프로필 정보를 수정합니다. (학생계정이 아닐경우 400 반환)")
    public ResponseEntity<?> updateStudentProfile(@RequestBody final UpdateStudentProfileRequest request) {
        //현재 로그인된 유저의 accountId를 가져온다.
        final Long accountId = signinUserService.getSigninUserId();
        //아이디를와 프로필 정보를 통해 해당 계정의 프로필 정보를 수정한다.
        final AccountDto dto = profileService.updateById(accountId, getDtoByRequest(request));
        //수정된 프로필을 반환한다.
        final GetProfileResponse response = getResponseByDto(dto);
        return ResponseEntity.ok(response);
    }

    private UpdateProfileInfoDto getDtoByRequest(UpdateProfileRequest request) {
        return new UpdateProfileInfoDto(request.name(), request.birth());
    }

    private UpdateTeacherProfileInfoDto getDtoByRequest(UpdateTeacherProfileRequest request) {
        return new UpdateTeacherProfileInfoDto(request.teacher());
    }

    private UpdateStudentProfileInfoDto getDtoByRequest(UpdateStudentProfileRequest request) {
        return new UpdateStudentProfileInfoDto(request.admissionYear(), request.schoolNumber(), request.department());
    }

    private GetProfileResponse getResponseByDto(AccountDto dto) {
        if(dto instanceof StudentDto) return new GetProfileResponse(AccountType.STUDENT, (StudentDto) dto, null);
        if(dto instanceof TeacherDto) return new GetProfileResponse(AccountType.TEACHER, null, (TeacherDto) dto);
        throw new IllegalStateException("Unexpected value: " + dto); //Internal Server Error : 로직이 동기화되지 않았을경우
    }
}
