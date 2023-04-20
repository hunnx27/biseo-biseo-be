package com.onz.modules.account.web.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountMyinfoUpdateRequest {

    private String interestCompanyName; // 관심 기관
    private String interestZone; // 관심지역
    private String birthYYYY; // 생년
    private String majorSchool; // 출신 학교
    private String majorDepartment; // 학과
    private String interestTag;

}
