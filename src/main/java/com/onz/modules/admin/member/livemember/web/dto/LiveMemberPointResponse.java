package com.onz.modules.admin.member.livemember.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LiveMemberPointResponse {
    //--- Point --- //

        private String code;
//
    // total point 와 잔여포인트 개념 설계 다시 해야함
    private Integer ordersHistory;

    private Integer point;

    public LiveMemberPointResponse(Integer ordersHistory, Integer point) {
        this.ordersHistory = ordersHistory;
        this.point = point;
    }
}
