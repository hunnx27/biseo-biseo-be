package com.onz.modules.auth.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotNull(message = "반드시 입력해야 합니다")
    private String userId;
    @NotNull(message = "반드시 입력해야 합니다")
    private String password;

}
