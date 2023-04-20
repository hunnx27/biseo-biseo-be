package com.onz.modules.review.web.dto;

import com.onz.OnzApplication;
import com.onz.modules.common.code.application.TestData;
import com.onz.modules.review.domain.CompanyReview;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;


@Getter
@Setter
public class CompanyReviewDetailResponseDto {

    private final String keepWork;
    private final String eventCntJumsu;
    private final String docWrokJumsu;
    private final String readyClassJumsu;
    private final String personalPcJumsu;
    private final String selfDevJumsu;
    private final String kizRestJumsu;
    private final String partnershipJumsu;
    private final String ownerLeadershipJumsu;
    public Long workExp; // 근무연차

    private final String Txt;
    private final double starJumsuTotalAvg;
    private final int starJumsuWorkload;
    private final int starJumsuJobSatisfaction;
    private final int starJumsuWorkAtmosphere;
    private final String createDate;

    public CompanyReviewDetailResponseDto(CompanyReview companyReview) {
        this.Txt = companyReview.getTxt();
        this.keepWork = companyReview.getLikeCode() != null ? String.valueOf(TestData.hi.get(companyReview.getLikeCode()).get("name")) : null;
//        this.eventCntJumsu = companyReview.getItemB1().getName();
        this.eventCntJumsu = companyReview.getLikeCode() != null ? String.valueOf(TestData.hi.get(companyReview.getItemB1()).get("name")) : null;
        this.docWrokJumsu = companyReview.getLikeCode() != null ? String.valueOf(TestData.hi.get(companyReview.getItemB2()).get("name")) : null;
        this.readyClassJumsu = companyReview.getLikeCode() != null ? String.valueOf(TestData.hi.get(companyReview.getItemB3()).get("name")) : null;
        this.personalPcJumsu = companyReview.getLikeCode() != null ? String.valueOf(TestData.hi.get(companyReview.getItemC1()).get("name")) : null;
        this.selfDevJumsu = companyReview.getLikeCode() != null ? String.valueOf(TestData.hi.get(companyReview.getItemC2()).get("name")) : null;
        this.kizRestJumsu = companyReview.getLikeCode() != null ? String.valueOf(TestData.hi.get(companyReview.getItemC3()).get("name")) : null;
        this.partnershipJumsu = companyReview.getLikeCode() != null ? String.valueOf(TestData.hi.get(companyReview.getItemD1()).get("name")) : null;
        this.ownerLeadershipJumsu = companyReview.getLikeCode() != null ? String.valueOf(TestData.hi.get(companyReview.getItemD2()).get("name")) : null;
        this.starJumsuTotalAvg = companyReview.getStarJumsuTotalAvg();
        this.starJumsuWorkload = companyReview.getStarJumsuWorkload();
        this.starJumsuJobSatisfaction = companyReview.getStarJumsuJobSatisfaction();
        this.starJumsuWorkAtmosphere = companyReview.getStarJumsuWorkAtmosphere();
        this.workExp = companyReview.getWork_exp();
        this.createDate = companyReview.getCreatedAt() != null ? companyReview.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";

    }
}
