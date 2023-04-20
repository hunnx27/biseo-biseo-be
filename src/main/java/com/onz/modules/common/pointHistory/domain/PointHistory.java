package com.onz.modules.common.pointHistory.domain;

import com.onz.common.domain.BaseEntity;
import com.onz.modules.account.domain.Account;
import com.onz.modules.common.pointHistory.domain.embed.PointInfo;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PointHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Embedded
    private PointInfo pointInfo;

    @Builder
    public PointHistory(Account account, PointTable pointTable) {
        this.account = account;
        if(account.getPoint()==null&&pointTable.equals(PointTable.WELCOME_JOIN)){
            account.setPoint(0);
        }
        PointInfo pointinfo = new PointInfo(pointTable, account);
        this.pointInfo = pointinfo;
    }

    //    private String code;
//    private String name;
//    @Embedded
//    private Address address;
//    private int totalMemberCount;
//    private int currentMemberCount;
//
//    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
//    @OrderBy("createdAt")
//    private List<Account> accounts = new ArrayList<>();
//
//    private YN isOperation;
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "account_id")
//    private Account director;
//
//    private ZonedDateTime openedAt;
//    private YN isOpen;
//    private int fixedPeople;
//    private int currentPeople;
//
//    public void addAccount(PointHistory pointHistory) {
//        this.getAccounts().add(pointHistory);
//    }
//
//    public void addDirector(Account account){
//        this.director = account;
//    }
}
