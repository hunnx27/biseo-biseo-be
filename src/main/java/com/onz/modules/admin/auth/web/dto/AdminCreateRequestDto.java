package com.onz.modules.admin.auth.web.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AdminCreateRequestDto {
    @NotNull(message = " 반드시 입력해야 합니다")
    private String userId;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String team;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String name;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String pw;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String useYn;


}
