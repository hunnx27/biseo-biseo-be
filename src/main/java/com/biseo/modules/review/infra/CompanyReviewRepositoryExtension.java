package com.biseo.modules.review.infra;

import com.biseo.modules.review.domain.CompanyReview;
import com.biseo.modules.review.web.dto.FindEstaRequestDto;
import com.biseo.modules.company.web.dto.reponse.CompanyReviewListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyReviewRepositoryExtension {
    List<CompanyReviewListResponseDto> listCompanyReview(FindEstaRequestDto findEstaRequestDto, Pageable pageable);
    List<CompanyReview> listCompanyReviewByCompanyId(Long companyId);
}
