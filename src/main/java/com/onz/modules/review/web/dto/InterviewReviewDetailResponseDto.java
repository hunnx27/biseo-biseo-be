package com.onz.modules.review.web.dto;

import com.onz.modules.review.domain.InterviewReviewItem;
import com.onz.modules.review.domain.InterviewReview;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class InterviewReviewDetailResponseDto {
    private List<String> itemTest1;
    private String itemTest2;
    private String itemTest3;
    private String item4Goal;
    private String item5Mood;
    private List<InterviewItemResponse> qna;
    private Long workExp;

    public InterviewReviewDetailResponseDto(InterviewReview interviewReview) {
        if (interviewReview.getItem_1()!= null) {
            itemTest1 = new ArrayList<>();
            for (int i=0; i<interviewReview.getItem_1().length();i++) {
                if (interviewReview.getItem_1().charAt(i) == '1') {
                    itemTest1.add("제작한 교구보여주기");
                }
                if (interviewReview.getItem_1().charAt(i) == '2') {
                    itemTest1.add("피아노 연주하기");
                }
                if (interviewReview.getItem_1().charAt(i) == '3') {
                    itemTest1.add("수업하기");
                }
            }
        }
        this.itemTest2 = interviewReview.getItem_2()!=null&&"Y".equals(interviewReview.getItem_2().name())? "있어요" : "없어요";
        this.itemTest3 = interviewReview.getItem_3()!=null&&"Y".equals(interviewReview.getItem_3().name())? "있어요" : "없어요";
        switch(interviewReview.getItem_4()!=null?interviewReview.getItem_4():"999"){
            case "1": this.item4Goal = "합격";break;
            case "2": this.item4Goal = "불합격";break;
            case "3": this.item4Goal = "대기";break;
            default: this.item4Goal = "-";break;
        }
        switch(interviewReview.getItem_5()!=null?interviewReview.getItem_5():"999"){
            case "1": this.item5Mood = "여유";break;
            case "2": this.item5Mood = "편안";break;
            case "3": this.item5Mood = "긴장";break;
            default: this.item5Mood = "-";break;
        }
        this.workExp = interviewReview.getWorkExp();
        this.qna= interviewReview.getInterviewItems().stream().map(e -> new InterviewItemResponse(e)).collect(Collectors.toList());

    }

    @Getter
    static
    class InterviewItemResponse{
        private final String interviewQ;
        private final String interviewA;
        private final String interviewAQ;
        private final String interviewAA;

        public InterviewItemResponse(InterviewReviewItem item) {
            this.interviewQ = item.getInterviewQ();
            this.interviewA = item.getInterviewA();
            this.interviewAQ = item.getInterviewAQ();
            this.interviewAA = item.getInterviewAA();
        }
    }
}
