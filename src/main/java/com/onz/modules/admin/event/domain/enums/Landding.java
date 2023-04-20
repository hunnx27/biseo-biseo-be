package com.onz.modules.admin.event.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Landding {
    L0("버튼 없음","0"),
    L1("리뷰작성_기관","1"),
    L2("리뷰작성_연봉","2"),
    L3("리뷰작성_면접","3"),
    L4("리뷰작성_상담","4"),
    L5("원앤집소식","5"),
    L6("랜딩 주소 입력","6"),
    ;
    String name;
    String value;

}
