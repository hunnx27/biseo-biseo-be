package com.onz.modules.admin.counsels.infra;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.QAccount;
import com.onz.modules.admin.counsels.web.dto.*;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberDetailResponse;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberRequestDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberResponseDto;
import com.onz.modules.company.domain.Company;
import com.onz.modules.counsel.domain.QCounsel;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.counsel.domain.enums.QnaItem;
import com.onz.modules.review.domain.QCompanyReview;
import com.onz.modules.review.domain.QInterviewReview;
import com.onz.modules.review.domain.QYearAmtReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.onz.modules.company.domain.QCompany.company;
import static java.lang.String.valueOf;

public class CounselsRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CounselsRepositoryExtension {
    QCounsel counsel = QCounsel.counsel;

    private final JPAQueryFactory qf;
    private final EntityManager em;

    public CounselsRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Account.class);
        this.qf = qf;
        this.em = em;
    }

    private BooleanBuilder getWhere(CounselsRequestDto counselsRequestDto,
                                    QCounsel counsel) {

        BooleanBuilder where = new BooleanBuilder();
        where.and(counsel.qnaGubn.eq(QnaGubn.Q));
        if (counselsRequestDto.getInterestCompany() == null) {
            //gubun은 이넘으로 all을 받기 떄문에 처리 ㄴㄴ -> 해야할듯
        } else {
            where.and(counsel.interestCompany.eq(InterestCompany.valueOf(counselsRequestDto.getInterestCompany())));
        }
        if (counselsRequestDto.getCreateAdt() != null) {
            if (counselsRequestDto.getCreateBdt() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String ccc = String.format(account.createdAt.toString(), "yyyy-MM-dd");
                ZonedDateTime aaa = ZonedDateTime.of(LocalDateTime.parse(counselsRequestDto.getCreateAdt() + " 00:00:00", formatter), ZoneId.of("Asia/Seoul"));
                ZonedDateTime bbb = ZonedDateTime.of(LocalDateTime.parse(counselsRequestDto.getCreateBdt() + " 23:59:59", formatter), ZoneId.of("Asia/Seoul"));
                where.and(counsel.createdAt.between(
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", aaa),
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", bbb)
                ));
            }
        }
        if (counselsRequestDto.getCounselState() != null) {
            switch (counselsRequestDto.getCounselState()) {
                case "A":
                    where.and(counsel.counselState.eq(CounselState.A));
                    break;
                case "R":
                    where.and(counsel.counselState.eq(CounselState.R));
                    break;
                case "X":
                    where.and(counsel.qnaGubn.eq(QnaGubn.A).and(counsel.parentCounsel.id.eq(counsel.id)));
                    break;
                case "all":
                default:
                    break;
            }
        }
        if (counselsRequestDto.getTxt() != null) {
            where.and(counsel.txt.contains(counselsRequestDto.getTxt()));
        }
        return where;
    }
    private BooleanBuilder getWhere2(CounselsRequestDto counselsRequestDto,
                                    QCounsel counsel) {

        BooleanBuilder where = new BooleanBuilder();
        where.and(counsel.qnaGubn.eq(QnaGubn.A));
        if (counselsRequestDto.getInterestCompany() == null) {
            //gubun은 이넘으로 all을 받기 떄문에 처리 ㄴㄴ -> 해야할듯
        } else {
            where.and(counsel.interestCompany.eq(InterestCompany.valueOf(counselsRequestDto.getInterestCompany())));
        }
        if (counselsRequestDto.getCreateAdt() != null) {
            if (counselsRequestDto.getCreateBdt() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String ccc = String.format(account.createdAt.toString(), "yyyy-MM-dd");
                ZonedDateTime aaa = ZonedDateTime.of(LocalDateTime.parse(counselsRequestDto.getCreateAdt() + " 00:00:00", formatter), ZoneId.of("Asia/Seoul"));
                ZonedDateTime bbb = ZonedDateTime.of(LocalDateTime.parse(counselsRequestDto.getCreateBdt() + " 23:59:59", formatter), ZoneId.of("Asia/Seoul"));
                where.and(counsel.createdAt.between(
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", aaa),
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", bbb)
                ));
            }
        }
        if (counselsRequestDto.getCounselState() != null) {
            switch (counselsRequestDto.getCounselState()) {
                case "A":
                    where.and(counsel.counselState.eq(CounselState.A));
                    break;
                case "R":
                    where.and(counsel.counselState.eq(CounselState.R));
                    break;
                case "X":
                    where.and(counsel.qnaGubn.eq(QnaGubn.A).and(counsel.parentCounsel.id.eq(counsel.id)));
                    break;
                case "all":
                default:
                    break;
            }
        }
        if (counselsRequestDto.getTxt() != null) {
            where.and(counsel.txt.contains(counselsRequestDto.getTxt()));
        }
        return where;
    }
    private BooleanBuilder getWhere3(TagRequestDto tag,
                                    QCounsel counsel) {

        BooleanBuilder where = new BooleanBuilder();
        if (tag.getGubn() == null) {
            //gubun은 이넘으로 all을 받기 떄문에 처리 ㄴㄴ -> 해야할듯
        } else {
            where.and(counsel.gubn.eq(Gubn.valueOf(String.valueOf(tag.getGubn()))));
        }
        if (tag.getTag() != null) {
            where.and(counsel.inputTag.contains(tag.getTag()));
        }
        return where;
    }
    @Override
    public List<CounselsResponseDto> findcounselsItemSearchQ(CounselsRequestDto counselsRequestDto, Pageable pageable,String qnaItem) {
        BooleanBuilder where = this.getWhere(counselsRequestDto, counsel);
        where.and(counsel.qnaItem.eq(QnaItem.valueOf(qnaItem)));
        QCounsel counsel2 = new QCounsel("counsel2");
        JPQLQuery<CounselsResponseDto> result = from(counsel).select(
                        Projections.fields(CounselsResponseDto.class,
                                counsel.gubn,
                                counsel.qnaItem,
                                counsel.inputTag,
                                counsel.txt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(counsel2.count())
                                                .from(counsel2)
                                                .where(counsel2.parentCounsel.id.eq(counsel.id))
                                        , "qCount"),
                                counsel.counselState,
                                counsel.account.userId,
                                counsel.shortOpenYn,
                                counsel.createdAt,
                                counsel.isDelete
                        )
                )
                .where(where);
        JPQLQuery<CounselsResponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<CounselsResponseDto> findLiveMemberResults = query.fetchResults();
        List<CounselsResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();
        return findLiveMemberListResults;
    }
    @Override
    public List<CounselsResponseDto> findcounselsSearchQ(CounselsRequestDto counselsRequestDto, Pageable pageable) {
        BooleanBuilder where = this.getWhere(counselsRequestDto, counsel);
        QCounsel counsel2 = new QCounsel("counsel2");
        JPQLQuery<CounselsResponseDto> result = from(counsel).select(
                        Projections.fields(CounselsResponseDto.class,
                                counsel.gubn,
                                counsel.qnaItem,
                                counsel.inputTag,
                                counsel.txt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(counsel2.count())
                                                .from(counsel2)
                                                .where(counsel2.parentCounsel.id.eq(counsel.id))
                                        , "qCount"),
                                counsel.counselState,
                                counsel.account.userId,
                                counsel.shortOpenYn,
                                counsel.createdAt,
                                counsel.isDelete
                        )
                )
                .where(where);
        JPQLQuery<CounselsResponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<CounselsResponseDto> findLiveMemberResults = query.fetchResults();
        List<CounselsResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();
        return findLiveMemberListResults;
    }

    public CountEvent findcount(CounselsRequestDto counselsRequestDto) {
        // Q클래스 정의
        QCounsel counsel = QCounsel.counsel;
        BooleanBuilder where = this.getWhere2(counselsRequestDto, counsel);
        // 쿼리 생성(집계)
        return qf.select(
                Projections.constructor(CountEvent.class,
                      counsel.qnaItem.count().as("all"),
                      new CaseBuilder()
                              .when(counsel.qnaItem.in(QnaItem.QS01))
                              .then(1)
                              .otherwise(0).sum().as("QS01"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS02))
                                .then(1)
                                .otherwise(0).sum().as("QS02"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS03))
                                .then(1)
                                .otherwise(0).sum().as("QS03"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS04))
                                .then(1)
                                .otherwise(0).sum().as("QS04"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS05))
                                .then(1)
                                .otherwise(0).sum().as("QS05"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS06))
                                .then(1)
                                .otherwise(0).sum().as("QS06"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI01))
                                .then(1)
                                .otherwise(0).sum().as("QI01"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI02))
                                .then(1)
                                .otherwise(0).sum().as("QI02"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI03))
                                .then(1)
                                .otherwise(0).sum().as("QI03"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI04))
                                .then(1)
                                .otherwise(0).sum().as("QI04"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI05))
                                .then(1)
                                .otherwise(0).sum().as("QI05"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI06))
                                .then(1)
                                .otherwise(0).sum().as("QI06"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI07))
                                .then(1)
                                .otherwise(0).sum().as("QI07")
                )).from(counsel).where(where).fetchOne();
    }
    public CountEvent findcount2(TagRequestDto tagRequestDto) {
        // Q클래스 정의
        QCounsel counsel = QCounsel.counsel;
        BooleanBuilder where = this.getWhere3(tagRequestDto, counsel);
        // 쿼리 생성(집계)
        return qf.select(
                Projections.constructor(CountEvent.class,
                        counsel.qnaItem.count().as("all"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS01))
                                .then(1)
                                .otherwise(0).sum().as("QS01"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS02))
                                .then(1)
                                .otherwise(0).sum().as("QS02"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS03))
                                .then(1)
                                .otherwise(0).sum().as("QS03"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS04))
                                .then(1)
                                .otherwise(0).sum().as("QS04"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS05))
                                .then(1)
                                .otherwise(0).sum().as("QS05"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QS06))
                                .then(1)
                                .otherwise(0).sum().as("QS06"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI01))
                                .then(1)
                                .otherwise(0).sum().as("QI01"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI02))
                                .then(1)
                                .otherwise(0).sum().as("QI02"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI03))
                                .then(1)
                                .otherwise(0).sum().as("QI03"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI04))
                                .then(1)
                                .otherwise(0).sum().as("QI04"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI05))
                                .then(1)
                                .otherwise(0).sum().as("QI05"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI06))
                                .then(1)
                                .otherwise(0).sum().as("QI06"),
                        new CaseBuilder()
                                .when(counsel.qnaItem.in(QnaItem.QI07))
                                .then(1)
                                .otherwise(0).sum().as("QI07")
                )).from(counsel).where(where).fetchOne();
    }
    public List<CounselAnswerListResponseDto> findByAnswer(Long answerId){
        QCounsel counsel = QCounsel.counsel;
        // 쿼리 생성(집계)

//                counsel.parentCounsel.counselState).from(counsel).where(counsel.parentCounsel.id.eq(id)).fetchOne();
        JPQLQuery<CounselAnswerListResponseDto> result = from(counsel).select(
                Projections.fields(CounselAnswerListResponseDto.class,
                        counsel.parentCounsel.txt,
                        counsel.parentCounsel.images,
                        counsel.parentCounsel.account.userId,
                        counsel.parentCounsel.createdAt,
                        counsel.parentCounsel.counselState
                )
        ).where(counsel.parentCounsel.id.eq(answerId));
        QueryResults<CounselAnswerListResponseDto> findLiveMemberResults = result.fetchResults();
        List<CounselAnswerListResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();
        return findLiveMemberListResults;
    }

    @Override
    public List<CounselsAresponseDto> findcounselsSearchA(CounselsRequestDto counselsRequestDto, Pageable pageable) {
        BooleanBuilder where = this.getWhere2(counselsRequestDto, counsel);
        QCounsel counsel2 = new QCounsel("counsel2");
        JPQLQuery<CounselsAresponseDto> result = from(counsel).select(
                        Projections.fields(CounselsAresponseDto.class,
                                counsel.gubn,
                                counsel.qnaItem,
                                counsel.txt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(counsel.parentCounsel.txt)
                                                .from(counsel2)
                                                .where(counsel2.id.eq(counsel.id))
                                        , "txtA"),
                                counsel.counselState,
                                counsel.account.userId,
                                counsel.shortOpenYn,
                                counsel.createdAt,
                                counsel.isDelete
                        )
                )
                .where(where);
        JPQLQuery<CounselsAresponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<CounselsAresponseDto> findLiveMemberResults = query.fetchResults();
        List<CounselsAresponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();
        return findLiveMemberListResults;
    }
    @Override
    public List<CounselsAresponseDto> findcounselsItemSearchA(CounselsRequestDto counselsRequestDto, Pageable pageable,String qnaItem) {
        BooleanBuilder where = this.getWhere2(counselsRequestDto, counsel);
        QCounsel counsel2 = new QCounsel("counsel2");
        where.and(counsel.qnaItem.eq(QnaItem.valueOf(qnaItem)));
        JPQLQuery<CounselsAresponseDto> result = from(counsel).select(
                        Projections.fields(CounselsAresponseDto.class,
                                counsel.gubn,
                                counsel.qnaItem,
                                counsel.txt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(counsel.parentCounsel.txt)
                                                .from(counsel2)
                                                .where(counsel2.id.eq(counsel.id))
                                        , "txtA"),
                                counsel.counselState,
                                counsel.account.userId,
                                counsel.shortOpenYn,
                                counsel.createdAt,
                                counsel.isDelete
                        )
                )
                .where(where);
        JPQLQuery<CounselsAresponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<CounselsAresponseDto> findLiveMemberResults = query.fetchResults();
        List<CounselsAresponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();
        return findLiveMemberListResults;
    }
    @Override
    public List<TagResponseDto> findTag(TagRequestDto tagRequestDto, Pageable pageable) {
        BooleanBuilder where = this.getWhere3(tagRequestDto, counsel);
        JPQLQuery<TagResponseDto> result = from(counsel).select(
                        Projections.fields(TagResponseDto.class,
                                counsel.gubn,
                                counsel.inputTag,
                                counsel.inputTag.count().as("count")
                        )
                ).groupBy(counsel.inputTag)
                .where(where);
        JPQLQuery<TagResponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<TagResponseDto> findLiveMemberResults = query.fetchResults();
        List<TagResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();
        return findLiveMemberListResults;
    }

    @Override
    public List<CounselsResponseDto> findTagCounselQ(TagRequestDto tagRequestDto,Pageable pageable) {
        QCounsel counsel2 = new QCounsel("counsel2");
        BooleanBuilder where = this.getWhere3(tagRequestDto,counsel);
        JPQLQuery<CounselsResponseDto> result = from(counsel).select(
                        Projections.fields(CounselsResponseDto.class,
                                counsel.gubn,
                                counsel.qnaItem,
                                counsel.inputTag,
                                counsel.txt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(counsel2.count())
                                                .from(counsel2)
                                                .where(counsel2.parentCounsel.id.eq(counsel.id))
                                        , "qCount"),
                                counsel.counselState,
                                counsel.account.userId,
                                counsel.shortOpenYn,
                                counsel.createdAt,
                                counsel.isDelete
                        )
                )
                .where(where);
        JPQLQuery<CounselsResponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<CounselsResponseDto> findLiveMemberResults = query.fetchResults();
        List<CounselsResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();
        return findLiveMemberListResults;
    }
}
