package com.soom.account_api.domain.profile.controller;

import com.soom.account_api.domain.profile.data.dto.UpdateProfileInfoDto;
import com.soom.account_api.domain.profile.data.dto.UpdateStudentProfileInfoDto;
import com.soom.account_api.domain.profile.data.dto.UpdateTeacherProfileInfoDto;
import com.soom.account_api.domain.profile.data.request.UpdateProfileRequest;
import com.soom.account_api.domain.profile.data.request.UpdateStudentProfileRequest;
import com.soom.account_api.domain.profile.data.request.UpdateTeacherProfileRequest;
import com.soom.account_api.domain.profile.data.response.GetProfileResponse;
import com.soom.account_api.domain.profile.service.ProfileService;
import com.soom.account_api.global.data.dto.AccountDto;
import com.soom.account_api.global.data.dto.StudentDto;
import com.soom.account_api.global.data.dto.TeacherDto;
import com.soom.account_api.global.data.type.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/account/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    //TODO
    //계정 프로필 조회
    @GetMapping("/{accountId}")
    public ResponseEntity<GetProfileResponse> getProfile(@PathVariable final Long accountId) {
        //아이디를 통해 계정정보를 가져온다
        final AccountDto dto = profileService.getById(accountId);
        //계정정보를 응답객체로 치환하여 반환한다.
        final GetProfileResponse response = getResponseByDto(dto);
        return ResponseEntity.ok(response);
    }

    //계정 프로필 수정
    @PutMapping("/{accountId}")
    public ResponseEntity<?> updateProfile(@PathVariable final Long accountId, @RequestBody final UpdateProfileRequest request) {
        //아이디를와 프로필 정보를 통해 해당 계정의 프로필 정보를 수정한다.
        final AccountDto dto = profileService.updateById(accountId, getDtoByRequest(request));
        //수정된 프로필을 반환한다.
        final GetProfileResponse response = getResponseByDto(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/teacher/{accountId}")
    public ResponseEntity<?> updateTeacherProfile(@PathVariable final Long accountId, @RequestBody final UpdateTeacherProfileRequest request) {
        //아이디를와 프로필 정보를 통해 해당 계정의 프로필 정보를 수정한다.
        final AccountDto dto = profileService.updateById(accountId, getDtoByRequest(request));
        //수정된 프로필을 반환한다.
        final GetProfileResponse response = getResponseByDto(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/student/{accountId}")
    public ResponseEntity<?> updateStudentProfile(@PathVariable final Long accountId, @RequestBody final UpdateStudentProfileRequest request) {
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
        return switch (dto) {
            case StudentDto student -> new GetProfileResponse(AccountType.STUDENT, student, null);
            case TeacherDto teacher -> new GetProfileResponse(AccountType.TEACHER, null, teacher);
            default -> throw new IllegalStateException("Unexpected value: " + dto); //Internal Server Error : 로직이 동기화되지 않았을경우
        };
    }
}
