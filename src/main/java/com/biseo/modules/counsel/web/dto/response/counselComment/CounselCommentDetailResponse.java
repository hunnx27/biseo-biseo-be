package com.biseo.modules.counsel.web.dto.response.counselComment;

import com.biseo.modules.counsel.domain.CounselComment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
