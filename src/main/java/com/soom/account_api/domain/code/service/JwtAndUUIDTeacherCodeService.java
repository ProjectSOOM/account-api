package com.soom.account_api.domain.code.service;

import com.soom.account_api.domain.code.property.TeacherCodeJwtProperty;
import com.soom.account_api.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtAndUUIDTeacherCodeService implements TeacherCodeService {
    private final TeacherCodeJwtProperty teacherCodeJwtProperty;

    @Override
    public String token() {
        final Map<String, Object> claim = Collections.singletonMap("code", teacherCodeJwtProperty.getPublicCode() + UUID.randomUUID());
        return JwtUtil.encode(teacherCodeJwtProperty.getSecret(), teacherCodeJwtProperty.getExpiredDay() * 60 * 60 * 24, claim);
    }

    @Override
    public String code(String token) {
        final String decoded = JwtUtil.decode(teacherCodeJwtProperty.getSecret(), token).get("code", String.class);
        if(decoded.startsWith(teacherCodeJwtProperty.getPublicCode()))
            return decoded.replace(teacherCodeJwtProperty.getPublicCode(), "");
        return null; //TODO return null || throw runtime exception
    }
}
