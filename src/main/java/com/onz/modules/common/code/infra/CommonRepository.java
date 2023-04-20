package com.onz.modules.common.code.infra;

import com.onz.modules.common.code.domain.CommonCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CommonRepository {
    private final EntityManager em;

    public Long save(CommonCode commonCode){
        em.persist(commonCode);
        return commonCode.getId();
    }
    public CommonCode findById(Long id){
        return em.find(CommonCode.class,id);
    }
}
