package com.onz.modules.counsel.infra.counsel;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.QCounsel;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.counsel.domain.enums.QnaItem;
import com.onz.modules.counsel.web.dto.request.counsel.CounselSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CounselRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CounselRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CounselRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Counsel.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Counsel> findAnswerList(Long parentCounselId,Pageable pageable){
        QCounsel counsel = QCounsel.counsel;
        BooleanBuilder where = new BooleanBuilder();
        where.and(counsel.qnaGubn.eq(QnaGubn.A));
        where.and(counsel.parentCounsel.id.eq(parentCounselId));
        JPQLQuery<Counsel> result = from(counsel).where(where);
        JPQLQuery<Counsel> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Counsel> fetchResults = query.fetchResults();
        return fetchResults.getResults();
    }

    @Override
    public List<Counsel> findCounselList(CounselSearchRequest counselSearchRequest, Pageable pageable) {
        QCounsel counsel = QCounsel.counsel;
        BooleanBuilder where = new BooleanBuilder();

        if(counselSearchRequest.getGubn() != null){
            where.and(counsel.gubn.eq(counselSearchRequest.getGubn()));
        }
        if(counselSearchRequest.getCounselSearchType() !=null){
            switch(counselSearchRequest.getCounselSearchType()){
                case QNA_ITEM:
                    List<String> list = Arrays.stream(QnaItem.values()).map(qnaItem -> qnaItem.name()).collect(Collectors.toList());
                    if(list.contains(counselSearchRequest.getKeyword())) {
                        where.and(counsel.qnaItem.eq(QnaItem.valueOf(counselSearchRequest.getKeyword())));
                    }
                    break;
                case HASHTAG:
                    if(counselSearchRequest.getKeyword()!=null && !"".equals(counselSearchRequest.getKeyword())){
                        where.and(counsel.inputTag.contains("#".concat(counselSearchRequest.getKeyword())));
                    }
                    break;
            }
        }
        where.and(counsel.qnaGubn.eq(QnaGubn.Q));
        where.and(counsel.openYn.eq(YN.Y));
        where.and(counsel.isDelete.eq(YN.N));
        where.and(counsel.shortOpenYn.eq(YN.N).or(counsel.createdAt.goe(ZonedDateTime.now().minusDays(1))));
        JPQLQuery<Counsel> result = from(counsel).where(where);
        JPQLQuery<Counsel> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Counsel> fetchResults = query.fetchResults();
        return fetchResults.getResults();
    }

    @Override
    public List<Counsel> findMyqCounselList(Account account, Pageable pageable,String option) {
        QCounsel counsel = QCounsel.counsel;
        QCounsel childCounsel = new QCounsel("childCounsel");
        BooleanBuilder where = new BooleanBuilder();
        where.and(counsel.account.id.eq(account.getId()));
        where.and(counsel.qnaGubn.eq(QnaGubn.Q));
        switch (option){
            case "ALL":
                break;
            case "A":
                where.and(counsel.counselState.eq(CounselState.A));
                where.and(
                        JPAExpressions
                                .select(childCounsel.count())
                                .from(childCounsel)
                                .where(childCounsel.parentCounsel.id.eq(counsel.id))
                                .gt(0L));
                break;
            case "R":
                where.and(counsel.counselState.eq(CounselState.R));
                where.and(
                        JPAExpressions
                                .select(childCounsel.count())
                                .from(childCounsel)
                                .where(childCounsel.parentCounsel.id.eq(counsel.id))
                                .gt(0L));
                break;
            case"N":
               where.and(

                               JPAExpressions
                                       .select(childCounsel.count())
                                       .from(childCounsel)
                                       .where(childCounsel.parentCounsel.id.eq(counsel.id))
                                       .eq(0L));
            default:
                break;
        }
        where.and(counsel.isDelete.eq(YN.N));
        JPQLQuery<Counsel> result = from(counsel).where(where);
        JPQLQuery<Counsel> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Counsel> fetchResults = query.fetchResults();
        return fetchResults.getResults();
    }
    @Override
    public List<Counsel> findMyaCounselList(Account account, Pageable pageable,String option){
        QCounsel counsel = QCounsel.counsel;
        BooleanBuilder where = new BooleanBuilder();
        where.and(counsel.account.id.eq(account.getId()));
        where.and(counsel.qnaGubn.eq(QnaGubn.A));
        switch (option){
            case "ALL":
                break;
            case "A": //채택
                where.and(counsel.counselState.eq(CounselState.A));
                break;
            case "R"://완료
                where.and(counsel.parentCounsel.counselState.eq(CounselState.A));
                break;
            case"N"://진행중
                where.and(counsel.parentCounsel.counselState.eq(CounselState.R));
            default:
                break;
        }
        where.and(counsel.isDelete.eq(YN.N));
        JPQLQuery<Counsel> result = from(counsel).where(where);
        JPQLQuery<Counsel> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<Counsel> fetchResults = query.fetchResults();
        return fetchResults.getResults();
    }
    @Override
    public long countAdoptedAnswer(Long answerId, Long accountId) {
        QCounsel counsel = QCounsel.counsel;
        BooleanBuilder where = new BooleanBuilder();
        where.and(counsel.counselState.eq(CounselState.A)); // 채택된 글이고
        where.and(counsel.account.id.eq(accountId)); //답변작성자 글이고
        where.and(counsel.qnaGubn.eq(QnaGubn.A)); // 답변이고
        where.and(counsel.isDelete.eq(YN.N)); // 삭제가 안된글이고
        JPQLQuery<Counsel> result = from(counsel).where(where);
        return result.fetchCount();
    }

    @Override
    public List<Counsel> findCounselTag(String tag) {
        QCounsel counsel = QCounsel.counsel;
        BooleanBuilder where = new BooleanBuilder();
        where.and(counsel.isDelete.eq(YN.N)); // 삭제안된글
        where.and(counsel.inputTag.contains(tag));
        JPQLQuery<Counsel> result = from(counsel).where(where);
        return result.fetch();
    }
}
