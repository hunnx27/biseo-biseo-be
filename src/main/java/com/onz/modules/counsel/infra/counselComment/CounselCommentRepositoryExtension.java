package com.onz.modules.counsel.infra.counselComment;


import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.CounselComment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CounselCommentRepositoryExtension {
//    List<CounselComment> findAnswerList(Long parentCounselId, Pageable pageable);
    List<CounselComment> findCommentList(Long parentCounselId, Pageable pageable);
    long countCommentList(Long parentCounselId);
}