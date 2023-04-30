package com.biseo.modules.admin.reviews.web.dto;

import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.common.code.application.TestData;
import com.biseo.modules.review.domain.CompanyReview;
import com.biseo.modules.review.domain.embed.Images;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;


@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class CompanyReviewResponseDto {
    private String type;

    private Long id;

    private String state;
    private String stateName;
    private String txtAdmin;
    private String admin;

    private String interestCompany;
    private String establishmentType;
    private String zonecode;

    private String companyName;
    private String userId;

    private ZonedDateTime createdAt;

    private ZonedDateTime modifiedAt;

    private Long workExp;

    private YN expOpenYn;

    private String likecode;
    private String itemB2;
    private String itemB1;
    private String itemC1;
    private String itemB3;
    private String itemC3;
    private String itemC2;
    private String itemD1;
    private String itemD2;
    private double jumsuTotal;
    private double jumsuWorkload;
    private double jumsuJobSatisfaction;
    private double JumsuWorkAtmosphere;
    private Images images;

    public CompanyReviewResponseDto(CompanyReview cr) {
        this.type = "COMPANY";
        this.id = cr.getId();
        this.state = cr.getState().getName();
        this.txtAdmin = cr.getApprTxt();
        this.admin= cr.getApprId();
        this.interestCompany = cr.getCompany().getInterestCompany().getDesc();
        this.establishmentType = cr.getCompany().getEstablishmentType().getName();
        this.zonecode = cr.getCompany().getZonecode();
        this.companyName = cr.getCompany().getOfficeName();
        this.userId = cr.getAccount().getUserId();
        this.createdAt = cr.getCreatedAt();
        this.modifiedAt = cr.getModifiedAt();
        this.workExp = cr.getWork_exp();
        this.expOpenYn = cr.getWorkExpOpenYn();
        this.likecode = cr.getLikeCode();
        this.jumsuTotal = cr.getStarJumsuTotalAvg();
        this.jumsuJobSatisfaction=cr.getStarJumsuJobSatisfaction();
        this.jumsuWorkload=cr.getStarJumsuWorkload();
        this.JumsuWorkAtmosphere=cr.getStarJumsuWorkAtmosphere();
        this.images=cr.getImages();
//        this.itemB1=cr.getItemB1().getName();
        this.itemB1= String.valueOf(TestData.hi.get(cr.getItemB1()).get("name"));
        this.itemB2= String.valueOf(TestData.hi.get(cr.getItemB2()).get("name"));
        this.itemB3= String.valueOf(TestData.hi.get(cr.getItemB3()).get("name"));
        this.itemC1= String.valueOf(TestData.hi.get(cr.getItemC1()).get("name"));
        this.itemC2= String.valueOf(TestData.hi.get(cr.getItemC2()).get("name"));
        this.itemC3= String.valueOf(TestData.hi.get(cr.getItemC3()).get("name"));
        this.itemD1= String.valueOf(TestData.hi.get(cr.getItemD1()).get("name"));
        this.itemD2= String.valueOf(TestData.hi.get(cr.getItemD2()).get("name"));
    }

}
