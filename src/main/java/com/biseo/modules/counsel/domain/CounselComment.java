package com.biseo.modules.counsel.domain;

import com.biseo.common.domain.BaseEntity;
import com.biseo.common.web.dto.response.enums.Gubn;
import com.biseo.common.web.dto.response.enums.GubnConverter;
import com.biseo.modules.account.domain.Account;
import com.biseo.modules.counsel.web.dto.request.counselComment.CounselCommentCreateRequest;
import com.biseo.modules.counsel.web.dto.request.counselComment.CounselCommentUpdateRequest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CounselComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="answer_counsel_id")
    private Counsel counsel;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="account_id")
    private Account account;
    @Convert(converter = GubnConverter.class)
    private Gubn gubn;

    private String txt;


    public CounselComment() {
    }

    public CounselComment(CounselCommentCreateRequest req, Account account) {
        this.counsel = req.getParentCounsel();
        this.account = account;
        this.gubn = account.getGubn();
        this.txt = req.getTxt();
    }

    public void updateCounselComment(CounselCommentUpdateRequest req, Account account){
        this.gubn = account.getGubn();
        this.txt = req.getTxt();
    }
}
