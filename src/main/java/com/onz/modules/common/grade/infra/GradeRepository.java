package com.onz.modules.common.grade.infra;

import com.onz.modules.common.code.domain.CommonCode;
import com.onz.modules.common.grade.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long>,GradeRepositoryExtension {
    Grade findByGrade(String code);
}
