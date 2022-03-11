package com.soom.account_api.domain.code.data.response;

import java.util.List;

public record GenerateTeacherCodeResponse(
        Integer quantity,
        List<String> code
) {
}
