package com.onz.modules.company.web.dto.reponse;

import com.onz.modules.company.application.util.AggregateCompany;
import com.onz.modules.company.application.util.dto.EvaluationScore;
import com.onz.modules.company.application.util.dto.UserScore;
import com.onz.modules.company.domain.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@NoArgsConstructor
@Slf4j
public class CompanyJipyoResponse {
    private Long id;
    private String officeName;
    private String establishmentTypeName;
    private long jipyoScore;
    private double starAvgJumsu;
    private long reviewCount;
    private EvaluationScore escore;
    private UserScore uscore;
    @Setter
    private String mapsidogunguName;

    public CompanyJipyoResponse(Company company, AggregateCompany aggCompany) {
        this.id = company.getId();
        this.officeName = company.getOfficeName();
        this.establishmentTypeName = company.getEstablishmentType().getName();
        this.starAvgJumsu = aggCompany!=null? Math.round(aggCompany.getAvgStarJumsu() * 10) / (double)10 : 0.0;
        this.reviewCount = aggCompany.getCount();
        this.escore = new EvaluationScore(company);
        this.uscore = new UserScore(aggCompany);
        int avgCnt = 0;
        long evalTot = this.escore.getScoreTot();
        long userTot = this.uscore.getScoreTot();
        if(evalTot>0) avgCnt++;
        if(userTot>0) avgCnt++;
        this.jipyoScore = avgCnt!=0 ? (evalTot + userTot) / avgCnt : 0;
    }

//    @Getter
//    public class EvaluationScore{
//        private long scoreTot;          // 평가인증(총 평균)
//        private long scoreCareEnv;      // 보육환경
//        private long scoreOprManage;    // 운영관리
//        private long scoreCareCourse;   // 보육과정
//        private long scoreTeach;        // 상호작용과 교수법
//        private long scoreHealth;       // 건강과 영양
//        private long scoreSafty;        // 안전
//
//        public EvaluationScore(Company company) {
//            String[] evals = company.getEvalItems()!=null? company.getEvalItems().split(",") : null;
//            long scoreTot =0;
//            long scoreCareEnv =0;
//            long scoreOprManage =0;
//            long scoreCareCourse =0;
//            long scoreTeach =0;
//            long scoreHealth =0;
//            long scoreSafty =0;
//            if(evals !=null){
//                try{
//                    scoreTot = evals.length>0 ? Math.round(Double.parseDouble(evals[0])) : 0;
//                    scoreCareEnv = evals.length>1 ? Math.round(Double.parseDouble(evals[1])) : 0;
//                    scoreOprManage = evals.length>2 ? Math.round(Double.parseDouble(evals[2])) : 0;
//                    scoreCareCourse = evals.length>3 ? Math.round(Double.parseDouble(evals[3])) : 0;
//                    scoreTeach = evals.length>4 ? Math.round(Double.parseDouble(evals[4])) : 0;
//                    scoreHealth = evals.length>5 ? Math.round(Double.parseDouble(evals[5])) : 0;
//                    scoreSafty = evals.length>6 ? Math.round(Double.parseDouble(evals[6])) : 0;
//                }catch(NumberFormatException e){
//                    log.error("evals : {} :: {}",evals[0], e.getMessage());
//                }
//            }
//            this.scoreTot = scoreTot;
//            this.scoreCareEnv = scoreCareEnv;
//            this.scoreOprManage = scoreOprManage;
//            this.scoreCareCourse = scoreCareCourse;
//            this.scoreTeach = scoreTeach;
//            this.scoreHealth = scoreHealth;
//            this.scoreSafty = scoreSafty;
//        }
//    }
//    @Getter
//    public class UserScore{
//        private long scoreTot;          // 총 평균 점수
//        private long scoreKeepWork;     // 근속
//        private long scoreEvent;        // 행사
//        private long scoreDcoWork;      // 서류
//        private long scoreReadyClass;   // 수업준비
//        private long scorePersonalPC;   // 개인PC
//        private long scoreSelfDev;      // 자기개발
//        private long scoreKizRest;      // 육아휴직
//        private long scorePartnership;  // 동료관계
//        private long scoreLeadership;   // 리더쉽
//
//        public UserScore(AggregateCompany aggCompany) {
//            this.scoreTot = 0;
//            this.scoreKeepWork = 0;
//            this.scoreEvent = 0;
//            this.scoreDcoWork = 0;
//            this.scoreReadyClass = 0;
//            this.scorePersonalPC = 0;
//            this.scoreSelfDev = 0;
//            this.scoreKizRest = 0;
//            this.scorePartnership = 0;
//            this.scoreLeadership = 0;
//            if(aggCompany != null){
//                this.scoreTot = Math.round(aggCompany.getAvgTotal());
//                this.scoreKeepWork = Math.round(aggCompany.getAvgLikeCode());
//                this.scoreEvent = Math.round(aggCompany.getAvgItemB1());
//                this.scoreDcoWork = Math.round(aggCompany.getAvgItemB2());
//                this.scoreReadyClass = Math.round(aggCompany.getAvgItemB3());
//                this.scorePersonalPC = Math.round(aggCompany.getAvgItemC1());
//                this.scoreSelfDev = Math.round(aggCompany.getAvgItemC2());
//                this.scoreKizRest = Math.round(aggCompany.getAvgItemC3());
//                this.scorePartnership = Math.round(aggCompany.getAvgItemD1());
//                this.scoreLeadership = Math.round(aggCompany.getAvgItemD2());
//            }
//        }
//    }
}

