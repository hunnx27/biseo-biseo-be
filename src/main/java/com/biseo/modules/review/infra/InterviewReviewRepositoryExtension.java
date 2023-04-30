package com.biseo.modules.review.infra;

import com.biseo.modules.company.web.dto.reponse.InterviewListResponseDto;
import com.biseo.modules.review.web.dto.FindEstaRequestDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface InterviewReviewRepositoryExtension {
    List<InterviewListResponseDto> ListInterview(FindEstaRequestDto findEstaRequestDto, Pageable pageable);
}
