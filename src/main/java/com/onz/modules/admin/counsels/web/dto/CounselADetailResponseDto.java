package com.onz.modules.admin.counsels.web.dto;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.embed.Images;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.counsel.domain.enums.QnaItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class CounselADetailResponseDto {
    private String txtA;
    private String userIdA;
    private CounselState counselStateA;
    private ZonedDateTime createAdt;
    private String AComment;
    //---
    //업데이트용 openYn필요
    //상담
    private Gubn gubn;
    private String txt;
    private ZonedDateTime createDt;
    private YN openYn;
    private String userIdQ;
    private YN shortOpenYn;

    public CounselADetailResponseDto(Optional<Counsel> counsel) {
        if(counsel.get().getQnaGubn()== QnaGubn.A) {
            this.txtA = counsel.get().getParentCounsel().getTxt();
            this.userIdA = counsel.get().getParentCounsel().getAccount().getUserId();
            this.createAdt = counsel.get().getParentCounsel().getCreatedAt();
            this.counselStateA = counsel.get().getParentCounsel().getCounselState();
            this.AComment = counsel.get().getParentCounsel().getCommentTxt();
            this.gubn = counsel.get().getGubn();
            this.txt = counsel.get().getTxt();
            this.createDt = counsel.get().getCreatedAt();
            this.openYn = counsel.get().getOpenYn();
            this.userIdQ = counsel.get().getAccount().getUserId();
            this.shortOpenYn = counsel.get().getShortOpenYn();
        }
    }
}
