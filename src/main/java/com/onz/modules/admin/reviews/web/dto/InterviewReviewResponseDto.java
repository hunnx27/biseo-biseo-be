package com.onz.modules.admin.reviews.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.State;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.company.domain.enums.EstablishmentType;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.InterviewReviewItem;
import com.onz.modules.review.domain.enums.ItemCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class InterviewReviewResponseDto {
    private String type;
    private Long id;

    private State state;
    private String stateName;
    private String txtAdmin;

    private InterestCompany interestCompany;
    private EstablishmentType establishmentType;
    private String zonecode;
    private String companyName;
    private String userId;
    private ZonedDateTime createdAt;

    private ZonedDateTime modifiedAt;

    private Long workExp;

    private YN expOpenYn;

    private String item1;

    private YN item2;

    private YN item3;

    private String item4;

    private ItemCode item5;

    private List<InterviewReviewItems> qna;

    public InterviewReviewResponseDto(InterviewReview iv) {
        this.type = "INTERVIEW";
        this.id = iv.getId();
        this.state = iv.getState();
        this.txtAdmin = iv.getTxtAdmin();
        this.interestCompany = iv.getCompany().getInterestCompany();
        this.establishmentType = iv.getCompany().getEstablishmentType();
        this.zonecode = iv.getCompany().getZonecode();
        this.companyName = iv.getCompany().getOfficeName();
        this.userId = iv.getAccount().getUserId();
        this.createdAt = iv.getCreatedAt();
        this.modifiedAt = iv.getModifiedAt();
        this.workExp = iv.getWorkExp();
        this.expOpenYn = iv.getWorkExpOpenYn();
        this.item1 = iv.getItem_1();
        this.item2 = iv.getItem_2();
        this.item3 = iv.getItem_3();
        this.item4 = iv.getItem_4();
        this.item5 = ItemCode.valueOf(iv.getItem_5());
        this.qna =iv.getInterviewItems().stream().map(InterviewReviewItems::new).collect(Collectors.toList());
    }
    @Getter
    static class InterviewReviewItems{
        private final String interviewQ;
        private final String interviewA;
        private final String interviewAQ;
        private final String interviewAA;

        public InterviewReviewItems(InterviewReviewItem test) {
            this.interviewQ = test.getInterviewQ();
            this.interviewA = test.getInterviewA();
            this.interviewAQ = test.getInterviewAQ();
            this.interviewAA = test.getInterviewAA();
        }
    }
}
