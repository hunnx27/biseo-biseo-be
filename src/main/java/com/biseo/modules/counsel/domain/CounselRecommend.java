package com.biseo.modules.counsel.domain;

import com.biseo.common.domain.BaseEntity;
import com.biseo.modules.account.domain.Account;
import com.biseo.modules.counsel.domain.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class CounselRecommend extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="account_id")
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name="counsel_id")
    private Counsel counsel;
    @Enumerated(EnumType.STRING)
    private RecommendGubn recommendGubn;

    public CounselRecommend() {
    }

    public CounselRecommend(Account account, Counsel counsel, RecommendGubn recommendGubn) {
        this.account = account;
        this.counsel = counsel;
        this.recommendGubn = recommendGubn;
    }
}
