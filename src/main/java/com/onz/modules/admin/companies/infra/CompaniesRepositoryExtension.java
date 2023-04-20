package com.onz.modules.admin.companies.infra;

import com.onz.modules.admin.companies.domain.Companies;
import com.onz.modules.admin.companies.web.dto.*;
import com.onz.modules.auth.web.dto.UserPrincipal;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompaniesRepositoryExtension {

    List<CompaniesResponseDto> findByCompanies(CompaniesRequestDto companiesRequestDto, Pageable pageable);
    CompaniesDetailResponseDto findByCompaniesDetail(Long id);
    CompaniesDetailReviewDto findByCompaniesDetailReview(Long id);
    CompaniesDetailJipyoDto findByCompaniesDtailJipyo(Long id);

    List<CompaniesFixResponseDto> findByCompaniesFixList(CompaniesFixRequestDto companiesFixRequestDto, Pageable pageable);
    CompaniesFixDetailResponseDto findByCompaniesFixDetail(Long id);
}
