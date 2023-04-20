package com.onz.modules.admin.companies.web.dto;

import com.onz.modules.company.application.util.dto.EvaluationScore;
import com.onz.modules.company.application.util.dto.UserScore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompaniesDetailJipyoDto {
    private long id;
    private String officeName;

//    //평가인증 세부점수
//    private Score score;
    private EvaluationScore evaluationScore;
    private UserScore userScore;

    // 평가인증
    private long scoreA;
//            =evaluationScore.getScoreTot();
    // 유아교사 점수
    private long scoreT;
//            =userScore.getScoreTot();
    // 지표 점수
    private long scoreJ;
//    avgCnt != 0 ? (evalTot + userTot) / avgCnt : 0;
}
