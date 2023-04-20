package com.onz.modules.admin.member.livemember.web.dto;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.modules.account.domain.enums.AuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
public class LiveMemberDetailResponse {

     
    private Long id;
     
    private ZonedDateTime createdAt;
     
    private Gubn gubn;
     
    private AuthProvider snsType;
     
    private String userId;
     
    private ZonedDateTime modifiedAt;

    //--- 부가정보 ---//

     
    private InterestCompany interestCompany;
     
    private String interestZone;
     
    private String majorSchool; //출신학교
     
    private String majorDepartment; //출신학과

    private String interestTag;

    //--- PUSH ---//

//    private YN notification; // 알림
//    private YN notificationNotice;//공지알림

    //--- Point --- //

    private String code;
//
    // total point 와 잔여포인트 개념 설계 다시 해야함
    private Integer ordersHistory;

    private Integer point;

    //--- 기관 팔로잉 ---//
     
    private Long daycare;
     
    private Long kinder;

    //--- 리뷰 내역 ---//
//
     
    private Long madeReview;
    // ( 승인 ,대기, 거절을 담아야함)
     
    private Long reviewStateW;
     
    private Long reviewStateR;
     
    private Long reviewStateA;
    //--- 연봉 내역 ---//
     
    private Long madeAmt;
     
    private Long amtStateW;
     
    private Long amtStateR;
     
    private Long amtStateA;

    //--- 인터뷰 내역---//
     
    private Long madeInterview;
     
    private Long interviewStateW;
     
    private Long interviewStateR;
     
    private Long interviewStateA;
//     
//    private Long stateA;
//     
//    private Long stateR;
    //--- 상담 내역 ---//
     
    private Long madeQCounselR;
    
    private Long madeQCounselA;
    //답변은 총 답변 상담은 채택/미채택
    
    private Long madeACounsel;

    /*
    String fromSchool, String major, String interestTag, YN notification, YN notificationNotice , Long totalPoint,Long stateA
     */
    public LiveMemberDetailResponse(ZonedDateTime createdAt, Gubn gubn, AuthProvider snsType, String userId, ZonedDateTime modifiedAt, InterestCompany interestCompany, String interestZone,Integer ordersHistory, Integer point, Long daycare, Long kinder, Long madeReview,
                                    Long madeAmt, Long madeInterview, Long madeQCounselR, Long madeQCounselA, Long madeACounsel,
                                    Long reviewStateW, Long reviewStateR,Long reviewStateA
                                    ,Long amtStateW,Long amtStateR,Long amtStateA,
                                    Long interviewStateW ,Long interviewStateA ,Long interviewStateR,String code

    ) {
        this.createdAt = createdAt;
        this.gubn = gubn;
        this.snsType = snsType;
        this.userId = userId;
        this.modifiedAt = modifiedAt;
        this.interestCompany = interestCompany;
        this.interestZone = interestZone;
//        this.fromSchool = fromSchool;
//        this.major = major;
//        this.interestTag = interestTag;
//        this.notification = notification;
//        this.notificationNotice = notificationNotice;
//        this.totalPoint = totalPoint;
        this.ordersHistory=ordersHistory;
        this.point = point;
        this.daycare = daycare;
        this.kinder = kinder;
        this.madeReview = madeReview;
        this.reviewStateW=getReviewStateW()!=null?reviewStateW:0L;
        this.reviewStateR=getReviewStateR()!=null?reviewStateR:0L;
        this.reviewStateA=getReviewStateA()!=null?reviewStateA:0L;
        this.madeAmt = madeAmt;
        this.amtStateW=getAmtStateW()!=null?amtStateW:0L;
        this.amtStateR=getAmtStateR()!=null?amtStateR:0L;
        this.amtStateA=getAmtStateA()!=null?amtStateA:0L;
        this.madeInterview = madeInterview;
        this.interviewStateW=getInterviewStateW()!=null?interviewStateW:0L;
        this.interviewStateR=getInterviewStateR()!=null?interviewStateR:0L;
        this.interviewStateA=getInterviewStateA()!=null?interviewStateA:0L;
        this.madeQCounselR = madeQCounselR;
        this.madeQCounselA = madeQCounselA;
        this.madeACounsel = madeACounsel;
        this.code=code;
//        this.stateA=getStateA()!=null?getStateA():0L;
//        companyReview.getCreatedAt()!=null? companyReview.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
//        this.stateR=getStateR()!=null?getStateR():0L;
    }


    //--- CS ---/
//
//    public Long getStateA(Long state) {
//        if(state!=null){
//            return stateA;
//        }else
//        return 0L;
//    }

    //일단보류류
}
