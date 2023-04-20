package com.onz.modules.admin.member.livemember.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class LiveMemberResponseWrapPDto {
    private LiveMemberPointResponse total;
    private List<LiveMemberPointListResponse> list;

    public LiveMemberResponseWrapPDto(LiveMemberPointResponse total, List<LiveMemberPointListResponse> list) {
        this.total = total;
        this.list = list;
    }
}
