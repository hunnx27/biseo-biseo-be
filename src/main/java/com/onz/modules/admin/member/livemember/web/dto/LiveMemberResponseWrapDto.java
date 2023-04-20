package com.onz.modules.admin.member.livemember.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class LiveMemberResponseWrapDto {
    @Setter
    private Long cnt;

    private LiveMemberResponseDto total;
    private List<LiveMemberResponseDto> list;

    public LiveMemberResponseWrapDto(Long cnt, LiveMemberResponseDto total, List<LiveMemberResponseDto> list) {
        this.cnt = cnt;
        this.total = total;
        this.list = list;
    }
}
