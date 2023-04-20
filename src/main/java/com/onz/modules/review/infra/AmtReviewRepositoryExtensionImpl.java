package com.onz.modules.review.infra;

import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.review.domain.QYearAmtReview;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.company.web.dto.reponse.YearAmtListResponseDto;
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

public class AmtReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements AmtReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public AmtReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(YearAmtReview.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<YearAmtListResponseDto> ListAmt(FindEstaRequestDto findEstaRequestDto, Pageable pageable) {
        QYearAmtReview qYearAmtReview = QYearAmtReview.yearAmtReview;
        BooleanBuilder where = new BooleanBuilder();
        String zoneCode = findEstaRequestDto.getSido() + findEstaRequestDto.getGungu();
        where.and(qYearAmtReview.state.eq(State.A));
        if (qYearAmtReview.company.zonecode != null) {
            if (findEstaRequestDto.getSido() != null) {
                //sido가 널이 아닐떄
                if (findEstaRequestDto.getSido() == null) {
                    //모두검색
                }
                if (findEstaRequestDto.getGungu() == null) {//Sido값은들어옴 ,만약 군구가 널이라면
                    where.and(qYearAmtReview.company.zonecode.startsWith(findEstaRequestDto.getSido()));//시도로검색
                } else {
                    //만약 null이 아니라면
                    where.and((qYearAmtReview.company.zonecode.eq(zoneCode)));
                }
            }
        }
        if (qYearAmtReview.company.establishmentType != null) {
            if(findEstaRequestDto.getEstablishmentType()!=null) {
                if (findEstaRequestDto.getEstablishmentType().name().equals("all")) {
                    //all
                } else {
                    where.and(qYearAmtReview.company.establishmentType.eq(findEstaRequestDto.getEstablishmentType()));
                }
            }
        }
        if (qYearAmtReview.company.interestCompany != null) {
            if(findEstaRequestDto.getInterestCompany()!=null) {
                if (findEstaRequestDto.getInterestCompany().name().equals("all")) {
                } else {
                    where.and(qYearAmtReview.company.interestCompany.eq(findEstaRequestDto.getInterestCompany()));
                }
            }
        }

        JPQLQuery<YearAmtReview> result = from(qYearAmtReview).where(where);
        JPQLQuery<YearAmtReview> query = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, result);
        QueryResults<YearAmtReview> fetchResults = query.fetchResults();

        List<YearAmtReview> list = fetchResults.getResults();
        return list.stream().map(com -> new YearAmtListResponseDto(com)).collect(Collectors.toList());
    }

}

//    JPQLQuery<Company> query = getQuerydsl().applyPagination(pageable, result);
//