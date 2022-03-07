package com.soom.account_api.domain.authorize.service;

import com.soom.account_api.domain.authorize.data.dto.AuthInfoDto;
import com.soom.account_api.domain.authorize.property.AuthorizeMailProperty;
import com.soom.account_api.infra.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HtmlAuthorizeMailSenderService implements AuthorizeMailSenderService{
    private final AuthorizeMailProperty property;
    private final MailSenderService mailSenderService;

    @Override
    public void send(AuthInfoDto dto) {
        Map<String, Object> models = new HashMap<>();
        models.put("code", dto.code());
        mailSenderService.sendHtmlEmail(dto.email(), property.getTitle(), property.getTemplatePath(), models);
    }
}
