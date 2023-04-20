package com.onz.modules.company.web.dto.reponse;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.company.domain.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDetailResponse {
    @JsonIgnore
    private Long id;
    private String interestCompanyName;
    private String establishmentTypeName;
    private String officeName;
    private String juso;
    private String run;
    private String director;
    private String openDtStr;
    @Enumerated(EnumType.STRING)
    private YN useYn;
    @Enumerated(EnumType.STRING)
    private YN evaluateYn;
    private Double fill;
    private long totPeople;
    private long currPeople;
    private String agePeoples;
    private String charItems;
    private String perItems;
    private String evalItems;
    private String zonecode;
    private String zipcode;
    private String phoneNum;
    private String faxNum;
    private String homepage;
    private String syncCode;
    private String eventBannerDateStr;
    private String mapsidogunguName;
    private String gps_x;
    private String gps_y;

    public CompanyDetailResponse(Company company){
        this.id=company.getId();
        this.interestCompanyName=company.getInterestCompany().getDesc();
        this.establishmentTypeName=company.getEstablishmentType().getName();
        this.officeName=company.getOfficeName();
        this.juso=company.getJuso();
        this.run=company.getRun();
        this.director=company.getDirector();
        this.useYn=company.getUseYn();
        this.evaluateYn=company.getEvaluateYn();
        this.fill=company.getFill();
        this.totPeople=company.getTotPeople();
        this.currPeople=company.getCurrPeople();
        this.agePeoples=company.getAgePeoples();
        this.charItems= String.join(",", company.getCharItems().stream().map(charItem -> charItem.getName()).collect(Collectors.toList()));
        this.perItems=company.getPerItems();
        this.evalItems=company.getEvalItems();
        this.zonecode=company.getZonecode();
        this.zipcode=company.getZipcode();
        this.phoneNum=company.getPhoneNum();
        this.faxNum=company.getFaxNum();
        this.homepage=company.getHomepage();
        this.syncCode=company.getSyncCode();
        this.openDtStr = company.getOpenDt()!=null? company.getOpenDt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) : "-";
        this.eventBannerDateStr = company.getEventBannerDate()!=null? company.getEventBannerDate().format(DateTimeFormatter.ofPattern("yyyy.MM.dd")) : "-";;
        this.gps_x = company.getGps_x();
        this.gps_y = company.getGps_y();

    }
}
