package com.onz.modules.company.infra;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.domain.QCompany;
import com.onz.modules.company.web.dto.reponse.CompanyDetailResponse;
import com.onz.modules.company.web.dto.reponse.CompanySearchResponse;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

import static com.onz.modules.company.domain.QCompany.company;

public class CompanyRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CompanyRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;
    private AddressRepository addressRepository;

    public CompanyRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Company.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanBuilder searchWhere(CompanySearchRequest companySearchRequest){
        BooleanBuilder builder = new BooleanBuilder();

        if (companySearchRequest.getName() != null) {
            builder.and(company.officeName.contains(companySearchRequest.getName()));
        }
        if (companySearchRequest.getInterestCompany() != null){
            builder.and(company.interestCompany.eq(companySearchRequest.getInterestCompany()));
        }
        if (companySearchRequest.getCode() !=null) {
            if (companySearchRequest.getCode().length()<3) {
//                String first_request = companySearchRequest.getCode();
//                builder.and(company.zonecode.substring(0,2).eq(first_request));
                String first_request = companySearchRequest.getCode().substring(0,2);//시
                builder.and(company.zonecode.startsWith(first_request));
            }else{
                builder.and(company.zonecode.eq(companySearchRequest.getCode()));
            }
        }
        return builder;
    }

    @Override
    public List<CompanyDetailResponse> convertlist(Company company) {
        QCompany qc=QCompany.company;
        List<Company> list = jpaQueryFactory
                //select(
//        Projections.constructor(CompanyDetailResponse.class,
//                qc.id,qc.interestCompany,qc.establishmentType,qc.officeName,qc.juso,qc.run,
//                qc.director,qc.openDt,qc.useYn,qc.evaluateYn,qc.fill,qc.totPeople,qc.currPeople ,
//                qc.agePeoples,qc.charItems,qc.perItems,qc.evalItems,qc.zonecode,qc.zipcode,qc.phoneNum,
//                qc.faxNum,qc.homepage,qc.syncCode)
                // )
                .selectFrom(qc)
                .where(qc.id.eq(company.getId()))
                .fetch();

        return list.stream().map(com -> new CompanyDetailResponse(com)).collect(Collectors.toList());
    }

    @Override
    public PageImpl<Company> list(CompanySearchRequest searchRequest) {
        QCompany CompanySearchResponse = QCompany.company;

        final Pageable pageable = searchRequest.getPageable();

        BooleanBuilder where = new BooleanBuilder();
        where.and(company.isDelete.eq(YN.N));

        JPQLQuery<Company> result = from(company)
            .where(where);

        JPQLQuery<Company> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Company> fetchResults = query.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    @Override
    public List<CompanySearchResponse> search(CompanySearchRequest companySearchRequest, Pageable pageable) {
        BooleanBuilder builder = searchWhere(companySearchRequest);

//        jpaQueryFactory.selectFrom(company).where(builder).select(Projections.constructor(CompanySearchResponse.class)).fetch();
         JPQLQuery<CompanySearchResponse> query = jpaQueryFactory
                .selectFrom(company)
                .where(builder)
                .select(Projections.constructor(CompanySearchResponse.class, company.id, company.establishmentType, company.officeName, company.zonecode));
        List<CompanySearchResponse> list = getQuerydsl().applyPagination(pageable, query).fetch();
        return list;
    }

    public Long searchTotSize(CompanySearchRequest companySearchRequest){
        BooleanBuilder builder = searchWhere(companySearchRequest);
        Long result = jpaQueryFactory
                .select(company.count())
                .from(company)
                .where(builder)
                .fetchOne();
        return result;
    }

    public List<Company> getListBaseCompany(Pageable pageable){
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(company.isDelete.eq(YN.N));
        builder.and(company.useYn.eq(YN.Y));

        JPQLQuery<Company> query = jpaQueryFactory
                .selectFrom(company)
                .where(builder);

        List<Company> result = getQuerydsl().applyPagination(pageable, query).fetch();
        return result;
    }


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

    @Override
    public void update(Long id,CompanyUpdateRequest updateRequest) {
        QCompany company = QCompany.company;
        update(company)
            .set(company.officeName, updateRequest.getName())
            .where(company.id.eq(id))
            .execute();
    }
}
