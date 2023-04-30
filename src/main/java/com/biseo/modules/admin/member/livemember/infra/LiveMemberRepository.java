package com.biseo.modules.admin.member.livemember.infra;

import com.biseo.common.web.dto.response.enums.Gubn;
import com.biseo.modules.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiveMemberRepository extends JpaRepository<Account, Long> ,LiveMemberRepositoryExtension{
    List<Account> findAllByGubn(Gubn gubn);

}
