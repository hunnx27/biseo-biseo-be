package com.onz.modules.admin.member.livemember.infra;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.modules.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiveMemberRepository extends JpaRepository<Account, Long> ,LiveMemberRepositoryExtension{
    List<Account> findAllByGubn(Gubn gubn);

}
