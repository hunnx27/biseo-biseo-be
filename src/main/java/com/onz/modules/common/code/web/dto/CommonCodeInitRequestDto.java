package com.onz.modules.common.code.web.dto;

import com.onz.common.web.dto.response.enums.YN;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommonCodeInitRequestDto {
    @NotNull(message = "반드시 입력해야 합니다")
    private String code;
    @NotNull(message = "반드시 입력해야 합니다")
    private String codeSebu;
    @NotNull(message = "반드시 입력해야 합니다")
    private String codeName;
    @NotNull(message = "반드시 입력해야 합니다")
    private YN useYn;
    @NotNull(message = "반드시 입력해야 합니다")
    private String bigo;
    @NotNull(message = "반드시 입력해야 합니다")
    private String createDt;

}
