package com.biseo.modules.admin.counsels.web.dto;

import com.biseo.common.web.dto.response.enums.Gubn;
import lombok.Getter;

@Getter
public class TagRequestDto {
    private final Gubn gubn;
    private final String tag;

    public TagRequestDto(Gubn gubn, String tag) {
        this.gubn = gubn;
        this.tag = tag;
    }
}
