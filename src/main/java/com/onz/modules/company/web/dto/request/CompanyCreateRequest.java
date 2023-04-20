package com.onz.modules.company.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CompanyCreateRequest {
    @NotNull(message = " 반드시 입력해야 합니다")
    private String code;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String name;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String lat;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String lng;
    @Min(value = 1,message = "반드시 1보다 같거나 커야 합니다")
    private int totalMemberCount;
    @Min(value = 1,message = "반드시 1보다 같거나 커야 합니다")
    private int currentMemberCount;
}
