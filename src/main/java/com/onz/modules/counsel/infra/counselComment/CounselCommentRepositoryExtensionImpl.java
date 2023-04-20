package com.onz.modules.counsel.infra.counselComment;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.counsel.domain.CounselComment;
import com.onz.modules.counsel.domain.QCounselComment;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CounselCommentRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        CounselCommentRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;

    public CounselCommentRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(CounselComment.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<CounselComment> findCommentList(Long parentCounselId, Pageable pageable){
        QCounselComment comment = QCounselComment.counselComment;
        BooleanBuilder where = new BooleanBuilder();
        where.and(comment.isDelete.eq(YN.N));
        where.and(comment.counsel.id.eq(parentCounselId));
        JPQLQuery<CounselComment> result = from(comment).where(where);
        JPQLQuery<CounselComment> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<CounselComment> fetchResults = query.fetchResults();
        return fetchResults.getResults();
    }

    @Override
    public long countCommentList(Long parentCounselId) {
        QCounselComment comment = QCounselComment.counselComment;
        BooleanBuilder where = new BooleanBuilder();
        where.and(comment.isDelete.eq(YN.N));
        where.and(comment.counsel.id.eq(parentCounselId));
        JPQLQuery<CounselComment> result = from(comment).where(where);
        return result.fetchCount();
    }
}
