package com.onz.modules.common.code.infra;

import com.onz.modules.common.code.domain.CommonCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonJRepository extends JpaRepository<CommonCode, Long> {
    List<CommonCode> findAllByCode(String code);
    List<CommonCode> findAllByOrderByCodeSebuAsc();
}
