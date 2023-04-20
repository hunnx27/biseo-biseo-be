package com.onz.modules.admin.reviews.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.company.domain.enums.EstablishmentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Setter
@NoArgsConstructor
public class ReviewsResponseDto {
    @Getter
    private String type;
    @Getter
    private Long id;

    private State state;
    private String stateName;

    private InterestCompany interestCompany;
    private String interestCompanyName;
    private EstablishmentType establishmentType;
    private String establishmentTypeName;
    @Getter
    private String companyName;
    @Getter
    private String userId;

    private ZonedDateTime createdAt;
    @Getter
    private String zonecode;


    public ReviewsResponseDto(Long id) {
        this.id = id;
    }

    public String getInterestCompanyName() {
        if (this.interestCompany != null && this.interestCompanyName == null) {
            interestCompanyName = this.interestCompany.getDesc();
        }
        return interestCompanyName;
    }

    public String getStateName() {
        if (this.state != null && this.stateName == null) {
            stateName = this.state.getName();
        }
        return stateName;
    }

    public String getEstablishmentTypeName() {
        if (this.establishmentType != null && this.establishmentTypeName == null) {
            establishmentTypeName = this.establishmentType.getName();
        }
        return establishmentTypeName;
    }

    public String getCreatedAt() {
        String createdAtStr = "";
        if(this.createdAt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            createdAtStr = this.createdAt.format(formatter);
        }
        return createdAtStr;
    }

}
