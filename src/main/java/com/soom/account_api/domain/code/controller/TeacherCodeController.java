package com.soom.account_api.domain.code.controller;

import com.soom.account_api.domain.authorize.data.response.GenerateTeacherCodeResponse;
import com.soom.account_api.domain.code.service.TeacherCodeGenerateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/account/teacher-code")
@RequiredArgsConstructor
public class TeacherCodeController {
    private final TeacherCodeGenerateService teacherCodeGenerateService;

    //교사코드 생성
    @GetMapping
    public ResponseEntity<GenerateTeacherCodeResponse> generateTeacherCode(@RequestParam final Integer quantity) {
        final List<String> codes = teacherCodeGenerateService.generate(quantity);
        return ResponseEntity.ok(new GenerateTeacherCodeResponse(quantity, codes));
    }
}
