package com.biseo.modules.counsel.infra.counselRecommend;


import com.biseo.modules.counsel.domain.CounselRecommend;

import java.util.List;

public interface CounselRecommendRepositoryExtension {
//    List<Counsel> findAnswerList(Long parentCounselId, Pageable pageable);

    List<CounselRecommend> findRecommendCheck(Long answerId, Long accountId);
    List<CounselRecommend> findRecommendCheckN(Long answerId, Long accountId);
    List<CounselRecommend> findRecommend(Long answerId);
    long countNotice(Long answerId, Long accountId);
}