package com.onz.common.web.dto.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ProcessT {
    W("Wait", "승인대기"),
    A("Accept", "접수"),
    C("Complete", "처리완료")
    ;
    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(ProcessT::getCode, ProcessT::name)));

    private String code;
    private String name;

    public static ProcessT of(final String code){
        return ProcessT.valueOf(CODE_MAP.get(code));
    }
}

