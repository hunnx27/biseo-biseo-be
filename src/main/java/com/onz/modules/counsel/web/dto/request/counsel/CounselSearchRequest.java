package com.onz.modules.counsel.web.dto.request.counsel;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.modules.counsel.web.dto.request.counsel.enums.CounselSearchType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CounselSearchRequest {

    private CounselSearchType counselSearchType;
    private String keyword;
    private Gubn gubn;

}
