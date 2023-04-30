package com.biseo.modules.admin.companies.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FixOption {
    A("ADD", "추가"),
    F("FIX", "수정");

    private String code;
    private String name;

}
