package com.onz.modules.company.web.dto.reponse;

import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.web.dto.ReviewCommonResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InterviewListResponseDto extends ReviewCommonResponseDto {
    private String type = "INTERVIEW";
    private String q_1;
    private String itemMood;
    private String itemTest1;
    private String itemTest2;
    private String itemTest3;

    public InterviewListResponseDto(InterviewReview interviewReview) {
        this.id = interviewReview.getId();
        this.accountId = interviewReview.getAccount().getId();
        this.companyId = interviewReview.getCompany().getId();
        this.zoneCode = interviewReview.getCompany().getZonecode();
        this.itemTest1 = interviewReview.getItem_1()!=null? "O" : "X";
        this.itemTest2 = interviewReview.getItem_2()!=null&&"Y".equals(interviewReview.getItem_2().name())? "O" : "X";
        this.itemTest3 = interviewReview.getItem_3()!=null&&"Y".equals(interviewReview.getItem_3().name())? "O" : "X";
        String test = String.valueOf(interviewReview.getItem_5());
        switch(interviewReview.getItem_5()!=null?test:"9"){
            case "1": this.itemMood = "여유";break;
            case "2": this.itemMood = "편안";break;
            case "3": this.itemMood = "긴장";break;
            default: this.itemMood = "-";break;
        }
        this.q_1 = interviewReview.getTopQ1();
        this.establishmentTypeName=interviewReview.getCompany().getEstablishmentType()!=null?interviewReview.getCompany().getEstablishmentType().getName():"";
        this.companyName = interviewReview.getCompany().getOfficeName();
        this.workExp = interviewReview.getWorkExp();
        this.workExpOpenYn = interviewReview.getWorkExpOpenYn();
    }
}
