package com.onz.modules.review.infra;

import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import com.onz.modules.company.web.dto.reponse.CompanyReviewListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyReviewRepositoryExtension {
    List<CompanyReviewListResponseDto> listCompanyReview(FindEstaRequestDto findEstaRequestDto, Pageable pageable);
    List<CompanyReview> listCompanyReviewByCompanyId(Long companyId);
}
