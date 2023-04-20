package com.onz.modules.admin.notice.infra;


import com.onz.modules.admin.notice.domain.Notice;
import com.onz.modules.admin.notice.domain.QNotice;
import com.onz.modules.admin.notice.domain.enums.DeviceGubn;
import com.onz.modules.admin.notice.web.dto.NoticeSearchRequestDto;
import com.onz.modules.admin.notice.web.dto.NoticeSearchResponseDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
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

public class NoticeRepositoryExtensionImpl  extends QuerydslRepositorySupport implements
        NoticeRepositoryExtension {

    private final JPAQueryFactory qf;
    private final EntityManager em;

    public NoticeRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Notice.class);
        this.qf = qf;
        this.em = em;
    }
    private BooleanBuilder getWhere(NoticeSearchRequestDto noticeSearchRequestDto,
                                     QNotice notice) {

        BooleanBuilder where = new BooleanBuilder();
        if(noticeSearchRequestDto.getTxt()!=null){
            where.and(notice.content.contains(noticeSearchRequestDto.getTxt()));
        }
        if(noticeSearchRequestDto.getDeviceGubn()!=null){
            switch (DeviceGubn.valueOf(noticeSearchRequestDto.getDeviceGubn())){
                case A:
                    where.and(notice.deviceGubn.eq(DeviceGubn.A));
                    break;
                case I:
                    where.and(notice.deviceGubn.eq(DeviceGubn.I));
                default:
                    break;
            }
        }
        if (noticeSearchRequestDto.getCreateAdt() != null) {
            if (noticeSearchRequestDto.getCreateBdt() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String ccc = String.format(account.createdAt.toString(), "yyyy-MM-dd");
                ZonedDateTime aaa = ZonedDateTime.of(LocalDateTime.parse(noticeSearchRequestDto.getCreateAdt() + " 00:00:00", formatter), ZoneId.of("Asia/Seoul"));
                ZonedDateTime bbb = ZonedDateTime.of(LocalDateTime.parse(noticeSearchRequestDto.getCreateBdt() + " 23:59:59", formatter), ZoneId.of("Asia/Seoul"));
                where.and(notice.createDt.between(
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", aaa),
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", bbb)
                ));
            }
        }
        return where;
    }
    @Override
    public List<NoticeSearchResponseDto> findNoticeSearch(NoticeSearchRequestDto noticeSearchRequestDto, Pageable pageable) {
        QNotice notice =QNotice.notice;
        BooleanBuilder where = this.getWhere(noticeSearchRequestDto, notice);
        JPQLQuery<NoticeSearchResponseDto> result = from(notice).select(
                        Projections.fields(NoticeSearchResponseDto.class,
                                notice.id,
                                notice.title,
                                notice.createDt,
                                notice.useYn,
                                notice.account.userId,
                                notice.deviceGubn

                        )
                )
                .where(where).orderBy(notice.createDt.desc());
        JPQLQuery<NoticeSearchResponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<NoticeSearchResponseDto> findLiveMemberResults = query.fetchResults();
        List<NoticeSearchResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();
        return findLiveMemberListResults;
    }
}
