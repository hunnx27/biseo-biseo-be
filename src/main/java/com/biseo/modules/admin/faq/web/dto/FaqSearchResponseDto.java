package com.biseo.modules.admin.faq.web.dto;

import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.admin.faq.domian.enums.Category;
import com.biseo.modules.admin.notice.domain.enums.DeviceGubn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
public class FaqSearchResponseDto {

    private DeviceGubn deviceGubn;
    @Getter
    private String title;

    private Category category;
    @Getter
    private String userId;
    private ZonedDateTime createDt;
    private String createDtName;
    private YN useYn;
    private String userYnStr;
    private String deviceName;
    private String categoryName;

    public String getDevicegubn(){
        String deviceName= this.deviceGubn.getName();
        if(deviceGubn!=null&& this.deviceName==null){
            deviceName=deviceGubn.getName();
        }
        return deviceName;
    }

    public String getCreateDtName() {
        String createDtName = this.createDtName;
        if (createDt != null && this.createDtName == null) {
            createDtName = this.createDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
        }
        return createDtName;
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

    public String getCategory(){
        String categoryName = this.categoryName;
        if(category!=null&& this.categoryName==null){
            categoryName=category.getName();
        }
        return categoryName;
    }
}
