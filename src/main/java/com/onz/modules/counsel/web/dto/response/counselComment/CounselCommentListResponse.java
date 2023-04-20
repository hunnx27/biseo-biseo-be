package com.onz.modules.counsel.web.dto.response.counselComment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onz.common.util.TimeUtil;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.CounselComment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CounselCommentListResponse {

    private Long id;
    private String gubnName;
    private String txt;
    @JsonProperty(value="isMine")
    private boolean isMine;
    private String time;
    @JsonProperty(value="isCounselWriter")
    private boolean isCounselWriter;
    @JsonProperty(value="isAnswerWriter")
    private boolean isAnswerWriter;
    private String writerName;

    public CounselCommentListResponse(CounselComment comment, Account me) {
        this.id = comment.getId();
        this.gubnName = comment.getGubn()!=null? comment.getGubn().getName() : "";
        this.time = comment.getCreatedAt()!=null? TimeUtil.calculateTime(Date.from(comment.getCreatedAt().toInstant())) : "";
        this.isMine = (comment.getAccount().getId() == me.getId());
        this.txt = comment.getTxt();
        Long counselWriterId = comment.getCounsel().getParentCounsel().getAccount().getId();    // 질문자
        Long answerWriterId = comment.getCounsel().getAccount().getId();                        // 답변자
        Long commentWriterId = comment.getAccount().getId();                                    // 댓글작성자
        this.isCounselWriter = commentWriterId == counselWriterId;
        this.isAnswerWriter = commentWriterId == answerWriterId;
        this.writerName = isCounselWriter? "질문자" : isAnswerWriter?"답변자":"";
    }
}
