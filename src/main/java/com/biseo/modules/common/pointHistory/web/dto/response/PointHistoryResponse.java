package com.biseo.modules.common.pointHistory.web.dto.response;

import com.biseo.common.domain.BaseEntity;
import com.biseo.modules.common.pointHistory.domain.PointHistory;
import com.biseo.modules.common.pointHistory.domain.enums.PointTable;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class PointHistoryResponse extends BaseEntity {

    private Long id;
    private Long  accountId;
    private int amt;
    private String description;
    private String createDt;

    public PointHistoryResponse(PointHistory pointHistory) {
        this.id = pointHistory.getId();
        this.accountId = pointHistory.getAccount().getId();
        PointTable pt = pointHistory.getPointInfo().getCode();
        this.amt = pt.getAmt();
        this.description = pt.getCodeName();
        ZonedDateTime zdt = pointHistory.getCreatedAt();
        String result = zdt.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.createDt = result;

    }
}
