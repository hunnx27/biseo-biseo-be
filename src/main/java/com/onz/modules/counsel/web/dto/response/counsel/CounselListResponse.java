package com.onz.modules.counsel.web.dto.response.counsel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.Counsel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class CounselListResponse {

    private Long id;
    private String counselStateCode;
    private String counselStateName;
    private String gubnName;
    private String inputTag;
    private String txt;
    private String createDate;
    private long reportCnt;
    private Long accountId;
    @JsonProperty(value="isMine")
    private boolean isMine;

    public CounselListResponse(Counsel counsel, Account me) {
        this.id = counsel.getId();
        this.counselStateCode = counsel.getCounselState()!=null ? counsel.getCounselState().name() : "";
        this.counselStateName = counsel.getCounselState()!=null ? counsel.getCounselState().getName() : "";
        this.gubnName = counsel.getGubn()!=null? counsel.getGubn().getName() : "";
        this.inputTag = counsel.getInputTag();
        this.txt = counsel.getTxt();
        this.reportCnt = counsel.getReportCnt();
        this.createDate = counsel.getCreatedAt()!=null? counsel.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) : "";
        this.accountId = counsel.getAccount().getId();
        this.isMine = (counsel.getAccount().getId().equals(me.getId()));
    }
    public CounselListResponse(Counsel counsel) {
        this.id = counsel.getId();
        this.counselStateCode = counsel.getCounselState()!=null ? counsel.getCounselState().name() : "";
        this.counselStateName = counsel.getCounselState()!=null ? counsel.getCounselState().getName() : "";
        this.gubnName = counsel.getGubn()!=null? counsel.getGubn().getName() : "";
        this.inputTag = counsel.getInputTag();
        this.txt = counsel.getTxt();
        this.reportCnt = counsel.getReportCnt();
        this.createDate = counsel.getCreatedAt()!=null? counsel.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")) : "";
        this.accountId = counsel.getAccount().getId();
    }
}
