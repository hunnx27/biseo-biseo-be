package com.onz.modules.common.code.web.dto;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.common.code.application.TestData;
import com.onz.modules.common.code.domain.CommonCode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
@Setter
public class CommonCodeInitResponseDto {
    private Long id;
    private String code;
    private String codeSebu;
    private String codeName;
    private int fiveScore;
    private int score;
    private String gubn;
    private String sebuCode;

    public CommonCodeInitResponseDto(CommonCode commonCode) {
        this.id = commonCode.getId();
        this.code = commonCode.getCode();
        this.codeSebu = commonCode.getCodeSebu();
        this.codeName = commonCode.getCodeName();
        this.fiveScore = getFiveScore();
        this.score = getScore();
    }

    public CommonCodeInitResponseDto() {
    }
}
