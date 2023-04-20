package com.onz.modules.counsel.infra.counsel;


import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.web.dto.request.counsel.CounselSearchRequest;
import com.onz.modules.counsel.web.dto.response.counsel.CounselListResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CounselRepositoryExtension {

    List<Counsel> findCounselList(CounselSearchRequest counselSearchRequest, Pageable pageable);
    List<Counsel> findMyqCounselList(Account account, Pageable pageable,String option);
    List<Counsel> findMyaCounselList(Account account, Pageable pageable,String option);
    List<Counsel> findAnswerList(Long parentCounselId, Pageable pageable);
    List<Counsel> findCounselTag(String tag);
    long countAdoptedAnswer(Long answerId, Long accountId);


}