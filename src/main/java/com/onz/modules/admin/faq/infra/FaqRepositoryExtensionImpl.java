package com.onz.modules.admin.faq.infra;

import com.onz.modules.admin.event.domain.QEvent;
import com.onz.modules.admin.event.infra.EventRepositoryExtension;
import com.onz.modules.admin.event.web.dto.EventSearchRequestDto;
import com.onz.modules.admin.event.web.dto.EventSearchResponseDto;
import com.onz.modules.admin.faq.domian.Faq;
import com.onz.modules.admin.faq.domian.QFaq;
import com.onz.modules.admin.faq.web.dto.FaqSearchRequestDto;
import com.onz.modules.admin.faq.web.dto.FaqSearchResponseDto;
import com.onz.modules.admin.notice.domain.Notice;
import com.onz.modules.admin.notice.domain.enums.DeviceGubn;
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

public class FaqRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        FaqRepositoryExtension {

    private final JPAQueryFactory qf;
    private final EntityManager em;

    public FaqRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Faq.class);
        this.qf = qf;
        this.em = em;
    }

    private BooleanBuilder getWhere(FaqSearchRequestDto faqSearchRequestDto,
                                    QFaq faq) {

        BooleanBuilder where = new BooleanBuilder();
        if(faqSearchRequestDto.getTxt()!=null){
            where.and(faq.content.contains(faqSearchRequestDto.getTxt()));
            where.or(faq.title.contains(faqSearchRequestDto.getTxt()));
        }
        if (faqSearchRequestDto.getCreateDtA() != null) {
            if (faqSearchRequestDto.getCreateDtB() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String ccc = String.format(account.createdAt.toString(), "yyyy-MM-dd");
                ZonedDateTime aaa = ZonedDateTime.of(LocalDateTime.parse(faqSearchRequestDto.getCreateDtA() + " 00:00:00", formatter), ZoneId.of("Asia/Seoul"));
                ZonedDateTime bbb = ZonedDateTime.of(LocalDateTime.parse(faqSearchRequestDto.getCreateDtB() + " 23:59:59", formatter), ZoneId.of("Asia/Seoul"));
                where.and(faq.createDt.between(
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", aaa),
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", bbb)
                ));
            }
        }
        if(faqSearchRequestDto.getDeviceGubn()!=null){
            switch (faqSearchRequestDto.getDeviceGubn()){
                case I:
                    where.and(faq.deviceGubn.eq(DeviceGubn.I));
                    break;
                case A:
                    where.and(faq.deviceGubn.eq(DeviceGubn.A));
                    break;
                default:
                    break;
            }
        }
        return where;
    }

    @Override
    public List<FaqSearchResponseDto> findFaqSearch(FaqSearchRequestDto faqSearchRequestDto, Pageable pageable) {
        QFaq faq = QFaq.faq;
        BooleanBuilder where = this.getWhere(faqSearchRequestDto, faq);
        JPQLQuery<FaqSearchResponseDto> result = from(faq).select(
                        Projections.fields(FaqSearchResponseDto.class,
                                faq.deviceGubn,
                                faq.title,
                                faq.category,
                                faq.userId,
                                faq.createDt,
                                faq.useYn
                        )
                )
                .where(where).orderBy(faq.createDt.desc());
        JPQLQuery<FaqSearchResponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<FaqSearchResponseDto> findLiveMemberResults = query.fetchResults();
        List<FaqSearchResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();
        return findLiveMemberListResults;
    }
}
