package com.onz.modules.common.grade.web.dto;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.common.grade.domain.Grade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GradeListResponseDto {
    //등급명/아이콘/누적포인트 구간/ 등급 필수 조건/ 누적 회원수/등급사용/수정//Pageable!
    private String code;
    private String iconUrl;
    private Long startTot;
    private Long endTot;
    private Long replayRate;
    private Long replayCnt;
    private YN useYn;

    public GradeListResponseDto(Grade grade) {
        this.code = grade.getGrade();
        this.iconUrl = grade.getIconUrl();
        this.startTot = grade.getStartTot();
        this.endTot = grade.getEndTot();
        this.replayRate = grade.getReplayRate();
        this.replayCnt = grade.getReplyCnt();
        this.useYn = grade.getUseYn();
    }
}
