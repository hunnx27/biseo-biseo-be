package com.biseo.modules.admin.companies.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CompaniesFixCreateRequestDto {
    @NotNull(message = " 반드시 입력해야 합니다")
    private String fixText;
    @NotNull(message = " 반드시 입력해야 합니다")
    private Long companyId;
}
