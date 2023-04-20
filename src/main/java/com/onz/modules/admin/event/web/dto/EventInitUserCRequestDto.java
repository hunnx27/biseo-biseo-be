package com.onz.modules.admin.event.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EventInitUserCRequestDto {
    @NotNull(message = "반드시 입력해야 합니다")
    private String phone;
    @NotNull(message = "반드시 입력해야 합니다")
    private String answer;
    @NotNull(message = "반드시 입력해야 합니다")
    private String chu;
}
