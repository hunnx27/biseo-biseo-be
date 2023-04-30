package com.biseo.modules.admin.member.deluser.infra;

import com.biseo.modules.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelUserRepository  extends JpaRepository<Account, Long>, DelUserRepositoryExtension {
}
