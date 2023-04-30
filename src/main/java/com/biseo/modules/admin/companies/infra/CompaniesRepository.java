package com.biseo.modules.admin.companies.infra;

import com.biseo.modules.admin.companies.domain.Companies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompaniesRepository extends JpaRepository<Companies, Long>, CompaniesRepositoryExtension {
}
