package com.soom.account_api.domain.code.controller;

import com.soom.account_api.domain.code.data.response.GenerateTeacherCodeResponse;
import com.soom.account_api.domain.code.service.TeacherCodeGenerateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "교사 코드 API", description = "교사임을 증명하는 코드를 발급해주는 API 입니다.")
@RequestMapping("/api/v2/account/teacher-code")
@RequiredArgsConstructor
public class TeacherCodeController {
    private final TeacherCodeGenerateService teacherCodeGenerateService;

    //교사코드 생성
    @GetMapping@Operation(summary = "교사코드 - 코드발급", description = "입력받은 수량에 맞는 교사코드를 발급합니다.")
    public ResponseEntity<GenerateTeacherCodeResponse> generateTeacherCode(@RequestParam final Integer quantity) {
        final List<String> codes = teacherCodeGenerateService.generate(quantity);
        return ResponseEntity.ok(new GenerateTeacherCodeResponse(quantity, codes));
    }
}
