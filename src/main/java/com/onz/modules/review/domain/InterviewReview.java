package com.onz.modules.review.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.domain.BaseEntity;
import com.onz.common.web.dto.response.enums.State;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.review.web.dto.InterviewRequestDto;
import com.onz.modules.company.domain.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DynamicInsert
public class InterviewReview extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //모의수업 인덱스 1,3
    private String item_1;

    //필기시험 여부 y
    @Enumerated(EnumType.STRING)
    private YN item_2;

    //인적성 평가 여부 y
    @Enumerated(EnumType.STRING)
    private YN item_3;

    // 면접 합격여부 및 평가
    private String item_4;

    //난이도
    private String item_5;

    @Enumerated(EnumType.STRING)
    private YN item_6;

    @ColumnDefault("'W'")
    @Enumerated(EnumType.STRING)
    private State state;

    //    private Long reviewOrder;
//    private String interviewQ;
//    private String interviewA;
//    private String interviewQA;
//    private String interviewAA;
    private String txtAdmin;

    @Enumerated(EnumType.STRING)
    private YN workExpOpenYn;

    private Long workExp;

    private String zonecode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "interviewId")
    private List<InterviewReviewItem> interviewItems;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private ZonedDateTime apprDt;

    private String apprTxt;
    private String topQ1;
    private String apprId; // 승인자 (관리자)
    //    //1 page
//    private String interviewQ; // 인터뷰 질문
//    private String interviewA; // 인터뷰 답변
//    private String interviewAQ; // 인터뷰 질문 관리자 질문
//    private String interviewAA; // 인터뷰 질문 관리자 답변
//    private String interviewOrder; // 인터뷰 질문 내림차순정렬
//    private String txtAdmin; //관리자 의견
//    //    private Long reviewOrder;
//    private Long companyId;
    @Builder
    public InterviewReview(InterviewRequestDto interviewRequestDto, Company company, Account account) {
//        this.reviewOrder=interviewRequestDto.getInterviewOrder();
        this.company = company;
        this.workExp= interviewRequestDto.getWorkExp();
//        this.interviews=interviewRequestDto.getInterview();
        this.account = account;
        this.item_1=interviewRequestDto.getItem_1();
        this.item_2=interviewRequestDto.getItem_2();
        this.item_3=interviewRequestDto.getItem_3();
        this.item_4=interviewRequestDto.getItem_4();
        this.item_5=interviewRequestDto.getItem_5();
        this.item_6=interviewRequestDto.getItem_6();
//        this.Zonecode=company.getZonecode();
        this.workExpOpenYn=interviewRequestDto.getWorkExpOpenYn();
        this.topQ1=interviewRequestDto.getInterviews().get(0).getQ();
    }
    public InterviewReview(InterviewReview interviewReviewItem){
        this.topQ1=interviewReviewItem.getTopQ1();
    }


    public InterviewReview(InterviewRequestDto interviewRequestDto, Long id) {
        this.id = id;
//        this.reviewOrder=reviewOrder;
//        this.interviewQ=interviewQ;
//        this.interviewAA=interviewAA;
//        this.interviewA=interviewA;
//        this.interviewQA=interviewQA;
        this.company = getCompany();
        this.state=getState();
    }

}
