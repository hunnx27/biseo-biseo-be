package com.onz.modules.review.infra;

import com.onz.modules.company.web.dto.reponse.InterviewListResponseDto;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterviewReviewRepositoryExtension {
    List<InterviewListResponseDto> ListInterview(FindEstaRequestDto findEstaRequestDto, Pageable pageable);
}
