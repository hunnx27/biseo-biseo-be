package com.onz.modules.admin.companies.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.ProcessT;
import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CompaniesFixResponseDto {

    @Getter
    private Long id;
    //기관유형
    private InterestCompany interestCompany;
    private String interestCompanyName;
    //설립유형
    private EstablishmentType establishmentType;
    private String establishmentTypeName;
    //기관면
    @Getter
    private String officeName;
    //지역
    @Getter
    private String zonecode;
    //요청자id
    @Getter
    private String userId;
    //요청일

    private ZonedDateTime createDt;

    private String requestEdtStr;

    private ProcessT process;
    private String stateName;

    public String getInterestCompany() {
        String interestCompanyName = this.interestCompany.getDesc();
        if(this.interestCompany !=null && this.interestCompanyName==null){
            interestCompanyName=this.interestCompany.getDesc();
        }return interestCompanyName;
    }

    public String getEstablishmentType() {
        String establishmentTypeName = this.establishmentType.getName();
        if(this.establishmentType !=null&& this.establishmentTypeName==null){
            establishmentTypeName = this.establishmentType.getName();
        }return establishmentTypeName;
    }
    public String getCreateDt(){
        String requestEdtStr = "";
        if(this.createDt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            requestEdtStr = this.createDt.format(formatter);
        }
        return requestEdtStr;
    }
    public String getProcess(){
        String isDeleteN ="";
        if(this.process!=null&& this.stateName==null){
            isDeleteN=this.process.name();
        }return isDeleteN;
    }
}
