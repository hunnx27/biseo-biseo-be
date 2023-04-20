package com.onz.modules.review.infra;

import com.onz.common.web.dto.response.enums.State;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.*;
import com.onz.modules.company.web.dto.reponse.CompanyReviewListResponseDto;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompanyReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements CompanyReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CompanyReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(CompanyReview.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<CompanyReviewListResponseDto> listCompanyReview(FindEstaRequestDto findEstaRequestDto, Pageable pageable) {
        QCompanyReview qCompanyReview = QCompanyReview.companyReview;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qCompanyReview.state.eq(State.A));
        String zoneCode = findEstaRequestDto.getSido() + findEstaRequestDto.getGungu();
        if (qCompanyReview.company.zonecode != null) {
            if (findEstaRequestDto.getSido() != null) {
                //sido가 널이 아닐떄
                if (findEstaRequestDto.getSido() == null) {
                    //모두검색
                }
                if (findEstaRequestDto.getGungu() == null) {//Sido값은들어옴 ,만약 군구가 널이라면
                    where.and(qCompanyReview.company.zonecode.startsWith(findEstaRequestDto.getSido()));//시도로검색
                } else {
                    //만약 null이 아니라면
                    where.and((qCompanyReview.company.zonecode.eq(zoneCode)));
                }
            }
        }
        if (qCompanyReview.company.establishmentType != null) {
            if(findEstaRequestDto.getEstablishmentType()!=null) {
                if (findEstaRequestDto.getEstablishmentType().name().equals("all")) {
                    //all
                } else {
                    where.and(qCompanyReview.company.establishmentType.eq(findEstaRequestDto.getEstablishmentType()));
                }
            }
        }
        if (qCompanyReview.company.interestCompany != null) {
            if(findEstaRequestDto.getInterestCompany()!=null) {
                if (findEstaRequestDto.getInterestCompany().name().equals("all")) {
                } else {
                    where.and(qCompanyReview.company.interestCompany.eq(findEstaRequestDto.getInterestCompany()));
                }
            }
        }

        JPQLQuery<CompanyReview> result = from(qCompanyReview).where(where);
        JPQLQuery<CompanyReview> query = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, result);
        QueryResults<CompanyReview> fetchResults = query.fetchResults();

        List<CompanyReview> list = fetchResults.getResults();
        return list.stream().map(com -> new CompanyReviewListResponseDto(com)).collect(Collectors.toList());
//        List<CompanyReview> list = jpaQueryFactory
//                .selectFrom(qCompanyReview)
//                .fetch();
//        return list.stream().map(com -> new CompanyReviewListResponseDto(com)).collect(Collectors.toList());
    }

    @Override
    public List<CompanyReview> listCompanyReviewByCompanyId(Long companyId) {
        QCompanyReview qCompanyReview = QCompanyReview.companyReview;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qCompanyReview.isDelete.eq(YN.N));
        where.and(qCompanyReview.company.id.eq(companyId));


        List<CompanyReview> list = jpaQueryFactory
                .selectFrom(qCompanyReview)
                .where(where)
                .fetch();
//        return list.stream().map(com -> new CompanyReviewListResponseDto(com)).collect(Collectors.toList());
        return list;
    }
}