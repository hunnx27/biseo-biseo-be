package com.onz.modules.counsel.web.dto.response.counsel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.Counsel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselDetailResponse {

    private Long id;
    private String counselStateCode;
    private String counselStateName;
    private String gubnName;
    private String addedTagData;
    private String txt;
    private String createDate;
    private long reportCnt;
    private Long accountId;
    @JsonProperty(value="isMine")
    private Boolean isMine;

    private String interestCompanyName;
    private String interestCompanyDesc;
    private String relatedZone;
    private String si;
    private String gugun;
    private String qnaItem;
    private String shortOpenYn;
    private String opneYn;
    private List<String> images = new ArrayList<>();




    public CounselDetailResponse(Counsel counsel, Account me) {
        this.id = counsel.getId();
        this.counselStateCode = counsel.getCounselState()!=null ? counsel.getCounselState().name() : "";
        this.counselStateName = counsel.getCounselState()!=null ? counsel.getCounselState().getName() : "";
        this.gubnName = counsel.getGubn()!=null? counsel.getGubn().getName() : "";
        this.txt = counsel.getTxt();
        this.reportCnt = counsel.getReportCnt();
        this.addedTagData = counsel.getInputTag();
        this.createDate = counsel.getCreatedAt()!=null? counsel.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) : "";
        this.accountId = counsel.getAccount().getId();
        this.isMine = (counsel.getAccount().getId() == me.getId());
        this.interestCompanyName = counsel.getInterestCompany()!=null? counsel.getInterestCompany().name() : null;
        this.interestCompanyDesc = counsel.getInterestCompany()!=null? counsel.getInterestCompany().getDesc() : null;
        this.relatedZone = counsel.getRelatedZone();
        this.si = counsel.getRelatedZone();
        this.gugun = counsel.getRelatedZone();
        this.qnaItem = counsel.getQnaItem()!=null? counsel.getQnaItem().name() : "Q1";
        if(counsel.getImages()!=null){
            if(counsel.getImages().getImage1()!=null) images.add(counsel.getImages().getImage1());
            if(counsel.getImages().getImage2()!=null) images.add(counsel.getImages().getImage2());
            if(counsel.getImages().getImage3()!=null) images.add(counsel.getImages().getImage3());
            if(counsel.getImages().getImage4()!=null) images.add(counsel.getImages().getImage4());
            if(counsel.getImages().getImage5()!=null) images.add(counsel.getImages().getImage5());
        }

        this.opneYn = counsel.getOpenYn().name();
        this.shortOpenYn = counsel.getShortOpenYn()!=null? counsel.getShortOpenYn().name() : "Y";
    }

    public CounselDetailResponse(Counsel counsel) {
        this.id = counsel.getId();
        this.counselStateCode = counsel.getCounselState()!=null ? counsel.getCounselState().name() : "";
        this.counselStateName = counsel.getCounselState()!=null ? counsel.getCounselState().getName() : "";
        this.gubnName = counsel.getGubn()!=null? counsel.getGubn().getName() : "";
        this.txt = counsel.getTxt();
        this.reportCnt = counsel.getReportCnt();
        this.addedTagData = counsel.getInputTag();
        this.createDate = counsel.getCreatedAt()!=null? counsel.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) : "";
        this.accountId = counsel.getAccount().getId();
        this.isMine = null;
        this.interestCompanyName = counsel.getInterestCompany()!=null? counsel.getInterestCompany().name() : null;
        this.interestCompanyDesc = counsel.getInterestCompany()!=null? counsel.getInterestCompany().getDesc() : null;
        this.relatedZone = counsel.getRelatedZone();
        this.si = counsel.getRelatedZone();
        this.gugun = counsel.getRelatedZone();
        this.qnaItem = counsel.getQnaItem()!=null? counsel.getQnaItem().name() : "Q1";
        if(counsel.getImages()!=null){
            if(counsel.getImages().getImage1()!=null) images.add(counsel.getImages().getImage1());
            if(counsel.getImages().getImage2()!=null) images.add(counsel.getImages().getImage2());
            if(counsel.getImages().getImage3()!=null) images.add(counsel.getImages().getImage3());
            if(counsel.getImages().getImage4()!=null) images.add(counsel.getImages().getImage4());
            if(counsel.getImages().getImage5()!=null) images.add(counsel.getImages().getImage5());
        }

        this.opneYn = counsel.getOpenYn().name();
        this.shortOpenYn = counsel.getShortOpenYn()!=null? counsel.getShortOpenYn().name() : "Y";
    }
}
