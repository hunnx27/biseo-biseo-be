package com.onz.modules.admin.member.livemember.web.dto;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.request.BasePageRequest;
import lombok.Getter;

@Getter
public class LiveMemberRequestDto extends BasePageRequest {

    private Gubn gubn;
    private final String userId;
    private final String createAtA;
    private final String createAtD;
    private final String birthYYYYo;
    private final String birthYYYYt;
    private final String sido;
    private final String sigungu;
    private final String reviewCount;
    private final String counselQCount;
    private final String counselACount;
    private final Long reviewCountNum;
    private final Long counselQCountNum;
    private final Long counselACountNum;
    private final String options;

    public LiveMemberRequestDto(String gubn, String userId, String createAtA, String createAtD, String birthYYYYo, String birthYYYYt, String sido, String sigungu
            , String reviewCount, String counselQCount, String counselACount, Long reviewCountNum, Long counselQCountNum, Long counselACountNum,String options) {
        if (gubn != null && !"".equals(gubn)) {
            this.gubn = Gubn.valueOf(gubn);
        }
        this.options=options;
        this.reviewCount=reviewCount;
        this.counselQCount=counselQCount;
        this.counselACount=counselACount;
        this.reviewCountNum=reviewCountNum;
        this.counselQCountNum=counselQCountNum;
        this.counselACountNum=counselACountNum;
        this.userId = userId;
        this.birthYYYYo = birthYYYYo;
        this.birthYYYYt = birthYYYYt;
        this.createAtD = createAtD;
        this.createAtA = createAtA;
        this.sido = sido;
        this.sigungu = sigungu;
    }
}
