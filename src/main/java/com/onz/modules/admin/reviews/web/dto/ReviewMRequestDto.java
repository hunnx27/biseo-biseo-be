package com.onz.modules.admin.reviews.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.State;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ReviewMRequestDto {
    private InterestCompany interestCompany;
    private EstablishmentType establishmentType;
    private State state;
    private String siDo;
    private String sigunGu;
    private String createdAtOption;
    private String createdAtA;
    private String createdAtB;

    private String officeName;
}
