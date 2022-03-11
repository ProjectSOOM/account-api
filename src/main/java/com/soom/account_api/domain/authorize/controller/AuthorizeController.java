package com.soom.account_api.domain.authorize.controller;

import com.soom.account_api.domain.authorize.data.dto.AuthInfoDto;
import com.soom.account_api.domain.authorize.data.request.SendAuthEmailRequest;
import com.soom.account_api.domain.authorize.data.response.ConfirmAuthEmailResponse;
import com.soom.account_api.domain.authorize.service.AuthorizeService;
import com.soom.account_api.global.exception.JwtUtilException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "인증 API", description = "이메일 인증 등의 본인인증을 담당하는 API 입니다.")
@RequestMapping("/api/v2/account/auth")
@RequiredArgsConstructor
public class AuthorizeController {
    private final AuthorizeService authorizeService;

    //이메일 인증 발송
    @PostMapping("/email") @Operation(summary = "이메일 - 인증메일 발송", description = "이메일 주소를 받아, 해당 주소로 인증코드를 담은 메일을 발송합니다.")
    public ResponseEntity<?> sendAuthEmail(@RequestBody final SendAuthEmailRequest request) {
        //인증코드를 생성한다
        final String code = authorizeService.createAuthCode();
        final AuthInfoDto dto = new AuthInfoDto(code, request.email());
        //인증코드와 이메일을 매핑한다
        authorizeService.addAuthInfo(dto);
        //인증코드를 이메일로 발송한다
        authorizeService.sendAuthCode(dto);
        //작업이 성공적으로 마무리되면 200 OK를 반환한다
        return ResponseEntity.ok().build();
    }
    //이메일 인증
    @GetMapping("/email/{code}") @Operation(summary = "이메일 - 인증", description = "인증코드를 통해 이메일이 본인소유임을 인증하는 토큰을 발급힙니다.")
    public ResponseEntity<ConfirmAuthEmailResponse> confirmAuthEmail(@PathVariable final String code) {
        //인증코드에 매핑된 이메일을 가져온다
        final String email = authorizeService.getEmailByCode(code);
        //인증코드를 삭제한다
        authorizeService.removeAuthInfo(code);
        //이메일을 통해 토큰을 생성하여 반환한다.
        final String token = authorizeService.getTokenByEmail(email);
        final ConfirmAuthEmailResponse response = new ConfirmAuthEmailResponse(token);
        return ResponseEntity.ok(response);
    }
}
