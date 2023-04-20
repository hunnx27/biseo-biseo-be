package com.onz.modules.admin.companies.web.dto;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
public class CompaniesResponseDto {
    @Getter
    private Long id;
    private InterestCompany interestCompany;
    private String interestCompanyName;
    private EstablishmentType establishmentType;
    private String establishmentTypeName;

    @Getter
    private String officeName;
    @Getter
    private String zonecode;



    private ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;
    private YN isDelete;
    private String isDeleteN;

    @Getter
    private long companyReviewCnt;
    @Getter
    private long interviewReviewCnt;
    @Getter
    private long amtReviewCnt;
    @Getter
    private long jipyoScore;

    public String getInterestCompany() {
        String interestCompanyName;
        if(interestCompany==null){
            interestCompanyName = "";
        }else{
            interestCompanyName=interestCompany.getDesc();
        }
        if(this.interestCompany !=null && this.interestCompanyName==null){
            interestCompanyName=this.interestCompany.getDesc();
        }return interestCompanyName;
    }

    public String getEstablishmentType() {
        String establishmentTypeName;
        if(establishmentType==null){
            establishmentTypeName = "";
        }else{
            establishmentTypeName = this.establishmentType.getName();
        }
        if(this.establishmentType !=null&& this.establishmentTypeName==null){
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
    public String getModifiedAt(){
        String modifiedAtStr = "";
        if(this.modifiedAt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            modifiedAtStr = this.modifiedAt.format(formatter);
        }
        return modifiedAtStr;
    }

    public String getIsDelete(){
        String isDeleteN ="";
        if(this.isDelete!=null&& this.isDeleteN==null){
            isDeleteN=this.isDelete.name();
        }return isDeleteN;
    }
}
