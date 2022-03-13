package com.soom.account_api.domain.code.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TeacherCodeGenerateServiceImpl implements TeacherCodeGenerateService {
    private final TeacherCodeService jwtTeacherCodeService;

    @Override
    public List<String> generate(final Integer quantity) {
        return IntStream.range(0, quantity)
                .mapToObj(x -> jwtTeacherCodeService.token())
                .toList();
    }
}
