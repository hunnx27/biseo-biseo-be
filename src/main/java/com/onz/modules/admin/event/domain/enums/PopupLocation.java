package com.onz.modules.admin.event.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PopupLocation {
    L1("기관리뷰작성후","1"),
    L2("연봉리뷰작성후","2"),
    L3("면접리뷰작성후","3"),
    L4("상담리뷰작성후","4"),
    L5("원앤집소식","5"),
    ;
    String name;
    String value;
}
