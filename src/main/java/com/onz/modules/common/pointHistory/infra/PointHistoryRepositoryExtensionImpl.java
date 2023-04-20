package com.onz.modules.common.pointHistory.infra;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.common.pointHistory.domain.PointHistory;
import com.onz.modules.common.pointHistory.domain.QPointHistory;
import com.onz.modules.common.pointHistory.web.dto.request.PointHistorySearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class PointHistoryRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        PointHistoryRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public PointHistoryRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(PointHistory.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public PageImpl<PointHistory> list(PointHistorySearchRequest pointHistorySearchRequest) {
        QPointHistory pointHistory = QPointHistory.pointHistory;

        final Pageable pageable = pointHistorySearchRequest.getPageable();

        BooleanBuilder where = new BooleanBuilder();
        where.and(pointHistory.isDelete.eq(YN.N));

        JPQLQuery<PointHistory> result = from(pointHistory)
            .where(where);

        JPQLQuery<PointHistory> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<PointHistory> fetchResults = query.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

}
