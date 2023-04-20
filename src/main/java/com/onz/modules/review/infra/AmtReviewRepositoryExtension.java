package com.onz.modules.review.infra;

import com.onz.modules.review.web.dto.FindEstaRequestDto;
import com.onz.modules.company.web.dto.reponse.YearAmtListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AmtReviewRepositoryExtension {
    List<YearAmtListResponseDto> ListAmt(FindEstaRequestDto findEstaRequestDto, Pageable pageable);

}
