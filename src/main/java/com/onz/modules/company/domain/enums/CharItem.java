package com.onz.modules.company.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum     CharItem {
    CHAR01("일반", "1"),
    CHAR02("영아 전담", "2"),
    CHAR03("장애아 전문", "3"),
    CHAR04("장애아 통합", "4"),
    CHAR05("방과후 전담", "5"),
    CHAR06("방과후 통합", "6"),
    CHAR07("시간연장형", "7"),
    CHAR08("휴일보육", "8"),
    CHAR09("24시간", "9"),
    CHAR10("일시보육", "10"),
    CHARN("선택안함", "N")
    ;

    String name;
    String value;
}
