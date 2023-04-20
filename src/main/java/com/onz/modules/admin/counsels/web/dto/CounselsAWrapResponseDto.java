package com.onz.modules.admin.counsels.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CounselsAWrapResponseDto {
    private CountEvent count;
    private List<CounselsAresponseDto> list;

    public CounselsAWrapResponseDto(CountEvent count, List<CounselsAresponseDto> list) {
        this.count = count;
        this.list = list;
    }
}
