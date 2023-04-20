package com.onz.modules.follower.web.dto;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.modules.company.domain.enums.EstablishmentType;
import com.onz.modules.follower.domain.Follower;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
