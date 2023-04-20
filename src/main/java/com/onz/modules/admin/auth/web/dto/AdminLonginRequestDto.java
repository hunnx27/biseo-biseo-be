package com.onz.modules.admin.auth.web.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AdminLonginRequestDto {
     @NotNull(message = " 반드시 입력해야 합니다")
     private String userId;
     @NotNull(message = " 반드시 입력해야 합니다")
     private String passWord;

}
