package com.biseo.modules.admin.companies.web.dto;

import com.biseo.common.web.dto.response.enums.InterestCompany;
import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.admin.companies.application.Child;
import com.biseo.modules.admin.companies.application.Staff;
import com.biseo.modules.company.application.util.dto.EvaluationScore;
import com.biseo.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
public class CompaniesDetailResponseDto {
    private Long id;
    private String officeName;
    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;

    // 기관 기본정보
    private InterestCompany interestCompany;
    private String juso;
    private String zonecode;
    private EstablishmentType establishmentType;
    private YN isDelete;
    private String director;
    private ZonedDateTime openDt;
    private YN useYn;
    // 아동 연령별 현황
    private Double fill; //  정원퍼센트
    private long totPeople;// 기관 정원

    //기관특성
    private long currPeople; //직원들
    //평가인증EvaluationScore
    private long jipyoScore; //총점수
    private Child child;
    private Staff staff;
    private EvaluationScore evaluationScore;

}
