package com.onz.modules.company.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstablishmentType {
    C01("국공립", "01"),
    C02("사회복지법인", "02"),
    C03("법인,단체등", "03"),
    C04("민간", "04"),
    C05("가정", "05"),
    C06("직장", "06"),
    C07("부모협동", "07"),
    C99("기타", "99"),
    P01("국립","01"),
    P02("공립(단설)","02"),
    P03("공립(병설)","03"),
    P04("사립(사인)","04"),
    P05("사립(법인)","05"),
    P99("기타","99"),
    ;

    String name;
    String value;
}
