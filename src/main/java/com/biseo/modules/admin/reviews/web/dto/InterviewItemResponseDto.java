package com.biseo.modules.admin.reviews.web.dto;

import com.biseo.modules.review.domain.InterviewReviewItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterviewItemResponseDto {
    private final String interviewQ;
    private final String interviewA;
    private final String interviewAQ;
    private final String interviewAA;

    
    public InterviewItemResponseDto(InterviewReviewItem ir) {
        this.interviewQ = ir.getInterviewQ();
        this.interviewA = ir.getInterviewA();
        this.interviewAQ = ir.getInterviewAQ();
        this.interviewAA = ir.getInterviewAA();
    }
}
