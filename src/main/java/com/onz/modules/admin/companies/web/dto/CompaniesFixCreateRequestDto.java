package com.onz.modules.admin.companies.web.dto;

import com.onz.common.web.dto.response.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CompaniesFixCreateRequestDto {
    @NotNull(message = " 반드시 입력해야 합니다")
    private String fixText;
    @NotNull(message = " 반드시 입력해야 합니다")
    private Long companyId;
}
