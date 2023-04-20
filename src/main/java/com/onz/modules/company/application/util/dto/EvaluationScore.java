package com.onz.modules.company.application.util.dto;

import com.onz.modules.company.domain.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class EvaluationScore {
    private long scoreTot;          // 평가인증(총 평균)
    private long scoreCareEnv;      // 보육환경
    private long scoreOprManage;    // 운영관리
    private long scoreCareCourse;   // 보육과정
    private long scoreTeach;        // 상호작용과 교수법
    private long scoreHealth;       // 건강과 영양
    private long scoreSafty;        // 안전

    public EvaluationScore(Company company) {
        String[] evals = company.getEvalItems()!=null? company.getEvalItems().split(",") : null;
        long scoreTot =0;
        long scoreCareEnv =0;
        long scoreOprManage =0;
        long scoreCareCourse =0;
        long scoreTeach =0;
        long scoreHealth =0;
        long scoreSafty =0;
        if(evals !=null){
            try{
                scoreTot = evals.length>0 ? Math.round(Double.parseDouble(evals[0])) : 0;
                scoreCareEnv = evals.length>1 ? Math.round(Double.parseDouble(evals[1])) : 0;
                scoreOprManage = evals.length>2 ? Math.round(Double.parseDouble(evals[2])) : 0;
                scoreCareCourse = evals.length>3 ? Math.round(Double.parseDouble(evals[3])) : 0;
                scoreTeach = evals.length>4 ? Math.round(Double.parseDouble(evals[4])) : 0;
                scoreHealth = evals.length>5 ? Math.round(Double.parseDouble(evals[5])) : 0;
                scoreSafty = evals.length>6 ? Math.round(Double.parseDouble(evals[6])) : 0;
            }catch(NumberFormatException e){
                log.error("evals : {} :: {}",evals[0], e.getMessage());
            }
        }
        this.scoreTot = scoreTot;
        this.scoreCareEnv = scoreCareEnv;
        this.scoreOprManage = scoreOprManage;
        this.scoreCareCourse = scoreCareCourse;
        this.scoreTeach = scoreTeach;
        this.scoreHealth = scoreHealth;
        this.scoreSafty = scoreSafty;
    }
    @Builder
    public EvaluationScore(long scoreTot, long scoreCareEnv, long scoreOprManage, long scoreCareCourse, long scoreTeach, long scoreHealth, long scoreSafty) {
        this.scoreTot = scoreTot;
        this.scoreCareEnv = scoreCareEnv;
        this.scoreOprManage = scoreOprManage;
        this.scoreCareCourse = scoreCareCourse;
        this.scoreTeach = scoreTeach;
        this.scoreHealth = scoreHealth;
        this.scoreSafty = scoreSafty;
    }
}
