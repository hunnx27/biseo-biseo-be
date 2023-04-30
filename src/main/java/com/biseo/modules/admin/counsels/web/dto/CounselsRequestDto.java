package com.biseo.modules.admin.counsels.web.dto;

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
