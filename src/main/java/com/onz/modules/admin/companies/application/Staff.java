package com.onz.modules.admin.companies.application;

import lombok.Builder;
import lombok.Getter;

/*
 총합, 원장 , 보육교사, 특수교사, 치료사, 영양사, 간호사, 간호조무사, 조리원, 사무원
 총 input 12개 output 10개
 */

@Getter
public class Staff {
    long total;
    long director;
    long daycareT;
    long specialT;
    long healerT;
    long dietitianT;
    long nurseT;
    long nursingAide;
    long cook;
    long officeWorker;

    @Builder
    public Staff(long total, long director, long daycareT, long specialT, long healerT, long dietitianT, long nurseT, long nursingAide, long cook, long officeWorker) {
        this.total = total;
        this.director = director;
        this.daycareT = daycareT;
        this.specialT = specialT;
        this.healerT = healerT;
        this.dietitianT = dietitianT;
        this.nurseT = nurseT;
        this.nursingAide = nursingAide;
        this.cook = cook;
        this.officeWorker = officeWorker;
    }
}
