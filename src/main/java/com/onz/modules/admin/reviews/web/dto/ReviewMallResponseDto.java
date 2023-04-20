package com.onz.modules.admin.reviews.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Setter
public class ReviewMallResponseDto {
    @Getter
    private String type;
    @Getter
    private Long id;

    private State state;
    private String stateName;
    private InterestCompany interestCompany;
    private String interestCompanyName;
    private EstablishmentType establishmentType;
    private String establishmentTypeValueName;
    @Getter
    private String officeName;
    @Getter
    private String userId;
    private ZonedDateTime createdAt;
    private String createdAtStr;
    private ZonedDateTime apprDt;
    private String apprDtStr;
    @Getter
    private String zonecode;
    @Getter
    private String apprId;
    @Getter
    private String apprTxt;

    public String getInterestCompany() {
        String interestCompanyName = this.interestCompany.getDesc();
        if(this.interestCompany !=null && this.interestCompanyName==null){
            interestCompanyName=this.interestCompany.getDesc();
        }return interestCompanyName;
    }
    public String getState() {
        String stateName = this.state.getName();
        if(this.state !=null && this.stateName==null){
            stateName=this.state.getName();
        }return stateName;
    }
    public String getEstablishmentType() {
        String establishmentTypeName = this.establishmentType.getName();
        if(this.establishmentType !=null&& this.establishmentTypeValueName==null){
            establishmentTypeName = this.establishmentType.getName();
        }return establishmentTypeName;
    }
    public String getCreatedAt(){
        String createdAtStr = "";
        if(this.createdAt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            createdAtStr = this.createdAt.format(formatter);
        }
        return createdAtStr;
    }
    public String getApprDt(){
        String apprTxt = "";
        if(this.apprDt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            apprTxt = this.apprDt.format(formatter);
        }
        return apprTxt;
    }
/*
순서 맞춰야함!!!!! 생성자에 들어오는 매개변수 순서 주의!
 */
}
