package com.soom.account_api.domain.sign.service;

import com.soom.account_api.domain.code.service.TeacherCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherCodeValidServiceImpl implements TeacherCodeValidService{
    private final TeacherCodeService teacherCodeService;

    @Override
    public boolean valid(String teacherCode) {
        return teacherCodeService.valid(teacherCode);
    }
}
