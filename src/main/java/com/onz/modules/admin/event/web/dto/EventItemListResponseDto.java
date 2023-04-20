package com.onz.modules.admin.event.web.dto;

import com.onz.modules.admin.event.domain.EventItem;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class EventItemListResponseDto {
    public EventItemListResponseDto(EventItem eventItem) {
        this.userId = eventItem.getAccount().getUserId();
        this.phone = eventItem.getPhone();
        this.answer = eventItem.getAnswer();
        this.chu = eventItem.getChu();
        this.createDt = eventItem.getCreateDt();
    }

    private String userId;
    private String phone;
    private String answer;
    private String chu;
    private ZonedDateTime createDt;
}
