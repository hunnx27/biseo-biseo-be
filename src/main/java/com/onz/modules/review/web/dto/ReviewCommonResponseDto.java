package com.onz.modules.review.web.dto;

import com.onz.common.web.dto.response.enums.YN;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.time.ZonedDateTime;

public class ReviewCommonResponseDto {
    /**
     * Common
     */
    @JsonIgnore
    public Long id;
    public String zoneCode;
    @Setter
    public String mapsidogunguName; //시도군,구 명칭
    public Long companyId; //기관 아이디
    public String companyName; //기관이름
    public String establishmentTypeName; //기관 분류
    public Long accountId; // 회원아이디
    public Long workExp; // 근무연차
//    public YN WorkExpOpenYn;
    public YN workExpOpenYn; // 근무시 교사 연차 공개 여부
    public ZonedDateTime ModifiedAt;
    public ZonedDateTime CreatedAt;

}
