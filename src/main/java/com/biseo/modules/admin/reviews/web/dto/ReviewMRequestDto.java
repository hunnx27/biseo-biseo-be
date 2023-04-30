package com.biseo.modules.admin.reviews.web.dto;

import com.biseo.common.web.dto.response.enums.InterestCompany;
import com.biseo.common.web.dto.response.enums.State;
import com.biseo.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.Setter;

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
