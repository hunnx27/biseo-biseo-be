package com.onz.modules.admin.reviews.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.State;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.company.domain.enums.EstablishmentType;
import com.onz.modules.review.domain.YearAmtReview;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Setter
@Getter
@NoArgsConstructor
public class AmtReviewResponseDto {
    private String type;

    private Long id;

    private State state;
    private String stateName;

    private String txtAdmin;

    private InterestCompany interestCompany;

    private EstablishmentType establishmentType;
    private String establishmentTypeName;

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

    public AmtReviewResponseDto(YearAmtReview rv) {
        this.type = "AMT";
        this.id = rv.getId();
        this.state = rv.getState()!=null?rv.getState():State.W;
        this.txtAdmin = rv.getApprTxt();
        this.interestCompany = rv.getCompany().getInterestCompany();
        this.establishmentType = rv.getCompany().getEstablishmentType();
        this.zonecode = rv.getCompany().getZonecode();
        this.companyName = rv.getCompany().getOfficeName();
        this.userId = rv.getAccount().getUserId();
        this.createdAt = rv.getCreatedAt();
        this.modifiedAt = rv.getModifiedAt();
        this.workExp = rv.getWorkExp();
        this.expOpenYn = rv.getWorkExpOpenYn();
        this.amt = rv.getAmt();
        this.etcYn = rv.getEtcYn();
        this.ectItems = rv.getEtcItems();
        this.etcAmt = rv.getEtcAmt();
        this.endExpYn = rv.getEndAtmYn();
    }
}
