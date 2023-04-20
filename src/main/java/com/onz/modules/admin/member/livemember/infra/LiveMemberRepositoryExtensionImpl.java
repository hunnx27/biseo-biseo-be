package com.onz.modules.admin.member.livemember.infra;

import com.onz.common.web.dto.response.enums.Role;
import com.onz.common.web.dto.response.enums.State;
import com.onz.common.exception.CustomException;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.QAccount;
import com.onz.modules.admin.member.livemember.web.dto.*;
import com.onz.modules.common.pointHistory.domain.QPointHistory;
import com.onz.modules.counsel.domain.QCounsel;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.review.domain.QCompanyReview;
import com.onz.modules.review.domain.QInterviewReview;
import com.onz.modules.review.domain.QYearAmtReview;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
@Slf4j
public class LiveMemberRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        LiveMemberRepositoryExtension {
    private final JPAQueryFactory qf;
    private final EntityManager em;

    public LiveMemberRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Account.class);
        this.qf = qf;
        this.em = em;
    }

    private BooleanBuilder getWhere(LiveMemberRequestDto liveMemberRequestDto,
                                    QAccount account, QCompanyReview companyReview, QInterviewReview interviewReview, QYearAmtReview amtReview, QCounsel counsel) {

        BooleanBuilder where = new BooleanBuilder();
        String zoneCode = liveMemberRequestDto.getSido() + liveMemberRequestDto.getSigungu();
        if (liveMemberRequestDto.getSido() != null) {
            //sido가 널이 아닐떄
            if (liveMemberRequestDto.getSido() == null) {
                //모두검색
            }
            if (liveMemberRequestDto.getSigungu() == null) {//Sido값은들어옴 ,만약 군구가 널이라면
                where.and(QAccount.account.myinfo.interestZone.startsWith(liveMemberRequestDto.getSido()));//시도로검색
            } else {
                //만약 null이 아니라면
                log.info(String.valueOf(account.myinfo.interestZone));
                where.and((QAccount.account.myinfo.interestZone.eq(zoneCode)));
            }
        }
        if (liveMemberRequestDto.getGubn() == null) {
            //gubun은 이넘으로 all을 받기 떄문에 처리 ㄴㄴ -> 해야할듯
        } else {
            where.and(account.gubn.eq(liveMemberRequestDto.getGubn()));
        }
        if (liveMemberRequestDto.getBirthYYYYo() != null) {
            if (liveMemberRequestDto.getBirthYYYYt() != null) {
//                Long one = Long.valueOf(liveMemberRequestDto.getBirthYYYYo());
//                Long two = Long.valueOf(liveMemberRequestDto.getBirthYYYYt());
                where.and(account.myinfo.birthYYYY.between(liveMemberRequestDto.getBirthYYYYo(), liveMemberRequestDto.getBirthYYYYt()));
            }
        }
        if (liveMemberRequestDto.getCreateAtA() != null) {
            if (liveMemberRequestDto.getCreateAtD() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//                String ccc = String.format(account.createdAt.toString(), "yyyy-MM-dd");
                ZonedDateTime aaa = ZonedDateTime.of(LocalDateTime.parse(liveMemberRequestDto.getCreateAtA() + " 00:00:00", formatter), ZoneId.of("Asia/Seoul"));
                ZonedDateTime bbb = ZonedDateTime.of(LocalDateTime.parse(liveMemberRequestDto.getCreateAtD() + " 23:59:59", formatter), ZoneId.of("Asia/Seoul"));
                where.and(account.createdAt.between(
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", aaa),
                        Expressions.dateTemplate(ZonedDateTime.class, "{0}", bbb)
                ));


            }
        }
        if (liveMemberRequestDto.getUserId() != null) {
            where.and(account.userId.eq(liveMemberRequestDto.getUserId()));
        }

        if (liveMemberRequestDto.getReviewCount() != null) {
            if (!liveMemberRequestDto.getReviewCount().equals("N")) {
                if (liveMemberRequestDto.getOptions().equals("goe")) {
                    where.and(
                            Expressions.asNumber(
                                            JPAExpressions
                                                    .select(companyReview.count())
                                                    .from(companyReview)
                                                    .where(companyReview.account.eq(account))
                                    ).castToNum(Long.class)
                                    .add(
                                            Expressions.asNumber(
                                                    JPAExpressions
                                                            .select(interviewReview.count())
                                                            .from(interviewReview)
                                                            .where(interviewReview.account.eq(account))
                                            ).castToNum(Long.class)
                                    )
                                    .add(
                                            Expressions.asNumber(
                                                    JPAExpressions
                                                            .select(amtReview.count())
                                                            .from(amtReview)
                                                            .where(amtReview.account.eq(account))
                                            ).castToNum(Long.class)
                                    ).goe(liveMemberRequestDto.getReviewCountNum()));
                } else {
                    where.and(
                            JPAExpressions
                                    .select(companyReview.count())
                                    .from(companyReview)
                                    .where(companyReview.account.eq(account))
                                    .loe(liveMemberRequestDto.getReviewCountNum()));
                }
            }
        }

        if (liveMemberRequestDto.getCounselQCount() != null) {
            if (!liveMemberRequestDto.getCounselQCount().equals("N")) {
                if (liveMemberRequestDto.getOptions().equals("goe")) {
                    where.and(
                            JPAExpressions
                                    .select(counsel.count())
                                    .from(counsel)
                                    .where(
                                            counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.Q))
                                    )
                                    .goe(liveMemberRequestDto.getCounselQCountNum()));
                } else {
                    where.and(
                            JPAExpressions
                                    .select(counsel.count())
                                    .from(counsel)
                                    .where(counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.Q)))
                                    .loe(liveMemberRequestDto.getCounselQCountNum()));
                }
            }
        }

        if (liveMemberRequestDto.getCounselACount() != null) {
            if (!liveMemberRequestDto.getCounselACount().equals("N")) {
                if (liveMemberRequestDto.getOptions().equals("goe")) {
                    where.and(
                            JPAExpressions
                                    .select((counsel.qnaGubn.eq(QnaGubn.A)).count())
                                    .from(counsel)
                                    .where(counsel.account.eq(account))
                                    .goe(liveMemberRequestDto.getCounselACountNum()));
                } else {
                    where.and(
                            JPAExpressions
                                    .select((counsel.qnaGubn.eq(QnaGubn.A)).count())
                                    .from(counsel)
                                    .where(counsel.account.eq(account))
                                    .loe(liveMemberRequestDto.getCounselACountNum()));
                }
            }
        }
        where.and(account.role.eq(Role.USER));
        //goe 이상 loe 이하
        return where;
    }

    @Override
    public List<LiveMemberResponseDto> findByLiveMember(LiveMemberRequestDto liveMemberRequestDto, Pageable pageable) {
        // Q클래스 정의
        QAccount account = QAccount.account;
        QCompanyReview companyReview = QCompanyReview.companyReview;
        QInterviewReview interviewReview = QInterviewReview.interviewReview;
        QYearAmtReview amtReview = QYearAmtReview.yearAmtReview;
        QCounsel counsel = QCounsel.counsel;


        // where절 정의
        BooleanBuilder where = this.getWhere(liveMemberRequestDto, account, companyReview, interviewReview, amtReview, counsel);

        // 쿼리 생성(리스트)
        JPQLQuery<LiveMemberResponseDto> result = from(account).select(
                        Projections.fields(LiveMemberResponseDto.class,
                                account.id,
                                account.gubn,
                                account.userId,
                                account.snsType,
                                account.grade.grade,
                                //누적포인트
                                account.point,
                                account.createdAt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(companyReview.count())
                                                .from(companyReview)
                                                .where(companyReview.account.eq(account))
                                        , "companyReviewCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(interviewReview.count())
                                                .from(interviewReview)
                                                .where(interviewReview.account.eq(account))
                                        , "interviewReviewCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(amtReview.count())
                                                .from(amtReview)
                                                .where(amtReview.account.eq(account))
                                        , "amtReviewCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(counsel.count())
                                                .from(counsel)
                                                .where(
                                                        counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.Q))
                                                )
                                        , "counselQCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(counsel.count())
                                                .from(counsel)
                                                .where(
                                                        counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.A))
                                                )
                                        , "counselACnt")
                        )
                )
                .where(where);
        JPQLQuery<LiveMemberResponseDto> query = getQuerydsl().applyPagination(pageable, result);
//        JPQLQuery<Account> result = from(account).where(where);

        QueryResults<LiveMemberResponseDto> findLiveMemberResults = query.fetchResults();
        List<LiveMemberResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();


        return findLiveMemberListResults;
        //return list.stream().map(com -> new LiveMemberResponseDto(com)).collect(Collectors.toList());

        /*
        JPQLQuery<YearAmtReview> result = from(qYearAmtReview).where(where);
        JPQLQuery<YearAmtReview> query = Objects.requireNonNull(getQuerydsl()).applyPagination(pageable, result);
        QueryResults<YearAmtReview> fetchResults = query.fetchResults();

        List<YearAmtReview> list = fetchResults.getResults();
        return list.stream().map(com -> new YearAmtListResponseDto(com)).collect(Collectors.toList());
         */
    }

    @Override
    public List<LiveMemberResponseDto> findByLiveMember(LiveMemberRequestDto liveMemberRequestDto) {
        // Q클래스 정의
        QAccount account = QAccount.account;
        QCompanyReview companyReview = QCompanyReview.companyReview;
        QInterviewReview interviewReview = QInterviewReview.interviewReview;
        QYearAmtReview amtReview = QYearAmtReview.yearAmtReview;
        QCounsel counsel = QCounsel.counsel;
        // where절 정의
        BooleanBuilder where = this.getWhere(liveMemberRequestDto, account, companyReview, interviewReview, amtReview, counsel);

        // 쿼리 생성(리스트)
        JPQLQuery<LiveMemberResponseDto> result = from(account).select(
                        Projections.fields(LiveMemberResponseDto.class,
                                account.id,
                                account.gubn,
                                account.userId,
                                account.snsType,
                                account.grade.grade,
                                //누적포인트
                                account.point,
                                account.createdAt,
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(companyReview.count())
                                                .from(companyReview)
                                                .where(companyReview.account.eq(account))
                                        , "companyReviewCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(interviewReview.count())
                                                .from(interviewReview)
                                                .where(interviewReview.account.eq(account))
                                        , "interviewReviewCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(amtReview.count())
                                                .from(amtReview)
                                                .where(amtReview.account.eq(account))
                                        , "amtReviewCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(counsel.count())
                                                .from(counsel)
                                                .where(
                                                        counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.Q))
                                                )
                                        , "counselQCnt"),
                                ExpressionUtils.as(
                                        JPAExpressions
                                                .select(counsel.count())
                                                .from(counsel)
                                                .where(
                                                        counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.A))
                                                )
                                        , "counselACnt")
                        )
                )
                .where(where);
        QueryResults<LiveMemberResponseDto> findLiveMemberResults = result.fetchResults();
        List<LiveMemberResponseDto> findLiveMemberListResults = findLiveMemberResults.getResults();

        return findLiveMemberListResults;
    }
    @Override
    public JPQLQuery<Long> findCountMember(LiveMemberRequestDto liveMemberRequestDto) {
        QAccount account = QAccount.account;
        QCompanyReview companyReview = QCompanyReview.companyReview;
        QInterviewReview interviewReview = QInterviewReview.interviewReview;
        QYearAmtReview amtReview = QYearAmtReview.yearAmtReview;
        QCounsel counsel = QCounsel.counsel;

        BooleanBuilder where = this.getWhere(liveMemberRequestDto, account, companyReview, interviewReview, amtReview, counsel);

        JPQLQuery<Long> findLiveMemberTotalCnt = from(account)
                .select(account.count())
                .where(where);
        return findLiveMemberTotalCnt;

    }

    public LiveMemberPointResponse findByAccountPointDetail(Long id) {
        QAccount account = QAccount.account;
        QPointHistory pointHistory = QPointHistory.pointHistory;

        BooleanBuilder where = new BooleanBuilder();
        where.and(account.id.eq(id));

        JPQLQuery<LiveMemberPointResponse> result = from(account).select(
                Projections.fields(LiveMemberPointResponse.class,
                        account.point,
                        account.grade.grade,
                                        Expressions.as(
                                                JPAExpressions
                                                        .select(pointHistory
                                                                .pointInfo.amt.sum())
                                                        .from(pointHistory)
                                                        .where(pointHistory.account.id.eq(account.id).and(pointHistory.pointInfo.amt.goe(0)))
                                                , "ordersHistory")
                        )).where(where);
        LiveMemberPointResponse fetchResulTd = result.fetchOne();
        return fetchResulTd;
    }
    public List<LiveMemberPointListResponse> findByAccountPointList(Long id, Pageable pageable) throws CustomException {
            QAccount account = QAccount.account;
            QPointHistory pointHistory = QPointHistory.pointHistory;
            BooleanBuilder where = new BooleanBuilder();
            where.and(account.id.eq(id));
            JPQLQuery<LiveMemberPointListResponse> result = from(pointHistory).orderBy(pointHistory.createdAt.desc()).select(
                    Projections.fields(LiveMemberPointListResponse.class,
                            pointHistory.createdAt,
                            pointHistory.pointInfo.code,
                            pointHistory.pointInfo.amt,
                            pointHistory.pointInfo.totAmt
                    )
            ).where(where);
            JPQLQuery<LiveMemberPointListResponse> query = getQuerydsl().applyPagination(pageable, result);
//        JPQLQuery<Account> result = from(account).where(where);

            QueryResults<LiveMemberPointListResponse> findLiveMemberResults = query.fetchResults();
            List<LiveMemberPointListResponse> findLiveMemberListResults = findLiveMemberResults.getResults();


            return findLiveMemberListResults;
    }

    public LiveMemberDetailResponse findByAccountDetail(Long id) {
        QAccount account = QAccount.account;
        QCompanyReview companyReview = QCompanyReview.companyReview;
        QInterviewReview interviewReview = QInterviewReview.interviewReview;
        QYearAmtReview amtReview = QYearAmtReview.yearAmtReview;
        QCounsel counsel = QCounsel.counsel;
        QPointHistory pointHistory = QPointHistory.pointHistory;

        BooleanBuilder where = new BooleanBuilder();
        where.and(account.id.eq(id));
        JPQLQuery<LiveMemberDetailResponse> result = from(account).select(
                Projections.fields(LiveMemberDetailResponse.class,
                        account.id,
                        account.createdAt,
                        account.gubn,
                        account.snsType,
                        account.userId,
                        account.modifiedAt,
                        Expressions.as(
                                JPAExpressions
                                        .select(pointHistory
                                                .pointInfo.amt.sum())
                                        .from(pointHistory)
                                        .where(pointHistory.account.id.eq(account.id).and(pointHistory.pointInfo.amt.goe(0)))
                                , "ordersHistory"),
                        account.point,
                        account.myinfo.interestCompany,
                        account.myinfo.interestZone,
                        account.myinfo.majorSchool,
                        account.myinfo.majorDepartment,
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(companyReview.count())
                                        .from(companyReview)
                                        .where(companyReview.account.id.eq(id))
                                , "madeReview"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(companyReview.count())
                                        .from(companyReview)
                                        .where(companyReview.account.id.eq(id).and(companyReview.state.eq(State.W)))
                                , "reviewStateW"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(companyReview.count())
                                        .from(companyReview)
                                        .where(companyReview.account.id.eq(id).and(companyReview.state.eq(State.R)))
                                , "reviewStateR"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(companyReview.count())
                                        .from(companyReview)
                                        .where(companyReview.account.id.eq(id).and(companyReview.state.eq(State.A)))
                                , "reviewStateA"),
                        //컴퍼니
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(amtReview.count())
                                        .from(amtReview)
                                        .where(amtReview.account.id.eq(id))
                                , "madeAmt"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(amtReview.count())
                                        .from(amtReview)
                                        .where(amtReview.account.id.eq(id).and(amtReview.state.eq(State.W)))
                                , "amtStateW"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(amtReview.count())
                                        .from(amtReview)
                                        .where(amtReview.account.id.eq(id).and(amtReview.state.eq(State.R)))
                                , "amtStateR"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(amtReview.count())
                                        .from(amtReview)
                                        .where(amtReview.account.id.eq(id).and(amtReview.state.eq(State.A)))
                                , "amtStateA"),
                        //연봉
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(interviewReview.count())
                                        .from(interviewReview)
                                        .where(interviewReview.account.id.eq(id))
                                , "madeInterview"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(interviewReview.count())
                                        .from(interviewReview)
                                        .where(interviewReview.account.id.eq(id).and(interviewReview.state.eq(State.W)))
                                , "interviewStateW"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(interviewReview.count())
                                        .from(interviewReview)
                                        .where(interviewReview.account.id.eq(id).and(interviewReview.state.eq(State.R)))
                                , "interviewStateR"),
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(interviewReview.count())
                                        .from(interviewReview)
                                        .where(interviewReview.account.id.eq(id).and(interviewReview.state.eq(State.A)))
                                , "interviewStateA"),
                        //인터뷰
//                        ExpressionUtils.as(
//                                JPAExpressions
//                                        .select(amtReview.count())
//                                        .from(amtReview)
//                                        .where(amtReview.account.id.eq(id).and(companyReview.state.eq(State.A)))
//                                , "stateA"),
//                        ExpressionUtils.as(
//                                JPAExpressions
//                                        .select(interviewReview.count())
//                                        .from(interviewReview)
//                                        .where(interviewReview.account.id.eq(id).and(companyReview.state.eq(State.R)))
//                                , "stateR"),

                        //상태

                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(counsel.count())
                                        .from(counsel)
                                        .where(counsel.account.id.eq(id).and(counsel.qnaGubn.eq(QnaGubn.Q)).and(counsel.counselState.eq(CounselState.R)))
                                , "madeQCounselR"),//총 qounsel
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(counsel.count())
                                        .from(counsel)
                                        .where(counsel.account.id.eq(id).and(counsel.qnaGubn.eq(QnaGubn.Q)).and(counsel.counselState.eq(CounselState.A)))
                                , "madeQCounselA"),//총 qounsel
                        ExpressionUtils.as(
                                JPAExpressions
                                        .select(counsel.count())
                                        .from(counsel)
                                        .where(counsel.account.id.eq(id).and(counsel.qnaGubn.eq(QnaGubn.A)))
                                , "madeACounsel")
,account.grade.grade

                )
        ).where(where);
        LiveMemberDetailResponse fetchResulTd = result.fetchOne();
        return fetchResulTd;
    }
//
//    public List<LiveMemberDetailResponse> findByAccountDetailset(Long id) {
//        QAccount account = QAccount.account;
//        QCompanyReview companyReview = QCompanyReview.companyReview;
//        QInterviewReview interviewReview = QInterviewReview.interviewReview;
//        QYearAmtReview amtReview = QYearAmtReview.yearAmtReview;
//        QCounsel counsel = QCounsel.counsel;
//        BooleanBuilder where = new BooleanBuilder();
//        where.and(account.id.eq(id));
//        JPQLQuery<LiveMemberDetailResponse> result = from(account).select(
//                Projections.fields(LiveMemberDetailResponse.class,
//                        Expressions.asNumber(0L).as("totalPoint"),
//                        ExpressionUtils.as(
//                                JPAExpressions
//                                        .select(account.point)
//                                        .from(account)
//                                        .where(where)
//                                , "point"),
//                        account.point,
//                        Expressions.asNumber(0L).as("daycare"),
//                        Expressions.asNumber(0L).as("kinder"),
//                        ExpressionUtils.as(
//                                JPAExpressions
//                                        .select(companyReview.count())
//                                        .from(companyReview)
//                                        .where(where)
//                                , "madeReview"),
//                        ExpressionUtils.as(
//                                JPAExpressions
//                                        .select(amtReview.count())
//                                        .from(amtReview)
//                                        .where(where)
//                                , "madeAmt"),
//                        ExpressionUtils.as(
//                                JPAExpressions
//                                        .select(interviewReview.count())
//                                        .from(interviewReview)
//                                        .where(where)
//                                , "madeInterview"),
//                        ExpressionUtils.as(
//                                JPAExpressions
//                                        .select(counsel.count())
//                                        .from(counsel)
//                                        .where(where.and(counsel.qnaGubn.eq(QnaGubn.Q)))
//                                , "madeQCounsel"),
//                        ExpressionUtils.as(
//                                JPAExpressions
//                                        .select(counsel.count())
//                                        .from(counsel)
//                                        .where(where.and(counsel.qnaGubn.eq(QnaGubn.A)))
//                                , "madeACounsel")
//                ));
//        QueryResults<LiveMemberDetailResponse> fetchResulTd = result.fetchResults();
//        List<LiveMemberDetailResponse> findLiveMemberListResults = fetchResulTd.getResults();
//
//        return findLiveMemberListResults;
//    }

    @Override
    public LiveMemberResponseDto findByLiveMemberTotal(LiveMemberRequestDto liveMemberRequestDto) {
        // Q클래스 정의
        QAccount account = QAccount.account;
        QCompanyReview companyReview = QCompanyReview.companyReview;
        QInterviewReview interviewReview = QInterviewReview.interviewReview;
        QYearAmtReview amtReview = QYearAmtReview.yearAmtReview;
        QCounsel counsel = QCounsel.counsel;

        // where절 정의
        BooleanBuilder where = this.getWhere(liveMemberRequestDto, account, companyReview, interviewReview, amtReview, counsel);

        // 쿼리 생성(집계)
        JPQLQuery<LiveMemberResponseDto> findLiveMemberTotal = from(account).select(
                Projections.constructor(LiveMemberResponseDto.class,
//                        Expressions.asNumber(0L).as("id"),
//                        Expressions.asEnum(Gubn.NULL).as("gubn"),
//                        Expressions.asString("").as("userId"),
//                        Expressions.asEnum(AuthProvider.NULL).as("snsType"),
//                        Expressions.asNumber(0L).as("point"),
//                        Expressions.asSimple(Expressions.nullExpression(ZonedDateTime.class)).as("createdAt"),
//                        Expressions.asSimple(Expressions.template(ZonedDateTime.class, TemplateFactory.DEFAULT.create("cast(null as datetime)"))).as("createdAt"),
//                        Expressions.asNumber(0L).as("companyReviewCnt"),
//                        Expressions.asNumber(0L).as("amtReviewCnt"),
//                        Expressions.asNumber(0L).as("counselQCnt"),
//                        Expressions.asNumber(0L).as("counselACnt"),
                        ExpressionUtils.as(
                                Expressions.asNumber(
                                        JPAExpressions
                                                .select(companyReview.count())
                                                .from(companyReview)
                                                .where(companyReview.account.eq(account))).castToNum(Long.class).sum()
                                , "companyReviewCnt"),
                        ExpressionUtils.as(
                                Expressions.asNumber(
                                        JPAExpressions
                                                .select(interviewReview.count())
                                                .from(interviewReview)
                                                .where(interviewReview.account.eq(account))).castToNum(Long.class).sum()
                                , "interviewReviewCnt"),
                        ExpressionUtils.as(
                                Expressions.asNumber(
                                        JPAExpressions
                                                .select(amtReview.count())
                                                .from(amtReview)
                                                .where(amtReview.account.eq(account))).castToNum(Long.class).sum()
                                , "amtReviewCnt"),
                        ExpressionUtils.as(
                                Expressions.asNumber(
                                        JPAExpressions
                                                .select(counsel.count())
                                                .from(counsel)
                                                .where(
                                                        counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.Q))
                                                )).castToNum(Long.class).sum()
                                , "counselQCnt"),
                        ExpressionUtils.as(
                                Expressions.asNumber(
                                        JPAExpressions
                                                .select(counsel.count())
                                                .from(counsel)
                                                .where(
                                                        counsel.account.eq(account).and(counsel.qnaGubn.eq(QnaGubn.A))
                                                )).castToNum(Long.class).sum()
                                , "counselACnt")
                )).where(where);
        LiveMemberResponseDto fetchResulTd = findLiveMemberTotal.fetchOne();


        return fetchResulTd;

    }

}
