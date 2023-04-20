package com.onz.modules.account.domain;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.response.enums.GubnConverter;
import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.common.web.dto.response.enums.Role;
import com.onz.modules.account.domain.embed.Myinfo;
import com.onz.modules.account.domain.enums.*;
import com.onz.modules.account.web.dto.request.AccountMyinfoUpdateRequest;
import com.onz.modules.account.web.dto.request.AccountUpdateRequest;
import com.onz.common.domain.BaseEntity;
import com.onz.modules.admin.auth.web.dto.AdminCreateRequestDto;
import com.onz.modules.admin.notice.domain.enums.DeviceGubn;
import com.onz.modules.auth.application.util.MD5Utils;
import com.onz.modules.auth.application.util.MysqlAESUtil;
import com.onz.modules.auth.application.util.MysqlSHA2Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onz.modules.common.grade.domain.Grade;
import com.onz.modules.company.domain.Company;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String userId;
    private String snsId;
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String deviceId;
    //@Enumerated(EnumType.STRING)
    @Convert(converter = AuthProviderConverter.class)
    private AuthProvider snsType = AuthProvider.local;

    //@Enumerated(EnumType.STRING)
    @Convert(converter = GubnConverter.class)
    private Gubn gubn = Gubn.PARENT;

    private Integer point;

    @Embedded
    private Myinfo myinfo; // 내정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", nullable = true)
    private Company company;


    private String deviceGubn ;//기기옵션

    private String pushYn;
    private String noticeYn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade",nullable = true)
    private Grade grade;

    private String temp1;
    private String temp2;

    @Builder
    public Account(String userId, Gubn gubn, Role role,Grade grade, AuthProvider provider) {
        this.gubn = gubn;
        this.snsType = provider;
        this.role = role;
        this.temp1 = userId; // userId 임시보관.
        this.grade = grade;
        if(gubn != null) {
            // Final Step;
            byte[] encode = new byte[0];
            try {
                String key = String.format("%s%s%s", "ONZ!@#", this.gubn.getCode(), this.snsType.getCode());
                encode = MysqlAESUtil.encryptoByte(key, userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String encodedUserId = MysqlSHA2Util.getSHA512(encode);
            this.userId = encodedUserId;
        }else{
            // First Step
            this.userId = userId; // plained UserId;
        }
    }

    public Account(AdminCreateRequestDto dto) {
        String pwEnc = MD5Utils.getMD5(dto.getPw());
        this.userId = MysqlSHA2Util.getSHA512(dto.getUserId());
        this.password = pwEnc;
        this.role = Role.ADMIN;
        this.snsType = AuthProvider.local;
        this.gubn = null;
        this.point = 0;
        this.myinfo = null;
        this.temp1 = dto.getPw();
        this.temp2= dto.getUserId();
    }

    public void setUpdateData(AccountUpdateRequest account) {
        String userId = account.getUserId();
        this.temp1 = userId;

        if(userId !=null){
            byte[] encode = new byte[0];
            try {
                String key = String.format("%s%s%s", "ONZ!@#", this.gubn.getCode(), this.snsType.getCode());
                encode = MysqlAESUtil.encryptoByte(key, userId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String encodedUserId = MysqlSHA2Util.getSHA512(encode);
            this.userId = encodedUserId;
        }
    }

    public void setUpdateMyinfo(AccountMyinfoUpdateRequest req){
        if(myinfo==null) myinfo = new Myinfo();
        myinfo.setInterestCompany(req.getInterestCompanyName()!=null? InterestCompany.valueOf(req.getInterestCompanyName()) : null);
        myinfo.setBirthYYYY(req.getBirthYYYY());
        myinfo.setInterestZone(req.getInterestZone());
        myinfo.setMajorSchool(req.getMajorSchool());
        myinfo.setMajorDepartment(req.getMajorDepartment());

    }

    public Account update(String registrationId) {
        AuthProvider snsType = AuthProvider.valueOf(registrationId);
        if (Objects.isNull(this.snsType)) {
            this.snsType = snsType;
        }
        return this;
    }

}
