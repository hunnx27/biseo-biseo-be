package com.onz.modules.company.application.util;

import com.onz.OnzApplication;
import com.onz.modules.common.code.application.TestData;
import com.onz.modules.review.domain.CompanyReview;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 근속 : Sum A1 / count A1
 * 행사 : Sum B2 / count A1
 * 서류 : Sum B3 / count A1
 * 수업준비 : Sum B4 / count A1
 * 개선PC : Sum C5 / count A1
 * 자기개발 : Sum C6 / count A1
 * 육아휴직 : Sum C7 / count A1
 * 동료관계 : Sum D8 / count A1
 * 리더쉽 : Sum D9 / count A1
 */
public class AggregateCompany {
    @Getter
    private long count = 0;
    private double likeCodeSum = 0;
    private double itemB1Sum = 0;
    private double itemB2Sum = 0;
    private double itemB3Sum = 0;
    private double itemC1Sum = 0;
    private double itemC2Sum = 0;
    private double itemC3Sum = 0;
    private double itemD1Sum = 0;
    private double itemD2Sum = 0;
    private double starJumsuSum = 0;

    public void merge(AggregateCompany other) {
        this.count += other.count;
        this.likeCodeSum += other.likeCodeSum;
        this.itemB1Sum += other.itemB1Sum;
        this.itemB2Sum += other.itemB2Sum;
        this.itemB3Sum += other.itemB3Sum;
        this.itemC1Sum += other.itemC1Sum;
        this.itemC2Sum += other.itemC2Sum;
        this.itemC3Sum += other.itemC3Sum;
        this.itemD1Sum += other.itemD1Sum;
        this.itemD2Sum += other.itemD2Sum;
    }

    public void add(CompanyReview rv) {
        //rv.getItemB1() > A100
//        OnzApplication.hi.get("B100").get("score");

        this.count += 1;
        int likeCodeScore = rv.getLikeCode() != null ? Integer.parseInt(String.valueOf(TestData.hi.get(rv.getLikeCode()).get("score"))) : 0;
        this.likeCodeSum += likeCodeScore;
//        this.itemB1Sum += rv.getItemB1().getScore();
        int itemB1Score = rv.getItemB1() != null ? Integer.parseInt(String.valueOf(TestData.hi.get(rv.getItemB1()).get("score"))) : 0;
        this.itemB1Sum += itemB1Score;
        int itemB2Score = rv.getItemB2() != null ? Integer.parseInt(String.valueOf(TestData.hi.get(rv.getItemB2()).get("score"))) : 0;
        this.itemB2Sum += itemB2Score;
        int itemB3Score = rv.getItemB3() != null ? Integer.parseInt(String.valueOf(TestData.hi.get(rv.getItemB3()).get("score"))) : 0;
        this.itemB3Sum += itemB3Score;
        int itemC1Score = rv.getItemC1() != null ? Integer.parseInt(String.valueOf(TestData.hi.get(rv.getItemC1()).get("score"))) : 0;
        this.itemC1Sum += itemC1Score;
        int itemC2Score = rv.getItemC2() != null ? Integer.parseInt(String.valueOf(TestData.hi.get(rv.getItemC2()).get("score"))) : 0;
        this.itemC2Sum += itemC2Score;
        int itemC3Score = rv.getItemC3() != null ? Integer.parseInt(String.valueOf(TestData.hi.get(rv.getItemC3()).get("score"))) : 0;
        this.itemC3Sum += itemC3Score;
        int itemD1Score = rv.getItemD1() != null ? Integer.parseInt(String.valueOf(TestData.hi.get(rv.getItemD1()).get("score"))) : 0;
        this.itemD1Sum += itemD1Score;
        int itemD2Score = rv.getItemD2() != null ? Integer.parseInt(String.valueOf(TestData.hi.get(rv.getItemD2()).get("score"))) : 0;
        this.itemD2Sum += itemD2Score;

        this.starJumsuSum += rv.getStarJumsuTotalAvg();
    }

    public String toStringSum() {
        String rs = this.likeCodeSum + ", "
                + this.itemB1Sum + ", " + this.itemB2Sum + ", " + this.itemB3Sum + ", "
                + this.itemC1Sum + ", " + this.itemC2Sum + ", " + this.itemC3Sum + ", "
                + this.itemD1Sum + ", " + this.itemD2Sum;
        return rs;
    }

    public double getAvgTotal() {
        double totalSum = this.likeCodeSum
                + this.itemB1Sum + this.itemB2Sum + this.itemB3Sum
                + this.itemC1Sum + this.itemC2Sum + this.itemC3Sum
                + this.itemD1Sum + this.itemD2Sum;
        return Math.round(totalSum / this.count / 9 * 10) / 10;
    }

    public double getAvgLikeCode() {
        return Math.round(this.likeCodeSum / this.count * 10) / 10;
    }

    public double getAvgItemB1() {
        return Math.round(this.itemB1Sum / this.count * 10) / 10;
    }

    public double getAvgItemB2() {
        return Math.round(this.itemB2Sum / this.count * 10) / 10;
    }

    public double getAvgItemB3() {
        return Math.round(this.itemB3Sum / this.count * 10) / 10;
    }

    public double getAvgItemC1() {
        return Math.round(this.itemC1Sum / this.count * 10) / 10;
    }

    public double getAvgItemC2() {
        return Math.round(this.itemC2Sum / this.count * 10) / 10;
    }

    public double getAvgItemC3() {
        return Math.round(this.itemC3Sum / this.count * 10) / 10;
    }

    public double getAvgItemD1() {
        return Math.round(this.itemD1Sum / this.count * 10) / 10;
    }

    public double getAvgItemD2() {
        return Math.round(this.itemD2Sum / this.count * 10) / 10;
    }

    public double getAvgStarJumsu() {
        return Math.round(this.starJumsuSum / this.count * 10) / 10;
    }
}
