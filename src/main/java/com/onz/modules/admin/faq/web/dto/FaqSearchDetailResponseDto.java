package com.onz.modules.admin.faq.web.dto;

import com.onz.modules.admin.faq.domian.Faq;
import com.onz.modules.admin.faq.domian.enums.Category;
import com.onz.modules.admin.notice.domain.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class FaqSearchDetailResponseDto {
    private String title;
    private String category;
    private String deviceGubn;
    private String useYn;
    private String content;
    private String createDt;

    public FaqSearchDetailResponseDto(Optional<Faq> faq) {
        this.title = faq.get().getTitle();
        this.category = faq.get().getCategory().getName();
        this.deviceGubn = faq.get().getDeviceGubn().getName();
        this.useYn = faq.get().getUseYn().name();
        this.content = faq.get().getContent();
        this.createDt = faq.get().getCreateDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
    }

    public FaqSearchDetailResponseDto(Faq faq) {
        this.title = faq.getTitle();
        this.category = faq.getCategory().getName();
        this.deviceGubn = faq.getDeviceGubn().getName();
        this.useYn = faq.getUseYn().name();
        this.content = faq.getContent();
    }
}

//public class NoticeSearchDetailResponseDto {
//    private String title;
//    private String deviceGubn;
//    private String useYn;
//    private String content;
//    private String createDt;
//
//    public NoticeSearchDetailResponseDto(Notice notice) {
//        if(notice!=null) {
//            this.title = notice.getTitle();
//            this.deviceGubn = notice.getDeviceGubn().getName();
//            this.useYn = notice.getUseYn().name();
//            this.content = notice.getContent();
//        }
//    }
//}
