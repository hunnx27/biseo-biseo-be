package com.onz.modules.admin.companies.application;

import lombok.Builder;
import lombok.Getter;

/*
 0세,1세,2세,3세,4세,5세
 영아혼합, 유아혼합, 특수 장애아
 */

@Getter
public class Child {
    long zeroAge;
    long oneAge;
    long twoAge;
    long threeAge;
    long forAge;
    long fiveAge;
    long infantMix;
    long childMix;
    long disabled;
    @Builder
    public Child(long zeroAge, long oneAge, long twoAge, long threeAge, long forAge, long fiveAge, long infantMix, long childMix, long disabled) {
        this.zeroAge = zeroAge;
        this.oneAge = oneAge;
        this.twoAge = twoAge;
        this.threeAge = threeAge;
        this.forAge = forAge;
        this.fiveAge=fiveAge;
        this.infantMix = infantMix;
        this.childMix = childMix;
        this.disabled = disabled;
    }
}
