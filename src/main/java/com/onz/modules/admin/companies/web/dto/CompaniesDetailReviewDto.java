package com.onz.modules.admin.companies.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompaniesDetailReviewDto {

    private long id;
    private String officeName;
    private long reviewCnt;
    private long interviewCnt;
    private long amtCnt;
    private long totalCnt;

}
