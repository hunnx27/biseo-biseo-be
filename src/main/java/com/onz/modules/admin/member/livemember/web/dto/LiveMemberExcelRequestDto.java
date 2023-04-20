package com.onz.modules.admin.member.livemember.web.dto;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.modules.account.domain.enums.AuthProvider;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class LiveMemberExcelRequestDto {

    private Gubn gubn;
    private String userId;
    private AuthProvider snsType;
    private Integer point;
    private long companyReviewCnt;
    private long interviewReviewCnt;
    private long amtReviewCnt;
    private long counselQCnt;
    private long counselACnt;
    private ZonedDateTime createdAt;

}
