package com.onz.common.web.dto.response.enums;

import com.onz.modules.account.domain.enums.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum State {
    W("Wait", "승인대기"),
    A("Accept", "승인"),
    R("Reject", "승인거절")
    ;

    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(State::getCode, State::name)));

    private String code;
    private String name;

    public static State of(final String code){
        return State.valueOf(CODE_MAP.get(code));
    }
}
