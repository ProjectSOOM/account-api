package com.soom.account_api.domain.sign.validation;

import com.soom.account_api.domain.sign.service.SchoolEmailValidService;
import com.soom.account_api.global.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UniqueSchoolEmailValidator implements ConstraintValidator<UniqueSchoolEmail, String> {
    private final AccountRepository accountRepository;
    private final SchoolEmailValidService schoolEmailValidService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return schoolEmailValidService.isSchoolEmail(email)
                && !accountRepository.existsByEmail(email);
    }
}
