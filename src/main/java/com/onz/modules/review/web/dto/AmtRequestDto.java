package com.onz.modules.review.web.dto;

import com.onz.common.domain.BaseEntity;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.review.domain.embed.Amt;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AmtRequestDto extends BaseEntity {

    //1 page
    @NotNull(message="반드시 입력해야 합니다.")
    private Long workExp; // 근무시 교사 연차

    private YN workExpOpenYn; // 근무시 교사 연차 공개 여부

    @NotNull(message="반드시 입력해야 합니다")
    private Long companyId;

    //2 page
    @Min(value = 1,message = "반드시 1보다 같거나 커야 합니다")
    @NotNull(message = "반드시 입력해야 합니다.")
    private Long amt; // 연봉

    private YN endAtmYn; // 퇴직금 여부

    private YN etcYn; //수당 여부
    //2-1 page 수당
    private String etcAmt; // 수당금액 배열 , 기준으로 etc_items와 매핑된다

    private String etcItems; //입력한 수당 idx 배열 {1 - 처우개선비 ,2 - 근무환경개선비, 3- 누리과정수당, 4- 기타

}
