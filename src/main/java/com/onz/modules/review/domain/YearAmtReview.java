package com.onz.modules.review.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.domain.BaseEntity;
import com.onz.common.web.dto.response.enums.State;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.company.domain.Company;
import com.onz.modules.review.web.dto.AmtRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class YearAmtReview extends BaseEntity {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @Enumerated(EnumType.STRING)
    private YN annYn;//뭔지모름

    private String zonecode;

//    @ColumnDefault("0")
//    @Column(nullable = true)
    private Long amtOld; //이전연봉

    @ColumnDefault("'W'")
    @Enumerated(EnumType.STRING)
    private State state; // 승인여부 A:승인 W:?

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private ZonedDateTime apprDt;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private ZonedDateTime topchoiceDt;

    private String apprTxt; // 승인메세지 {기관명} 승인처리 되었습니다.
    private String apprId; // 승인자 (관리자)

    @Enumerated(EnumType.STRING)
    private YN topchoiceYn; // 대표 리뷰로 선정여부

    //1 page
    private Long workExp; // 근무시 교사 연차

    @Enumerated(EnumType.STRING)
    private YN workExpOpenYn; // 근무시 교사 연차 공개 여부

    //2 page
    private Long amt; // 연봉

    @Enumerated(EnumType.STRING)
    private YN endAtmYn; // 퇴직금 여부

    @Enumerated(EnumType.STRING)
    private YN etcYn; //수당 여부
    //2-1 page 수당

    private String etcAmt; // 수당금액 배열 , 기준으로 etc_items와 매핑된다
    private String etcItems; //입력한 수당 idx 배열 {1 - 처우개선비 ,2 - 근무환경개선비, 3- 누리과정수당, 4- 기타}

    private String mapsidogunguName;
    /*
    create_dt // 생성일자
    appr_dt // 승인일자
    topchoice_dt //대표리뷰 설정 일자자     */

    @Builder
    public YearAmtReview(AmtRequestDto amtRequestDto, Company company, Account account) {
        this.workExp = amtRequestDto.getWorkExp();
        this.account=account;
        this.workExpOpenYn = amtRequestDto.getWorkExpOpenYn();
        this.amt = amtRequestDto.getAmt();
        this.endAtmYn = amtRequestDto.getEndAtmYn();
        this.etcYn = amtRequestDto.getEtcYn();
        this.etcAmt = amtRequestDto.getEtcAmt();
        this.state=State.W;
        this.annYn=YN.N;
        this.topchoiceYn=YN.N;
        this.etcItems = amtRequestDto.getEtcItems();
        this.company=company;
        this.zonecode=company.getZonecode();
    }

    public YearAmtReview(AmtRequestDto amtRequestDto, ZonedDateTime topchoiceDt, ZonedDateTime apprDt, Long id, YN annYn, Long amtOld, String apprTxt, String apprId, YN topchoiceYn) {
        this.id = id;
        this.annYn = annYn;
        this.amtOld = amtOld;
        this.zonecode=company.getZonecode();
        this.apprTxt = apprTxt;
        this.apprDt=apprDt;
        this.apprId = apprId;
        this.topchoiceYn = topchoiceYn;
        this.topchoiceDt = topchoiceDt;
        this.workExp = amtRequestDto.getWorkExp();
        this.workExpOpenYn = amtRequestDto.getWorkExpOpenYn();
        this.amt = amtRequestDto.getAmt();
        this.endAtmYn = amtRequestDto.getEndAtmYn();
        this.etcYn = amtRequestDto.getEtcYn()==null? YN.N : amtRequestDto.getEtcYn();
        if( etcYn.equals(YN.Y)){
            this.etcAmt = amtRequestDto.getEtcAmt();
            this.etcItems = amtRequestDto.getEtcItems();
        }else{
            this.etcAmt=null;
            this.etcItems = null;
        }
        this.company = getCompany();
    }
}
