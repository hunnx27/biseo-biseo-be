package com.onz.modules.admin.event.web.dto;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.admin.notice.domain.enums.DeviceGubn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
public class EventSearchResponseDto {
    @Getter
    private Long id;
    private DeviceGubn deviceGubn;
    @Getter
    private String title;
    @Getter
    private String content;
    private ZonedDateTime startDt;
    private ZonedDateTime endDt;
    private ZonedDateTime createDt;
    private YN useYn;
    private YN submitPopup;
    private String submitPopupStr;
    private String endDtName;
    private String startDtName;
    private String createDtName;
    private String userYnStr;
    private String deviceName;

    public String getDevicegubn(){
        String deviceName= this.deviceName;
        if(deviceGubn!=null&& this.deviceName==null){
            deviceName=deviceGubn.getName();
        }
        return deviceName;
    }

    public String getCreateDt() {
        String createDtName = this.createDtName;
        if (createDt != null && this.createDtName == null) {
            createDtName = this.createDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
        }
        return createDtName;
    }

    public String getStartDt() {
        String startDtName = this.startDtName;
        if (startDt != null && this.startDtName == null) {
            startDtName = this.startDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
        }
        return startDtName;
    }


    public String getEndDt() {
        String endDtName = this.endDtName;
        if (endDt != null && this.endDtName == null) {
            endDtName = this.endDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
        }
        return endDtName;
    }


    public String getUseYn() {
        String userYnStr = this.userYnStr;
        if (useYn != null && this.userYnStr == null) {
            if (useYn == YN.Y) {
                return userYnStr = "사용";
            } else {
                return userYnStr = "사용안함";
            }
        }
        return userYnStr;
    }

    public String getSubmitPopup() {
        String submitPopupStr = this.submitPopupStr;
        if (submitPopup != null && this.submitPopupStr == null) {
            if (submitPopup == YN.Y) {
                return submitPopupStr = "사용";
            } else {
                return submitPopupStr = "사용안함";
            }
        }
        return submitPopupStr;
    }
}
