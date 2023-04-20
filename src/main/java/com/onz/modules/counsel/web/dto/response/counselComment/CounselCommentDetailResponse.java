package com.onz.modules.counsel.web.dto.response.counselComment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.CounselComment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
public class CounselCommentDetailResponse {

    private Long id;
    private String txt;
    private Long parentCounselId;

    public CounselCommentDetailResponse(CounselComment counselComment) {
        this.id = counselComment.getId();
        this.txt = counselComment.getTxt();
        this.parentCounselId = counselComment.getCounsel().getId();
    }
}
