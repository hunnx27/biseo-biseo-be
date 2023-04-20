package com.onz.modules.account.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum AuthProvider {
    local("L", "-"),
    facebook("F", "페이스북"),
    google("G","구글"),
    kakao("K","카카오톡"),
    naver("N","네이버"),
    apple("A","애플")
    ;
    private static final Map<String, String> CODE_MAP = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(AuthProvider::getCode, AuthProvider::name)));

    private String code;
    private String name;

    public static AuthProvider of(final String code){
        return AuthProvider.valueOf(CODE_MAP.get(code));
    }

}
