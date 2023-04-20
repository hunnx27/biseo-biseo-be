package com.onz.modules.admin.notice.domain;

import com.onz.common.web.dto.response.enums.GubnConverter;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.Admin;
import com.onz.modules.admin.notice.domain.enums.DeviceGubn;
import com.onz.modules.admin.notice.domain.enums.DeviceGubnConverter;
import com.onz.modules.admin.notice.web.dto.NoticeRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId")
    private Account account;

    @Enumerated(EnumType.STRING)
    private DeviceGubn deviceGubn=DeviceGubn.P;
    private String content;
    private String title;
    private ZonedDateTime createDt;
    @Enumerated(EnumType.STRING)
    private YN useYn=YN.Y;


    public Notice(NoticeRequestDto noticeRequestDto,Account account) {
        this.deviceGubn=DeviceGubn.valueOf(noticeRequestDto.getDeviceGubn());
        this.content = noticeRequestDto.getContent();
        this.title = noticeRequestDto.getTitle();
        this.account = account;
    }

    public Notice(NoticeRequestDto noticeRequestDto) {
        this.deviceGubn=DeviceGubn.valueOf(noticeRequestDto.getDeviceGubn());
        this.content = noticeRequestDto.getContent();
        this.title = noticeRequestDto.getTitle();
    }
}
