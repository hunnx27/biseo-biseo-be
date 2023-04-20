package com.onz.modules.common.pointHistory.web.dto.response;

import com.onz.common.domain.BaseEntity;
import com.onz.modules.account.domain.Account;
import com.onz.modules.common.pointHistory.domain.PointHistory;
import com.onz.modules.common.pointHistory.domain.embed.PointInfo;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.time.LocalDate;
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
