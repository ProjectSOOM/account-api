package com.soom.account_api.domain.authorize.controller;

import com.soom.account_api.domain.authorize.request.SendAuthEmailRequest;
import com.soom.account_api.domain.authorize.response.ConfirmAuthEmailResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/account/auth")
public class AuthorizeController {
    //이메일 인증 발송
    @PostMapping("/email")
    public ResponseEntity<?> sendAuthEmail(@RequestBody SendAuthEmailRequest request) {
        //인증코드를 생성한다
        //인증코드와 이메일을 매핑한다
        //인증코드를 이메일로 발송한다
        return ResponseEntity.ok().build();
    }
    //이메일 인증
    @GetMapping("/email")
    public ResponseEntity<ConfirmAuthEmailResponse> confirmAuthEmail() {
        //인증코드를 유효한지 확인한다
        //인증코드가 유효하면 매핑된 이메일을 가져온다
        //인증코드를 삭제한다
        //이메일을 통해 토큰을 생성하여 반환한다.
        ConfirmAuthEmailResponse response = new ConfirmAuthEmailResponse("");
        return ResponseEntity.ok(response);
    }
}
