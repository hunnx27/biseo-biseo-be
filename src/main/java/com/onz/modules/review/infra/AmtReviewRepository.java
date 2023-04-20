package com.onz.modules.review.infra;

import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.company.web.dto.reponse.YearAmtListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmtReviewRepository extends JpaRepository<YearAmtReview, Long>,
        AmtReviewRepositoryExtension{

    List<YearAmtListResponseDto> findByCompanyId(Long company_id);
    Long countByAccount_Id(Long id);
}
