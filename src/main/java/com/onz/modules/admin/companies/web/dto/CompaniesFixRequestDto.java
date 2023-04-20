package com.onz.modules.admin.companies.web.dto;

import com.onz.common.web.dto.request.BasePageRequest;
import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;

@Getter
public class CompaniesFixRequestDto {

    private EstablishmentType establishmentType;
    private InterestCompany interestCompany;
    private final String siDo;
    private final String sigunGu;

    private final String requestEdtA;
    private final String requestEdtB;

    private final String companySearchOption;
    private final String officeName;

    public CompaniesFixRequestDto(String establishmentType,String interestCompany, String siDo, String sigunGu, String requestEdtA, String requestEdtB, String companySearchOption, String officeName) {
        if(establishmentType != null && !"".equals(establishmentType)){
            this.establishmentType=EstablishmentType.valueOf(establishmentType);
        }
        if(interestCompany!=null&& !"".equals(interestCompany)){
            this.interestCompany= InterestCompany.valueOf(interestCompany);
        }
        this.siDo = siDo;
        this.sigunGu = sigunGu;
        this.requestEdtA = requestEdtA;
        this.requestEdtB = requestEdtB;
        this.companySearchOption = companySearchOption;
        this.officeName = officeName;
    }
}
