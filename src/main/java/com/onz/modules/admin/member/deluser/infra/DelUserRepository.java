package com.onz.modules.admin.member.deluser.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.member.livemember.infra.LiveMemberRepositoryExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelUserRepository  extends JpaRepository<Account, Long>, DelUserRepositoryExtension {
}
