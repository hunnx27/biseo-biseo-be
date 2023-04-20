package com.onz.modules.company.web.dto.reponse;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class YearAmtAvgResponseDto {
    private long totalAmt;
    private long minAmt;
    private long maxAmt;

    @Builder
    public YearAmtAvgResponseDto(Long totalAmt, Long minAmt, Long maxAmt) {
        this.totalAmt = totalAmt;
        this.minAmt = minAmt;
        this.maxAmt = maxAmt;
    }
}
