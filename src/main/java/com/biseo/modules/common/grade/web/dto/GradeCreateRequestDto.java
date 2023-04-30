package com.biseo.modules.common.grade.web.dto;

import com.biseo.common.web.dto.response.enums.YN;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class GradeCreateRequestDto {
    @NotNull(message = "반드시 입력해야 합니다")
    private String grade;
    @NotNull(message = "반드시 입력해야 합니다")
    private Long startTot;
    @NotNull(message = "반드시 입력해야 합니다")
    private Long endTot;
    @NotNull(message = "반드시 입력해야 합니다")
    private Long replayRate;
    @NotNull(message = "반드시 입력해야 합니다")
    private Long replyCnt;
    @NotNull(message = "반드시 입력해야 합니다")
    private YN useYn;
}
