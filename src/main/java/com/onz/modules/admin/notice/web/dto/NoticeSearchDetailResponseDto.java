package com.onz.modules.admin.notice.web.dto;

import com.onz.modules.admin.notice.domain.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class NoticeSearchDetailResponseDto {
    private String title;
    private String deviceGubn;
    private String useYn;
    private String content;
    private String createDt;

    public NoticeSearchDetailResponseDto(Optional<Notice> notice) {
        if(notice.isPresent()) {
            this.title = notice.get().getTitle();
            this.deviceGubn = notice.get().getDeviceGubn().getName();
            this.useYn = notice.get().getUseYn().name();
            this.content = notice.get().getContent();
            this.createDt = notice.get().getCreateDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
        }
    }
    public NoticeSearchDetailResponseDto(Notice notice) {
        if(notice!=null) {
            this.title = notice.getTitle();
            this.deviceGubn = notice.getDeviceGubn().getName();
            this.useYn = notice.getUseYn().name();
            this.content = notice.getContent();
        }
    }
}
