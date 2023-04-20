package com.onz.modules.admin.event.infra;

import com.onz.common.exception.CustomException;
import com.onz.modules.account.domain.QAccount;
import com.onz.modules.admin.event.domain.QEvent;
import com.onz.modules.admin.event.domain.QEventItem;
import com.onz.modules.admin.event.web.dto.EventItemListResponseDto;
import com.onz.modules.admin.member.livemember.web.dto.LiveMemberPointListResponse;
import com.onz.modules.admin.notice.domain.Notice;
import com.onz.modules.common.pointHistory.domain.QPointHistory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

public class EventItemRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        EventItemRepositoryExtension {

    private final JPAQueryFactory qf;
    private final EntityManager em;

    public EventItemRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Notice.class);
        this.qf = qf;
        this.em = em;
    }

}
