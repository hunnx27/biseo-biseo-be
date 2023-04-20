package com.onz.modules.review.infra;

import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.review.domain.InterviewReview;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import com.onz.modules.company.domain.Company;
import com.onz.modules.review.domain.QInterviewReview;
import com.onz.modules.company.web.dto.reponse.InterviewListResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class InterviewReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements InterviewReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public InterviewReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Company.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<InterviewListResponseDto> ListInterview(FindEstaRequestDto findEstaRequestDto, Pageable pageable) {
        QInterviewReview qInterviewReview = QInterviewReview.interviewReview;
        BooleanBuilder where = new BooleanBuilder();
        where.and(qInterviewReview.state.eq(State.A));
        String zoneCode = findEstaRequestDto.getSido() + findEstaRequestDto.getGungu();
        if (qInterviewReview.company.zonecode != null) {
            if (findEstaRequestDto.getSido() != null) {
                //sido가 널이 아닐떄
                if (findEstaRequestDto.getSido() == null) {
                    //모두검색
                }
                if (findEstaRequestDto.getGungu() == null) {//Sido값은들어옴 ,만약 군구가 널이라면
                    where.and(qInterviewReview.company.zonecode.startsWith(findEstaRequestDto.getSido()));//시도로검색
                } else {
                    //만약 null이 아니라면
                    where.and((qInterviewReview.company.zonecode.eq(zoneCode)));
                }
            }
        }
        if (qInterviewReview.company.establishmentType != null) {
            if(findEstaRequestDto.getEstablishmentType()!=null) {
                if (findEstaRequestDto.getEstablishmentType().name().equals("all")) {
                    //all
                } else {
                    where.and(qInterviewReview.company.establishmentType.eq(findEstaRequestDto.getEstablishmentType()));
                }
            }
        }
        if (qInterviewReview.company.interestCompany != null) {
            if(findEstaRequestDto.getInterestCompany()!=null) {
                if (findEstaRequestDto.getInterestCompany().name().equals("all")) {
                } else {
                    where.and(qInterviewReview.company.interestCompany.eq(findEstaRequestDto.getInterestCompany()));
                }
            }
        }

        List<InterviewReview> list = jpaQueryFactory
                .selectFrom(qInterviewReview)
                .fetch();
        return list.stream().map(com -> new InterviewListResponseDto(com)).collect(Collectors.toList());
    }
}