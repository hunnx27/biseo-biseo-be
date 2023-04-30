package com.biseo.modules.review.infra;


import com.biseo.modules.auth.web.dto.UserPrincipal;
import com.biseo.modules.review.domain.dto.ReviewAllDto;
import com.biseo.modules.review.web.dto.FindEstaRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryExtension {
    List<ReviewAllDto> findByAllReview(FindEstaRequestDto findEstaRequestDto, Pageable pageable);
    List<ReviewAllDto> findByAllMyReview(UserPrincipal me, Pageable pageable);
}
