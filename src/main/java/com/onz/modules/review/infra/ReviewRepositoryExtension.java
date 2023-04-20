package com.onz.modules.review.infra;


import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.review.domain.dto.ReviewAllDto;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewRepositoryExtension {
    List<ReviewAllDto> findByAllReview(FindEstaRequestDto findEstaRequestDto, Pageable pageable);
    List<ReviewAllDto> findByAllMyReview(UserPrincipal me, Pageable pageable);
}
