package com.onz.modules.counsel.infra.counselRecommend;


import com.onz.modules.counsel.domain.CounselRecommend;

import java.util.List;
import java.util.Optional;

public interface CounselRecommendRepositoryExtension {
//    List<Counsel> findAnswerList(Long parentCounselId, Pageable pageable);

    List<CounselRecommend> findRecommendCheck(Long answerId, Long accountId);
    List<CounselRecommend> findRecommendCheckN(Long answerId, Long accountId);
    List<CounselRecommend> findRecommend(Long answerId);
    long countNotice(Long answerId, Long accountId);
}