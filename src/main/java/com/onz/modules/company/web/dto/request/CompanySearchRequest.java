package com.onz.modules.company.web.dto.request;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.request.BasePageRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanySearchRequest extends BasePageRequest {
    private InterestCompany interestCompany;
    private String code;
    private String name;
    }
