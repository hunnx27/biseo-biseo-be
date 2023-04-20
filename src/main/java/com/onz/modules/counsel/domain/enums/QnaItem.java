package com.onz.modules.counsel.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QnaItem {
    // 예비교사
    QS01("01", "고민상담", "#고민 #상담 #고민상담"),
    QS02("02", "자격증", "#자격증"),
    QS03("03", "실습", "#실습 #자원봉사"),
    QS04("04", "취업", "#취업 #진로 #임용 "),
    QS05("05", "연봉", "#교사월급 #월급"),
    QS06("06", "기관문의", "#기관 "),
    // 유아교사
    QI01("01", "고민상담", "#고민 #상담 #고민상담"),
    QI02("02", "우리반아이", "#우리반 #학급 #아이 #원아"),
    QI03("03", "휴가/휴직", "#휴가 #휴직"),
    QI04("04", "호봉/수당", "#호봉 #수당"),
    QI05("05", "승급/임용", "#승급 #임용"),
    QI06("06", "동료/보조/대체", "#동료 #보조 #대체"),
    QI07("07", "기관문의", "#기관 #어린이집 #유치원"),
    ;

    String code;
    String name;
    String tag;
}
