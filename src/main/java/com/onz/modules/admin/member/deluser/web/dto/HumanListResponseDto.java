package com.onz.modules.admin.member.deluser.web.dto;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.modules.account.domain.enums.AuthProvider;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@NoArgsConstructor
public class HumanListResponseDto {

    private Gubn gubn;
    @Getter
    private String userId;
    private String gubnName;
    private AuthProvider snsType;
    private String snsTypeName;
    private ZonedDateTime createdAt;
    private ZonedDateTime lastedAt;

    public String getGubnName() {
        String gubnName = this.gubnName;
        if(this.gubn != null && this.gubnName==null){
            gubnName = this.gubn.getName();
        }
        return gubnName;
    }
    public String getCreatedAt() {
        String createdAtStr = "";
        if(this.createdAt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            createdAtStr = this.createdAt.format(formatter);
        }
        return createdAtStr;
    }

    public String getLastedAt() {
        String lastedAtStr = "";
        if(this.lastedAt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            lastedAtStr = this.lastedAt.format(formatter);
        }
        return lastedAtStr;
    }
    public String getSnsTypeName() {
        String snsTypeName = this.snsTypeName;
        if(this.snsType != null && this.snsTypeName==null){
            snsTypeName = this.snsType.getName();
        }
        return snsTypeName;
    }
}
