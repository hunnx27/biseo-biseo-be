package com.onz.modules.admin.member.livemember.web.dto;

import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
public class LiveMemberPointListResponse {
    @Getter
    private ZonedDateTime createdAt;
    @Getter
    private Integer amt;

    private PointTable code;

    @Getter
    private Integer totAmt;

    private String codeName;


    public String getCreatedAt() {
        String createdAtStr = "";
        if(this.createdAt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            createdAtStr = this.createdAt.format(formatter);
        }
        return createdAtStr;
    }

    public String getCodeName() {
        String codeName = this.code.getCodeName();
        if(this.code != null && this.codeName==null){
            codeName = this.code.getCodeName();
        }
        return codeName;
    }

}
