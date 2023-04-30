package com.biseo.modules.account.web.dto;

import com.biseo.modules.account.web.dto.request.AccountUpdateRequest;
import com.biseo.modules.account.domain.Account;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AccountConverter {

    void updateAccountFromDto(AccountUpdateRequest accountUpdateRequest,
        @MappingTarget Account account);

}
