package com.onz.modules.company.web.dto.reponse;

import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.web.dto.ReviewCommonResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyReviewListResponseDto extends ReviewCommonResponseDto {
    private String type="COMPANY";
    private String Txt;
    private double starJumsuTotalAvg;
    private int starJumsuWorkload;
    private int starJumsuJobSatisfaction;
    private int starJumsuWorkAtmosphere;

    public CompanyReviewListResponseDto(CompanyReview companyReview){
        this.id=companyReview.getId();
        this.zoneCode=companyReview.getCompany().getZonecode();
        this.companyId=companyReview.getCompany().getId();
        this.accountId=companyReview.getAccount().getId();
        this.companyName=companyReview.getCompany().getOfficeName();
        this.workExp= companyReview.getWork_exp();
        this.establishmentTypeName=companyReview.getCompany().getEstablishmentType().getName();
        this.workExpOpenYn=companyReview.getWorkExpOpenYn();
        this.Txt=companyReview.getTxt();
        this.zoneCode=companyReview.getCompany().getZonecode();
//        this.likeCode=companyReview.getLikeCode().name();
//        this.itemB1=companyReview.getItemB1().name();
//        this.itemB2=companyReview.getItemB2().name();
//        this.itemB3=companyReview.getItemB3().name();
//        this.itemC1=companyReview.getItemC1().name();
//        this.itemC2=companyReview.getItemC2().name();
//        this.itemC3=companyReview.getItemC3().name();
//        this.itemD1=companyReview.getItemD1().name();
//        this.itemD2=companyReview.getItemD2().name();
//        this.likeCodeName=companyReview.getLikeCode().getName();
//        this.itemB1Name=companyReview.getItemB1().getName();
//        this.itemB2Name=companyReview.getItemB2().getName();
//        this.itemB3Name=companyReview.getItemB3().getName();
//        this.itemC1Name=companyReview.getItemC1().getName();
//        this.itemC2Name=companyReview.getItemC2().getName();
//        this.itemC3Name=companyReview.getItemC3().getName();
//        this.itemD1Name=companyReview.getItemD1().getName();
//        this.itemD2Name=companyReview.getItemD2().getName();
        this.starJumsuTotalAvg = companyReview.getStarJumsuTotalAvg();
        this.starJumsuWorkload = companyReview.getStarJumsuWorkload();
        this.starJumsuJobSatisfaction =  companyReview.getStarJumsuJobSatisfaction();
        this.starJumsuWorkAtmosphere = companyReview.getStarJumsuWorkAtmosphere();
        }
    }
