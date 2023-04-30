package com.biseo.modules.company.infra;


import com.biseo.modules.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long>,
        CompanyRepositoryExtension {
}
