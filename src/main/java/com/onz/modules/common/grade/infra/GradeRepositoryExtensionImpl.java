package com.onz.modules.common.grade.infra;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.QAccount;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberPointResponse;
import com.onz.modules.common.grade.domain.Grade;
import com.onz.modules.common.grade.domain.QGrade;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class GradeRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        GradeRepositoryExtension {
    private final JPAQueryFactory jpaQueryFactory;

    public GradeRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Grade.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Grade pointCheck(Account account){
        QGrade grade=QGrade.grade1;
        QAccount account2 = QAccount.account;
        BooleanBuilder where = new BooleanBuilder();
        // where.and(account2.isDelete.eq(YN.N)).and(account2.eq(account));
        where.and(grade.startTot.loe(account.getPoint()));
        where.and(grade.endTot.goe(account.getPoint()));

        Grade result = null;
        if(account.getGrade().getGrade()!=null) {
            if (account2.point != null && account.getPoint() >= 3501) {
                result = jpaQueryFactory.select(grade).from(grade).where(where).fetchOne();
            }else{
                result= jpaQueryFactory.select(grade).from(grade).where(grade.grade.eq("1")).fetchOne();
            }
        }


        return result!=null? result : account.getGrade();
    }
}
