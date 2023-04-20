package com.onz.modules.account.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AccountCreateRequest {
    @NotNull(message = " 반드시 입력해야 합니다")
    private String name;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String userId;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String age;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String location;
    @NotNull(message = " 반드시 입력해야 합니다")
    private String password;

}
