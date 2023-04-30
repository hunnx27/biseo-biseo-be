package com.biseo.modules.counsel.web.dto.request.counsel;

import com.biseo.common.web.dto.response.enums.InterestCompany;
import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.counsel.domain.enums.QnaItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CounselQUpdateRequest {


    private String addedTagData;
    @NotNull(message = "는 필수 값 입니다.")
    private InterestCompany interestCommpanyName; // 관심 기관
    @NotNull(message = "는 필수 값 입니다.")
    private String relatedZone;
    @NotNull(message = "는 필수 값 입니다.")
    private QnaItem qnaItem;
    @NotNull(message = "는 필수 값 입니다.")
    @Size(min = 3,message = "3글자 이상 입력")
    private String txt;
    @NotNull(message = "는 필수 값 입니다.")
    private YN shortOpenYn;

    // 자동처리
//    @Convert(converter = GubnConverter.class)
//    private Gubn gubn;
//    private JobGubn jobGubn;
//    private QnaGubn qnaGubn=QnaGubn.Q;





}
