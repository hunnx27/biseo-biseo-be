package com.biseo.modules.review.infra;

import com.biseo.modules.review.domain.YearAmtReview;
import com.biseo.modules.company.web.dto.reponse.YearAmtListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmtReviewRepository extends JpaRepository<YearAmtReview, Long>,
        AmtReviewRepositoryExtension{

    List<YearAmtListResponseDto> findByCompanyId(Long company_id);
    Long countByAccount_Id(Long id);
}
