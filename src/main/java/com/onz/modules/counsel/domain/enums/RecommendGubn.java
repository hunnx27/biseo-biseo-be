package com.onz.modules.counsel.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecommendGubn {
    R("Recommend", "추천"),
    N("Notification/Report", "신고")
    ;

    String code;
    String name;
}
