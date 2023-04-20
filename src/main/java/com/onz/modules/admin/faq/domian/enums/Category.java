package com.onz.modules.admin.faq.domian.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    CG01("기관", "1"),
    CG02("연봉", "2"),
    CG03("면접", "3"),
    CG04("포인트", "4"),
    CG05("상담", "5"),
    CG06("설정", "6"),
    CG07("기타", "7");
    String name;
    String value;
}
