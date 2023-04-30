package com.biseo.modules.follower.web.dto;

import com.biseo.common.web.dto.response.enums.Gubn;
import com.biseo.modules.follower.domain.Follower;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
public class FollowerFindCompanyResponseDto {
    private Gubn gubn;
    private String userId;
    private String code;
    private ZonedDateTime createDt;

    public FollowerFindCompanyResponseDto(Follower follower) {
        this.gubn=follower.getAccount().getGubn();
        this.userId = follower.getAccount().getUserId();
        this.createDt = follower.getCreatedAt();
        this.code=follower.getAccount().getGrade().getGrade();
    }
}
