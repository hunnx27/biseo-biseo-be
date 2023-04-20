package com.onz.modules.admin.event.web.dto;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.admin.event.domain.Event;
import com.onz.modules.admin.event.domain.enums.Landding;
import com.onz.modules.admin.notice.domain.Notice;
import lombok.Data;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
public class EventSearchDetailResponseDto {
    private String startDt;
    private String endDt;
    private String createDt;
    private String deviceGubn;
    private String useYn;
    private String title;
    private String content;
    private String files;
    private String buttonLanding;
    private String landingUrl;
    private String noticeId;
    private String popupShowOption;
    private String submit_popup;
    private String submit_popup_location;

    public EventSearchDetailResponseDto(Optional<Event> event) {
        if (event.isPresent()) {
            this.title = event.get().getTitle();
            this.deviceGubn = event.get().getDeviceGubn().getName();
            this.useYn = event.get().getUseYn().name();
            this.content = event.get().getContent();
            this.createDt = event.get().getCreateDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
            this.startDt = event.get().getStartDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
            this.endDt = event.get().getEndDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
            if(event.get().getImgUrl()!=null){
                this.files = event.get().getImgUrl().getImgUrl();
            }
            this.buttonLanding = event.get().getButtonLanding().getName();
            if (buttonLanding.equals(Landding.L5.getName())) {
                this.noticeId = String.valueOf(event.get().getNoticeId());
            }
            if (buttonLanding.equals(Landding.L6.getName())) {
                this.landingUrl = event.get().getLandingUrl();
            }
            this.submit_popup = event.get().getSubmitPopup().name();
            if (submit_popup.equals(YN.Y.name())) {
                this.submit_popup_location = event.get().getSubmitPopupLocation().getName();
            }
        }
    }

    public EventSearchDetailResponseDto(Event event) {
        this.startDt = event.getCreateDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
        this.endDt = event.getEndDt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
        this.deviceGubn = event.getDeviceGubn().getValue();
        this.useYn = event.getUseYn().name();
        this.title = event.getTitle();
        this.content = event.getContent();
        this.files = event.getImgUrl().getImgUrl();
        this.buttonLanding = event.getButtonLanding().getValue();
        this.landingUrl = event.getLandingUrl();
        this.noticeId = String.valueOf(event.getNoticeId());
        this.popupShowOption = event.getPopupShowOption();
        this.submit_popup = event.getSubmitPopup().name();
        this.submit_popup_location = event.getSubmitPopupLocation().getValue();
    }
}
