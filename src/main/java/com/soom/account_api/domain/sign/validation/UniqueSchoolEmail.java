package com.soom.account_api.domain.sign.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueSchoolEmailValidator.class)
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueSchoolEmail {
    String message() default "회원가입 학교 이메일 정책에 어긋납니다!";

    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
