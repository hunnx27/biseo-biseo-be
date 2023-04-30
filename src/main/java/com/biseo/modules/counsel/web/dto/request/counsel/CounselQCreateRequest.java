package com.biseo.modules.counsel.web.dto.request.counsel;

import com.biseo.common.web.dto.response.enums.InterestCompany;
import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.counsel.domain.enums.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CounselQCreateRequest {

    private String addedTagData;
    private InterestCompany interestCompanyName; // 관심 기관
    private String relatedZone;
    private QnaItem qnaItem;
    private String txt;
    private YN shortOpenYn;

    // 자동처리
//    @Convert(converter = GubnConverter.class)
//    private Gubn gubn;
//    private JobGubn jobGubn;
//    private QnaGubn qnaGubn=QnaGubn.Q;





}
