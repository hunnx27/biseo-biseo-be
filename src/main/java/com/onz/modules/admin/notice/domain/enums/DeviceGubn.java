package com.onz.modules.admin.notice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceGubn {
    P("전체","1"),
    A("안드로이드","2"),
    I("아이폰","3")
    ;
    String name;
    String value;



}
