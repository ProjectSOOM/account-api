package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.sign.property.SchoolEmailProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegexSchoolEmailService implements SchoolEmailValidService {
    private final SchoolEmailProperty schoolEmailProperty;

    @Override
    public boolean isSchoolEmail(String email) {
        return schoolEmailProperty.getRegex().values().stream()
                .anyMatch(email::matches);
    }
}
