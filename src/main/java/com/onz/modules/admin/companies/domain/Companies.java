package com.onz.modules.admin.companies.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.web.dto.response.enums.ProcessT;
import com.onz.common.web.dto.response.enums.State;
import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.companies.domain.enums.FixOption;
import com.onz.modules.admin.companies.web.dto.CompaniesCreateRequestDto;
import com.onz.modules.company.domain.Company;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Account 에서 받아오기

    @CreationTimestamp
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private ZonedDateTime createDt;

    private String fixText;
    //기관정보
    //company에서 받아오기

    //처리현황 adminid는 account에서 받아오기
    private String apprId;

    @Enumerated(EnumType.STRING)
    private ProcessT process;

    private String apprTxt;
    private ZonedDateTime apprDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId")
    private Company company;

    @Enumerated(EnumType.STRING)
    private FixOption fixOption;

    @Builder(builderMethodName = "CompanyB")
    public Companies(String fixText , ProcessT process, Company company, Account account,FixOption fixOption) {
        this.fixText = fixText;
        this.process=process;
        this.account = account;
        this.company =company;
        this.fixOption=fixOption;
    }

    public Companies(Companies companies) {
        this.id = companies.getId();
        this.createDt = companies.getCreateDt();
        this.fixText = companies.getFixText();
        this.apprId = companies.getApprId();
        this.apprTxt = companies.getApprTxt();
        this.account = companies.getAccount();
        this.company=companies.getCompany();
        this.company = companies.getCompany();
        this.process= companies.getProcess();
    }


}
