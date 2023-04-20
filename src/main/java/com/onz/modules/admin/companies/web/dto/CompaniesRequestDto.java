package com.onz.modules.admin.companies.web.dto;

import com.onz.common.web.dto.request.BasePageRequest;
import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;

@Getter
public class CompaniesRequestDto  extends BasePageRequest {

    private EstablishmentType establishmentType;
    private final String siDo;
    private final String sigunGu;
    private final String createAtA;
    private final String createAtD;
    private final String createdAtOption;
    private final String companySearchOption;
    private final String companyNameCh;
    private final Long reviewCountNum;
    private final String reviewCountCheck;
    private final String options;
/*
CompanyCh = 기관 이름으로 검색할건데 Ch가 널이 아닐때만 검색
commpanyname -> name으로 검색
reviewcountcheck -> reviewcount 검색 
* */
    public CompaniesRequestDto(String establishmentType , String siDo, String sigunGu, String createAtA,
                               String createAtD, String companySearchOption, String companyNameCh, String reviewCountCheck, Long reviewCountNum, String options,String createdAtOption) {
        if(establishmentType != null && !"".equals(establishmentType)){
            this.establishmentType=EstablishmentType.valueOf(establishmentType);
        }
        this.siDo = siDo;
        this.sigunGu = sigunGu;
        this.createAtA = createAtA;
        this.createAtD = createAtD;
        this.companySearchOption = companySearchOption;
        this.companyNameCh = companyNameCh;
        this.reviewCountNum=reviewCountNum;
        this.reviewCountCheck=reviewCountCheck;
        this.options = options;
        this.createdAtOption=createdAtOption;
    }
}
