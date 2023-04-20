package com.onz.modules.admin.companies.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Getter
@AllArgsConstructor
public enum FixOption {
    A("ADD", "추가"),
    F("FIX", "수정");

    private String code;
    private String name;

}
