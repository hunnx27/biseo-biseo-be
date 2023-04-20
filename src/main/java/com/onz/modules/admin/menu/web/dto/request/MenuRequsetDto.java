package com.onz.modules.admin.menu.web.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class MenuRequsetDto {
    @NotNull(message = "반드시 입력해야 합니다")
    private String link;
    @NotNull(message = "반드시 입력해야 합니다")
    private Long mainCode;
    @NotNull(message = "반드시 입력해야 합니다")
    private Long subCode;
    @NotNull(message = "반드시 입력해야 합니다")
    private String subject;
    @NotNull(message = "반드시 입력해야 합니다")
    private String role;
}
