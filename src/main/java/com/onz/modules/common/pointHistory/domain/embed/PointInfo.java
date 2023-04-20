package com.onz.modules.common.pointHistory.domain.embed;

import com.onz.modules.account.domain.Account;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import com.onz.modules.common.pointHistory.domain.enums.PointTableConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class PointInfo {
    @Convert(converter = PointTableConverter.class)
    private PointTable code;
    private Integer totAmt;
    private Integer amt;
    private String description;


    public PointInfo() {
    }

    public PointInfo(PointTable pointTable, Account account) {
        this.code = pointTable;
        this.totAmt = account.getPoint() + pointTable.getAmt();
        this.amt = pointTable.getAmt();
        this.description = pointTable.getCodeName();
        account.setPoint(this.totAmt); // account테이블도 totAmt를 Account의 point 컬럼에 Update해줌.
    }
}
