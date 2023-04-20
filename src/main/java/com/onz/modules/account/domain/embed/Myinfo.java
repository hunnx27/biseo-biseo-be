package com.onz.modules.account.domain.embed;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.InterestCompanyConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Myinfo {
//    @Enumerated(EnumType.STRING)
    @Convert(converter = InterestCompanyConverter.class)
    private InterestCompany interestCompany; // 관심 기관

    private String interestZone; // 관심지역

    private String birthYYYY; // 생년

    private String majorSchool; // 출신 학교

    private String majorDepartment; // 학과

    private String interestTag; //관심태그


    public Myinfo() {
    }

    public Myinfo(InterestCompany interestCompany, String interestZone, String birthYYYY, String majorSchool, String majorDepartment,String interestTag) {
        this.interestCompany = interestCompany;
        this.interestZone = interestZone;
        this.birthYYYY = birthYYYY;
        this.majorSchool = majorSchool;
        this.majorDepartment = majorDepartment;
        this.interestTag=interestTag;
    }
}
