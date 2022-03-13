package com.soom.account_api.domain.code.service;

import java.util.List;

public interface TeacherCodeGenerateService {
    List<String> generate(Integer quantity);
}
