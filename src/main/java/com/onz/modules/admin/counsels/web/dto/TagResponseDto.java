package com.onz.modules.admin.counsels.web.dto;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.modules.counsel.domain.enums.QnaItem;
import com.querydsl.core.Tuple;
import lombok.Getter;

@Getter
public class TagResponseDto {
    private Gubn gubn;
    private String inputTag;
    private Long count;
}
