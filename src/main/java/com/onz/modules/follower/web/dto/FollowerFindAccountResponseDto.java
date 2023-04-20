package com.onz.modules.follower.web.dto;

import com.onz.common.web.dto.response.enums.InterestCompany;
import com.onz.modules.company.domain.enums.EstablishmentType;
import com.onz.modules.follower.domain.Follower;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
public class FollowerFindAccountResponseDto {
    private EstablishmentType establishmentType;
    private InterestCompany interestCompany;
    private String officeName;
    private String zone;
    private Long jipyo;
    private ZonedDateTime createDt;

    public FollowerFindAccountResponseDto(Follower follower) {
        this.establishmentType = follower.getCompany().getEstablishmentType();
        this.interestCompany = follower.getCompany().getInterestCompany();
        this.officeName = follower.getCompany().getOfficeName();
        this.zone = follower.getCompany().getZonecode();
        this.createDt = follower.getCreatedAt();
    }

//
//    @Getter
//    private Long id;
//    private DeviceGubn deviceGubn;
//    private String getDeviceGubn(){
//        return deviceGubn != null ? deviceGubn.getName() : null;
//    }
//    @Getter
//    private String title;
//    @Getter
//    private String userId;
//
//    private ZonedDateTime createDt;
//    private String createDtName;
//    private YN useYn;
//    private String userYnStr;
//
//    public String getCreateDt(){
//        String createDtName=this.createDtName;
//        if(createDt!=null&&this.createDtName==null){
//            createDtName= this.createDt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh-mm-ss"));
//        }
//        return createDtName;
//    }
//    public String getUseYn(){
//        String userYnStr=this.userYnStr;
//        if(useYn!=null&&this.userYnStr==null){
//            if(useYn==YN.Y){
//                return userYnStr="사용";
//            }else{
//                return userYnStr="사용안함";
//            }
//        }
//        return userYnStr;
//    }
}
