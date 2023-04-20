package com.onz.modules.admin.reviews.web.dto;

import com.onz.common.web.dto.response.enums.State;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.domain.InterviewReviewItem;
import com.onz.modules.review.domain.embed.Images;
import com.onz.modules.review.web.dto.InterviewReviewDetailResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Setter
@NoArgsConstructor
@Getter
public class AllReviewResponsedto {
    private String type;
    private Long id;
    private String state;
    private String txtAdmin;
    private String interestCompany;

    private String establishmentType;

    private String zonecode;
    private String companyName;
    private String userId;

    private ZonedDateTime createdAt;

    private ZonedDateTime modifiedAt;

    private Long workExp;

    private YN expOpenYn;

    private Long amt;

    private YN etcYn;

    private String ectItems;

    private String etcAmt;
    private YN endExpYn;

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

    private String item1;

    private YN item2;

    private YN item3;

    private String item4;

    private String item5;

    private List<InterviewReviewResponseDto.InterviewReviewItems> qna;

    public AllReviewResponsedto(CompanyReviewResponseDto companyReviewResponseDto) {
        this.type = companyReviewResponseDto.getType();
        this.id = companyReviewResponseDto.getId();
        this.state = companyReviewResponseDto.getState();
        this.txtAdmin = companyReviewResponseDto.getTxtAdmin();
        this.interestCompany = companyReviewResponseDto.getInterestCompany();
        this.establishmentType = companyReviewResponseDto.getEstablishmentType();
        this.zonecode = companyReviewResponseDto.getZonecode();
        this.companyName = companyReviewResponseDto.getCompanyName();
        this.userId = companyReviewResponseDto.getUserId();
        this.createdAt = companyReviewResponseDto.getCreatedAt();
        this.modifiedAt = companyReviewResponseDto.getModifiedAt();
        this.workExp = companyReviewResponseDto.getWorkExp();
        this.expOpenYn = companyReviewResponseDto.getExpOpenYn();
        this.likecode = companyReviewResponseDto.getLikecode();
        this.jumsuTotal = companyReviewResponseDto.getJumsuTotal();
        this.jumsuJobSatisfaction=companyReviewResponseDto.getJumsuJobSatisfaction();
        this.jumsuWorkload=companyReviewResponseDto.getJumsuWorkload();
        this.JumsuWorkAtmosphere=companyReviewResponseDto.getJumsuWorkAtmosphere();
        this.images=companyReviewResponseDto.getImages();
        this.itemB1=companyReviewResponseDto.getItemB1();
        this.itemB2=companyReviewResponseDto.getItemB2();
        this.itemB3=companyReviewResponseDto.getItemB3();
        this.itemC1=companyReviewResponseDto.getItemC1();
        this.itemC2=companyReviewResponseDto.getItemC2();
        this.itemC3=companyReviewResponseDto.getItemC3();
        this.itemD1=companyReviewResponseDto.getItemD1();
        this.itemD2=companyReviewResponseDto.getItemD2();
    }
    public AllReviewResponsedto(InterviewReviewResponseDto interviewReviewResponseDto) {
        this.type = interviewReviewResponseDto.getType();
        this.id = interviewReviewResponseDto.getId();
        this.state = interviewReviewResponseDto.getState().getName();
        this.txtAdmin = interviewReviewResponseDto.getTxtAdmin();
        this.interestCompany = interviewReviewResponseDto.getInterestCompany().getDesc();
        this.establishmentType = interviewReviewResponseDto.getEstablishmentType().getName();
        this.zonecode = interviewReviewResponseDto.getZonecode();
        this.companyName = interviewReviewResponseDto.getCompanyName();
        this.userId = interviewReviewResponseDto.getUserId();
        this.createdAt = interviewReviewResponseDto.getCreatedAt();
        this.modifiedAt = interviewReviewResponseDto.getModifiedAt();
        this.workExp = interviewReviewResponseDto.getWorkExp();
        this.expOpenYn = interviewReviewResponseDto.getExpOpenYn();
        this.item1 = interviewReviewResponseDto.getItem1();
        this.item2 = interviewReviewResponseDto.getItem2();
        this.item3 = interviewReviewResponseDto.getItem3();
        this.item4 = interviewReviewResponseDto.getItem4();
        this.item5 = interviewReviewResponseDto.getItem5().getName();
        this.qna = interviewReviewResponseDto.getQna();
    }

    public AllReviewResponsedto(AmtReviewResponseDto amtReviewResponseDto) {
        this.type = amtReviewResponseDto.getType();
        this.id = amtReviewResponseDto.getId();
        this.state = amtReviewResponseDto.getState().getName()!=null?amtReviewResponseDto.getState().getName(): State.W.getName();
        this.txtAdmin = getTxtAdmin();
        this.interestCompany = amtReviewResponseDto.getInterestCompany().getDesc();
        this.establishmentType = amtReviewResponseDto.getEstablishmentType().getName();
        this.zonecode = amtReviewResponseDto.getZonecode();
        this.companyName = amtReviewResponseDto.getCompanyName();
        this.userId = amtReviewResponseDto.getUserId();
        this.createdAt = amtReviewResponseDto.getCreatedAt();
        this.modifiedAt = amtReviewResponseDto.getModifiedAt();
        this.workExp = amtReviewResponseDto.getWorkExp();
        this.expOpenYn = amtReviewResponseDto.getExpOpenYn();
        this.amt = amtReviewResponseDto.getAmt();
        this.etcYn = amtReviewResponseDto.getEtcYn();
        this.ectItems = amtReviewResponseDto.getEctItems();
        this.etcAmt = amtReviewResponseDto.getEtcAmt();
        this.endExpYn = amtReviewResponseDto.getEndExpYn();
    }
}
