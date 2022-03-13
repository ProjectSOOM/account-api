package com.soom.account_api.domain.code.service;

import com.soom.account_api.domain.code.property.TeacherCodeJwtProperty;
import com.soom.account_api.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtAndUUIDTeacherCodeService implements TeacherCodeService {
    private final TeacherCodeJwtProperty teacherCodeJwtProperty;

    @Override
    public String token() {
        final Map<String, Object> claim = new HashMap<>();
        claim.put("code", teacherCodeJwtProperty.getPublicCode() + UUID.randomUUID());
        return JwtUtil.encode(teacherCodeJwtProperty.getSecret(), teacherCodeJwtProperty.getExpiredDay() * 60 * 60 * 24, claim);
    }

    @Override
    public boolean valid(String token) {
        return JwtUtil
                .decode(teacherCodeJwtProperty.getSecret(), token)
                .get("code", String.class)
                .startsWith(teacherCodeJwtProperty.getPublicCode());
    }
}
