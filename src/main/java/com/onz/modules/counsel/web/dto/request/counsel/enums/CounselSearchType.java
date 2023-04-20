package com.onz.modules.counsel.web.dto.request.counsel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CounselSearchType {
    QNA_ITEM("상담유형으로 검색"),
    HASHTAG("해시태그로 검색")
    ;

    String desc;
}
