package com.onz.modules.company.application.util.dto;

import com.onz.modules.company.application.util.AggregateCompany;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserScore{
    private long scoreTot;          // 총 평균 점수
    private long scoreKeepWork;     // 근속
    private long scoreEvent;        // 행사
    private long scoreDcoWork;      // 서류
    private long scoreReadyClass;   // 수업준비
    private long scorePersonalPC;   // 개인PC
    private long scoreSelfDev;      // 자기개발
    private long scoreKizRest;      // 육아휴직
    private long scorePartnership;  // 동료관계
    private long scoreLeadership;   // 리더쉽

    public UserScore(AggregateCompany aggCompany) {
        this.scoreTot = 0;
        this.scoreKeepWork = 0;
        this.scoreEvent = 0;
        this.scoreDcoWork = 0;
        this.scoreReadyClass = 0;
        this.scorePersonalPC = 0;
        this.scoreSelfDev = 0;
        this.scoreKizRest = 0;
        this.scorePartnership = 0;
        this.scoreLeadership = 0;
        if(aggCompany != null){
            this.scoreTot = Math.round(aggCompany.getAvgTotal());
            this.scoreKeepWork = Math.round(aggCompany.getAvgLikeCode());
            this.scoreEvent = Math.round(aggCompany.getAvgItemB1());
            this.scoreDcoWork = Math.round(aggCompany.getAvgItemB2());
            this.scoreReadyClass = Math.round(aggCompany.getAvgItemB3());
            this.scorePersonalPC = Math.round(aggCompany.getAvgItemC1());
            this.scoreSelfDev = Math.round(aggCompany.getAvgItemC2());
            this.scoreKizRest = Math.round(aggCompany.getAvgItemC3());
            this.scorePartnership = Math.round(aggCompany.getAvgItemD1());
            this.scoreLeadership = Math.round(aggCompany.getAvgItemD2());
        }
    }

    @Builder
    public UserScore(long scoreTot, long scoreKeepWork, long scoreEvent, long scoreDcoWork, long scoreReadyClass, long scorePersonalPC, long scoreSelfDev, long scoreKizRest, long scorePartnership, long scoreLeadership) {
        this.scoreTot = scoreTot;
        this.scoreKeepWork = scoreKeepWork;
        this.scoreEvent = scoreEvent;
        this.scoreDcoWork = scoreDcoWork;
        this.scoreReadyClass = scoreReadyClass;
        this.scorePersonalPC = scorePersonalPC;
        this.scoreSelfDev = scoreSelfDev;
        this.scoreKizRest = scoreKizRest;
        this.scorePartnership = scorePartnership;
        this.scoreLeadership = scoreLeadership;
    }
}
