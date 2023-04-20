package com.onz.modules.admin.counsels.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CounselsRequestDto {
    private String interestCompany;
    private String counselState;
    private String createAdt;
    private String createBdt;
    private String txt;
}
