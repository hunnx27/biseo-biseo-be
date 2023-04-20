package com.onz.modules.admin.notice.web.dto;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.admin.notice.domain.enums.DeviceGubn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
public class NoticeSearchResponseDto {
    @Getter
    private Long id;
    private DeviceGubn deviceGubn;
    private String deviceName;
    public String getDevicegubn(){
        String deviceName= this.deviceName;
        if(deviceGubn!=null&& this.deviceName==null){
            deviceName=deviceGubn.getName();
        }
        return deviceName;
    }
    @Getter
    private String title;
    @Getter
    private String userId;

    private ZonedDateTime createDt;
    private String createDtName;
    private YN useYn;
    private String userYnStr;

    public String getCreateDt(){
        String createDtName=this.createDtName;
        if(createDt!=null&&this.createDtName==null){
            createDtName= this.createDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
        }
        return createDtName;
    }
    public String getUseYn(){
        String userYnStr=this.userYnStr;
        if(useYn!=null&&this.userYnStr==null){
            if(useYn==YN.Y){
                return userYnStr="사용";
            }else{
                return userYnStr="사용안함";
            }
        }
        return userYnStr;
    }
}
