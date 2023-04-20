package com.onz.modules.company.web.dto.reponse;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InterviewcountResponsedto {
    private int writCount; //필기
    private int patCount; // 인적성
    private int mockCount; //모의고사

    //난이도
    private double lowLevCount;
    private double midLevCount;
    private double highLevCount;
    private String totalLevel;
    //합격여부
    private double goalCount;
    private double waitCount;
    private double noCount;
    private String totalGoal;
    public InterviewcountResponsedto(int writCount, int patCount, int mockCount, double lowLevCount, double midLevCount, double highLevCount, String totalLevel,
     double goalCount, double waitCount, double noCount, String totalGoal) {
        this.writCount = writCount;
        this.patCount = patCount;
        this.mockCount = mockCount;
        this.lowLevCount = lowLevCount;
        this.midLevCount = midLevCount;
        this.highLevCount = highLevCount;
        this.totalLevel = totalLevel;
        this.goalCount=goalCount;
        this.waitCount=waitCount;
        this.noCount=noCount;
        this.totalGoal=totalGoal;
    }
}
