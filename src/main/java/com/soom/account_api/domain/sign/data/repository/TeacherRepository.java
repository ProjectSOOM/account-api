package com.soom.account_api.domain.sign.data.repository;

import com.soom.account_api.domain.sign.data.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
}
