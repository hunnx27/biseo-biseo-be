package com.onz.modules.counsel.infra.counselRecommend;

import com.onz.modules.counsel.domain.CounselRecommend;
import com.onz.modules.counsel.domain.QCounselRecommend;
import com.onz.modules.counsel.domain.enums.RecommendGubn;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

public class CounselRecommendRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CounselRecommendRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CounselRecommendRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(CounselRecommend.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<CounselRecommend> findRecommend(Long answerId) {
        QCounselRecommend recommend = QCounselRecommend.counselRecommend;
        BooleanBuilder where = new BooleanBuilder();
        where.and(recommend.counsel.id.eq(answerId));
        where.and(recommend.recommendGubn.eq(RecommendGubn.R));
        JPQLQuery<CounselRecommend> result = from(recommend).where(where);
        return result.fetch();
    }

    @Override
    public List<CounselRecommend> findRecommendCheck(Long answerId, Long accountId) {
        QCounselRecommend recommend = QCounselRecommend.counselRecommend;
        BooleanBuilder where = new BooleanBuilder();
        where.and(recommend.counsel.id.eq(answerId));
        where.and(recommend.recommendGubn.eq(RecommendGubn.R));
        where.and(recommend.account.id.eq(accountId));
        JPQLQuery<CounselRecommend> result = from(recommend).where(where);
        return result.fetch();
    }

    @Override
    public List<CounselRecommend> findRecommendCheckN(Long answerId, Long accountId) {
        QCounselRecommend recommend = QCounselRecommend.counselRecommend;
        BooleanBuilder where = new BooleanBuilder();
        where.and(recommend.counsel.id.eq(answerId));
        where.and(recommend.recommendGubn.eq(RecommendGubn.N));
        where.and(recommend.account.id.eq(accountId));
        JPQLQuery<CounselRecommend> result = from(recommend).where(where);
        return result.fetch();
    }
    @Override
    public long countNotice(Long answerId, Long accountId) {
        QCounselRecommend recommend = QCounselRecommend.counselRecommend;
        BooleanBuilder where = new BooleanBuilder();
        where.and(recommend.counsel.id.eq(answerId));
        where.and(recommend.account.id.eq(accountId));
        where.and(recommend.recommendGubn.eq(RecommendGubn.N));
        JPQLQuery<CounselRecommend> result = from(recommend).where(where);
        return result.fetchCount();
    }
}
