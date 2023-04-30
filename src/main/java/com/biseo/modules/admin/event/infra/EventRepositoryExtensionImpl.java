package com.biseo.modules.admin.event.infra;

import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.admin.event.domain.QEvent;
import com.biseo.modules.admin.event.web.dto.EventSearchRequestDto;
import com.biseo.modules.admin.event.web.dto.EventSearchResponseDto;
import com.biseo.modules.admin.notice.domain.Notice;
import com.biseo.modules.admin.notice.domain.QNotice;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;

public class EventRepositoryExtensionImpl  extends QuerydslRepositorySupport implements
        EventRepositoryExtension {

    private final JPAQueryFactory qf;
    private final EntityManager em;

    public EventRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Notice.class);
        this.qf = qf;
        this.em = em;
    }
    private BooleanBuilder getWhere(EventSearchRequestDto eventSearchRequestDto,
                                    QEvent event) {

        BooleanBuilder where = new BooleanBuilder();
        where.and(event.useYn.eq(YN.valueOf(YN.Y.name())));
        if(eventSearchRequestDto.getTxt()!=null){
            where.and(event.content.contains(eventSearchRequestDto.getTxt()));
            where.or(event.title.contains(eventSearchRequestDto.getTxt()));
        }
        return where;
    }
    @Override
    public List<EventSearchResponseDto> findEventSearch(EventSearchRequestDto eventSearchRequestDto, Pageable pageable) {
        QEvent event = QEvent.event;
        BooleanBuilder where = this.getWhere(eventSearchRequestDto, event);
        JPQLQuery<EventSearchResponseDto> result = from(event).select(
                        Projections.fields(EventSearchResponseDto.class,
                                event.id,
                                event.deviceGubn,
                                event.title,
                                event.content,
                                event.createDt,
                                event.startDt,
                                event.endDt,
                                event.useYn,
                                event.submitPopup
                        )
                )
                .where(where).orderBy(event.createDt.desc());
        JPQLQuery<EventSearchResponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<EventSearchResponseDto> findLiveMemberResults = query.fetchResults();
        List<EventSearchResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();
        return findLiveMemberListResults;
    }
}
