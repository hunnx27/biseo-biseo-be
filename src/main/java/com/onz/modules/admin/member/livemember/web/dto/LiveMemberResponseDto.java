package com.onz.modules.admin.member.livemember.web.dto;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.modules.account.domain.enums.AuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
public class LiveMemberResponseDto {

    @Getter
    private Long id;
    private Gubn gubn;
    private String gubnName;
    @Getter
    private String userId;

    private AuthProvider snsType;
    private String snsTypeName;



    @Getter
    private Integer point;

    private ZonedDateTime createdAt;

    @Getter
    private long companyReviewCnt;
    @Getter
    private long interviewReviewCnt;
    @Getter
    private long amtReviewCnt;
    @Getter
    private long counselQCnt;
    @Getter
    private long counselACnt;
    @Getter
    private String grade;
//    @Getter
//    private long totalCompanyReviewCnt;
//    @Getter
//    private long totalInterviewReviewCnt;
//    @Getter
//    private long totalAmtReviewCnt;
//    @Getter
//    private long totalCounselQCnt;
//    @Getter
//    private long totalCounselACnt;

    public LiveMemberResponseDto(Long id){
        this.id=id;
    }

    public String getGubnName() {
        String gubnName = this.gubnName;
        if(this.gubn != null && this.gubnName==null){
            gubnName = this.gubn.getName();
        }
        return gubnName;
    }

    public String getSnsTypeName() {
        String snsTypeName = this.snsTypeName;
        if(this.snsType != null && this.snsTypeName==null){
            snsTypeName = this.snsType.getName();
        }
        return snsTypeName;
    }

    public String getCreatedAt() {
        String createdAtStr = "";
        if (this.createdAt != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            createdAtStr = this.createdAt.format(formatter);
        }
        return createdAtStr;
    }


    public LiveMemberResponseDto(long companyReviewCnt, long interviewReviewCnt, long amtReviewCnt, long counselQCnt, long counselACnt) {
        this.gubnName="합계";
        this.companyReviewCnt = companyReviewCnt;
        this.interviewReviewCnt = interviewReviewCnt;
        this.amtReviewCnt = amtReviewCnt;
        this.counselQCnt = counselQCnt;
        this.counselACnt = counselACnt;
    }
}
