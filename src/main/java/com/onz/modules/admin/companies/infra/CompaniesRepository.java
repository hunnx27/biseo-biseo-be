package com.onz.modules.admin.companies.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.companies.domain.Companies;
import com.onz.modules.admin.counsels.web.dto.CounselsDetailResponseDto;
import com.onz.modules.admin.member.livemember.infra.LiveMemberRepositoryExtension;
import com.onz.modules.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompaniesRepository extends JpaRepository<Companies, Long>, CompaniesRepositoryExtension {
}
