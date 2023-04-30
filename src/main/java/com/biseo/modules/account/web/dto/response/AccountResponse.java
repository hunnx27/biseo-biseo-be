package com.biseo.modules.account.web.dto.response;

import com.biseo.common.web.dto.response.enums.Role;
import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.account.domain.Account;
import com.biseo.modules.account.domain.embed.Myinfo;
import com.biseo.modules.account.domain.enums.AuthProvider;
import com.biseo.common.web.dto.response.enums.Gubn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponse {

    private Long id;
    private String userId;
    private Role role;
    private AuthProvider snsType;
    private Integer point;
    private Gubn gubn;
    private Myinfo myinfo;
    private YN isDelete;

    public AccountResponse(Account account) {
        this.id = account.getId();
        this.userId = account.getUserId();
        this.role = account.getRole();
        this.snsType = account.getSnsType();
        this.point = account.getPoint();
        this.gubn = account.getGubn();
        this.myinfo = account.getMyinfo();
        this.isDelete = account.getIsDelete();
    }
}
