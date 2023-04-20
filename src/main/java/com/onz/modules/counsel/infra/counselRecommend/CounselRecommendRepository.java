package com.onz.modules.counsel.infra.counselRecommend;


import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.CounselRecommend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CounselRecommendRepository extends JpaRepository<CounselRecommend, Long>,
        CounselRecommendRepositoryExtension {

//    List<Counsel> findByParentCounselId(Long parentCounselId);
//    @Query("select c from Counsel c where c.qnaGubn='A' and c.parentCounsel.id = :parentCounselId")
//    List<Counsel> findByParentCounselId2(@Param("parentCounselId") Long parentCounselId);
}
