package com.soom.account_api.domain.authorize.controller;

import com.soom.account_api.domain.authorize.dto.AuthInfoDto;
import com.soom.account_api.domain.authorize.request.SendAuthEmailRequest;
import com.soom.account_api.domain.authorize.response.ConfirmAuthEmailResponse;
import com.soom.account_api.domain.authorize.service.AuthorizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/account/auth")
public class AuthorizeController {
    AuthorizeService authorizeService;

    //이메일 인증 발송
    @PostMapping("/email")
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
    @GetMapping("/email/{code}")
    public ResponseEntity<ConfirmAuthEmailResponse> confirmAuthEmail(@PathVariable final String code) {
        //인증코드에 매핑된 이메일을 가져온다
        final String email = authorizeService.getEmailByCode(code);
        //인증코드를 삭제한다
        authorizeService.removeAuthInfo(code);
        //이메일을 통해 토큰을 생성하여 반환한다.
        authorizeService.getTokenByEmail(email);
        final ConfirmAuthEmailResponse response = new ConfirmAuthEmailResponse("");
        return ResponseEntity.ok(response);
    }
}
