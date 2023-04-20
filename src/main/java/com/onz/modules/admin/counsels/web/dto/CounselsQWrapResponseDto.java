package com.onz.modules.admin.counsels.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CounselsQWrapResponseDto {
    private CountEvent count;
    private List<CounselsResponseDto> list;

    public CounselsQWrapResponseDto(CountEvent count, List<CounselsResponseDto> list) {
        this.count = count;
        this.list = list;
    }
}
