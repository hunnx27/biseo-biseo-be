package com.onz.modules.company.infra;


import com.onz.modules.company.web.dto.reponse.CompanyDetailResponse;
import com.onz.modules.company.web.dto.reponse.CompanySearchResponse;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.onz.modules.company.domain.Company;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyRepositoryExtension {
    List<CompanyDetailResponse> convertlist(Company company);
    PageImpl<Company> list(CompanySearchRequest companySearchRequest);
    List<CompanySearchResponse> search(CompanySearchRequest companySearchRequest, Pageable pageable);
    Long searchTotSize(CompanySearchRequest companySearchRequest);

    //        QAccount account = QAccount.account;
    //
    //        // 조건생성
    //        BooleanBuilder where = new BooleanBuilder();
    //        where.and(account.userId.eq(encodedUserId));
    //
    //        // 쿼리생성
    //        Account findedAccount = qf.selectFrom(account)
    //                .where(where)
    //                .fetchOne();
    //
    //        return Optional.ofNullable(findedAccount);
    //    }

    //        QAccount account = QAccount.account;
    //
    //        // 조건생성
    //        BooleanBuilder where = new BooleanBuilder();
    //        where.and(account.userId.eq(encodedUserId));
    //
    //        // 쿼리생성
    //        Account findedAccount = qf.selectFrom(account)
    //                .where(where)
    //                .fetchOne();
    //
    //        return Optional.ofNullable(findedAccount);
    //    }

    void update(Long id,CompanyUpdateRequest updateRequest);
    List<Company> getListBaseCompany(Pageable pageable);
}
