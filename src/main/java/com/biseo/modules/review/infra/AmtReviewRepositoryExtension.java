package com.biseo.modules.review.infra;

import com.biseo.modules.review.web.dto.FindEstaRequestDto;
import com.biseo.modules.company.web.dto.reponse.YearAmtListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AmtReviewRepositoryExtension {
    List<YearAmtListResponseDto> ListAmt(FindEstaRequestDto findEstaRequestDto, Pageable pageable);

}
