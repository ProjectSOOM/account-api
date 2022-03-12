package com.soom.account_api.domain.sign.service;

import com.soom.account_api.global.data.type.SchoolType;
import com.soom.account_api.domain.sign.exception.WrongSchoolEmailException;
import com.soom.account_api.domain.sign.property.SchoolEmailProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RegexSchoolEmailService implements SchoolEmailService {
    private final SchoolEmailProperty schoolEmailProperty;

    @Override
    public boolean isSchoolEmail(String email) {
        return schoolEmailProperty.getRegex().values().stream()
                .anyMatch(email::matches);
    }

    @Override
    public SchoolType getSchoolByEmail(String email) {
        final Map<SchoolType, String> regexMap = schoolEmailProperty.getRegex();
        return regexMap.keySet().stream()
                .filter(key -> email.matches(regexMap.get(key)))
                .findAny()
                .orElseThrow(() -> new WrongSchoolEmailException(email));
    }
}
