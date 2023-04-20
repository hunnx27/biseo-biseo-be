package com.onz.modules.account.domain;

import com.onz.common.domain.BaseEntity;

import java.time.ZonedDateTime;
import javax.persistence.*;

import com.onz.modules.admin.auth.web.dto.AdminCreateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    private String team;
    private String name;
    private ZonedDateTime lastLogin;


    public Admin(AdminCreateRequestDto adminCreateRequestDto, Account account) {
        this.account = account;
        this.team = adminCreateRequestDto.getTeam();
        this.name = adminCreateRequestDto.getName();
        account.setGubn(null);
    }
}
