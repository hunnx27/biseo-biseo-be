package com.biseo.modules.review.web.dto;

import com.biseo.common.web.dto.response.enums.InterestCompany;
import com.biseo.common.web.dto.request.BasePageRequest;
import com.biseo.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindEstaRequestDto extends BasePageRequest {
    private InterestCompany interestCompany;
    private EstablishmentType establishmentType;
    private String sido;
    private String gungu;

}
/*
{
  "interestCompany": "daycarecenter",
  "establishmentType": "all"
}
 */