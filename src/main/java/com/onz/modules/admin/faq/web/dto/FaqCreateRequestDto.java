package com.onz.modules.admin.faq.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FaqCreateRequestDto {
    @NotNull(message = "반드시 입력해야 합니다")
    private String deviceGubn;
    @NotNull(message = "반드시 입력해야 합니다")
    private String category;
    @NotNull(message = "반드시 입력해야 합니다")
    private String title;
    @NotNull(message = "반드시 입력해야 합니다")
    private String content;
    @NotNull(message = "반드시 입력해야 합니다")
    private String useYn;
}
