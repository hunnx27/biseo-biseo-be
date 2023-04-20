package com.onz.modules.admin.member.deluser.infra;

import com.onz.common.web.dto.response.enums.Role;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.QAccount;
import com.onz.modules.admin.member.deluser.web.dto.*;
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

import static com.onz.common.web.dto.response.enums.YN.N;

public class HumanRepositoryExtensionImpl extends QuerydslRepositorySupport implements HumanRepositoryExtension{
    private final JPAQueryFactory qf;
    private final EntityManager em;

    public HumanRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Account.class);
        this.qf = qf;
        this.em = em;
    }

    private BooleanBuilder getWhere(HumanListRequestDto humanListRequestDto, QAccount account) {

        BooleanBuilder where = new BooleanBuilder();
        if (humanListRequestDto.getGubn() == null) {
            //gubun은 이넘으로 all을 받기 떄문에 처리 ㄴㄴ -> 해야할듯
        } else {
            where.and(account.gubn.eq(humanListRequestDto.getGubn()));
        }
        if (humanListRequestDto.getCreatedAtA() != null) {
            if (humanListRequestDto.getCreatedAtB() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String ccc = String.format(account.createdAt.toString(), "yyyy-MM-dd");
                ZonedDateTime aaa = ZonedDateTime.of(LocalDateTime.parse(humanListRequestDto.getCreatedAtA() + " 00:00:00", formatter), ZoneId.of("Asia/Seoul"));
                ZonedDateTime bbb = ZonedDateTime.of(LocalDateTime.parse(humanListRequestDto.getCreatedAtB() + " 23:59:59", formatter), ZoneId.of("Asia/Seoul"));
                where.and(account.createdAt.between(Expressions.dateTemplate(ZonedDateTime.class, "{0}", aaa), Expressions.dateTemplate(ZonedDateTime.class, "{0}", bbb)));
            }
        }
        if (humanListRequestDto.getUserId() != null) {
            where.and(account.userId.eq(humanListRequestDto.getUserId()));
        }
            //now - 365 <dt
        where.and(account.role.eq(Role.USER));
        where.and(account.isDelete.eq(N));
        return where;
    }

    @Override
    public List<HumanListResponseDto> findByHumanList(HumanListRequestDto humanListRequestDto, Pageable pageable) {
        // Q클래스 정의
        QAccount account = QAccount.account;
        // where절 정의
        BooleanBuilder where = this.getWhere(humanListRequestDto, account);
        ZonedDateTime humanBase = ZonedDateTime.now().minusYears(1);
        where.and(account.lastedAt.before(ZonedDateTime.from(humanBase)));
        // 쿼리 생성(리스트)
        JPQLQuery<HumanListResponseDto> result = from(account).select(
                Projections.fields(HumanListResponseDto.class,
                        account.id,
                        account.gubn,
                        account.userId,
                        account.snsType,
                        account.createdAt,
                        account.lastedAt)
                     ).where(where);
        JPQLQuery<HumanListResponseDto> query = getQuerydsl().applyPagination(pageable, result);
        QueryResults<HumanListResponseDto> findByDelUser = query.fetchResults();
        List<HumanListResponseDto> findLiveMemberListResults = findByDelUser.getResults();

        return findLiveMemberListResults;
    }

    @Override
    public List<HumanListResponseDto> findByHumanUser(HumanListRequestDto humanListRequestDto) {
        // Q클래스 정의
        QAccount account = QAccount.account;
        // where절 정의
        BooleanBuilder where = this.getWhere(humanListRequestDto, account);
        ZonedDateTime humanBase = ZonedDateTime.now().minusYears(1);
        where.and(account.lastedAt.before(ZonedDateTime.from(humanBase)));
        // 쿼리 생성(리스트)
        JPQLQuery<HumanListResponseDto> result = from(account).select(
                Projections.fields(HumanListResponseDto.class,
                        account.id,
                        account.gubn,
                        account.userId,
                        account.snsType,
                        account.createdAt,
                        account.lastedAt)
        ).where(where);
        QueryResults<HumanListResponseDto> findLiveMemberResults = result.fetchResults();
        List<HumanListResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();

        return findLiveMemberListResults;
    }

    @Override
    public HumanResponseDto findByHumanDetail(Long id) {
        // Q클래스 정의
        QAccount account = QAccount.account;

        // where절 정의
        // 쿼리 생성(리스트)
        ZonedDateTime humanBase = ZonedDateTime.now().minusYears(1);
        JPQLQuery<HumanResponseDto> result = from(account).select(
                Projections.fields(HumanResponseDto.class,
                        account.userId,
                        account.point,
                        account.myinfo.interestCompany,
                        account.createdAt,
                        account.isDelete,
                        account.gubn,
                        account.snsType,
                        account.myinfo.interestZone,
                        account.lastedAt
                )).where(account.id.eq(id).and(account.role.eq(Role.USER)).and(account.lastedAt.before(ZonedDateTime.from(humanBase))));
        HumanResponseDto fetchResulTd = result.fetchOne();
        return fetchResulTd;
    }
}
