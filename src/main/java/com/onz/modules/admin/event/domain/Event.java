package com.onz.modules.admin.event.domain;

import com.onz.common.util.dto.AttachDto;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.admin.event.domain.enums.Landding;
import com.onz.modules.admin.event.domain.enums.LanddingConverter;
import com.onz.modules.admin.event.domain.enums.PopupLocation;
import com.onz.modules.admin.event.domain.enums.PopupLocationConverter;
import com.onz.modules.admin.event.web.dto.EventCreateRequestDto;
import com.onz.modules.admin.notice.domain.enums.DeviceGubn;
import com.onz.modules.counsel.domain.embed.Images2;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //아이디

    private String title; // 이벤트명
    private String content; // 이벤트 내용

    @Embedded
    private Images2 imgUrl; //파일

    private ZonedDateTime startDt; // 시작일
    private ZonedDateTime endDt; //끝나는날
    private ZonedDateTime createDt; // Event input time

    @Enumerated(EnumType.STRING)
    private YN useYn = YN.Y; //사용여부 Y=노출 N=비노출

    @Convert(converter = LanddingConverter.class)
    private Landding buttonLanding= Landding.L0; // 버튼랜딩주소 -> Enum 참고

    private String facebookLandingUrl; //지금은 안쓰는듯 (페이스북 랜딩주소)
    private String popupShowOption; // popup노출 안함~ 1,7일 , 다시 보지 않기

    @Enumerated(EnumType.STRING)
    private YN submitPopup = YN.N; //이벤트 응모 노출/비노출

    @Convert(converter = PopupLocationConverter.class)
    private PopupLocation submitPopupLocation; //어디노출? 1~5 Enum 참고

    @Enumerated(EnumType.STRING)
    private DeviceGubn deviceGubn = DeviceGubn.P; //기기옵션

    private Long noticeId; // 버튼랜딩 원앤집 소식일때 -> 공지사항 id값
    private String landingUrl;

    public void setImgUrl(List<AttachDto> filelist) {
        Images2 embedImages = new Images2();
        if (filelist != null && filelist.size() > 0) {
            for (int i = 0; i < filelist.size(); i++) {
                AttachDto atttach = filelist.get(i);
                embedImages.setImgUrl(atttach.getSaveName());
            }
        }
        this.imgUrl = embedImages;
    }

    public Event(EventCreateRequestDto eventCreateRequestdto) {
        this.title = eventCreateRequestdto.getTitle();
        this.content = eventCreateRequestdto.getContent();
        this.popupShowOption = eventCreateRequestdto.getPopupShowOption();
        this.deviceGubn = DeviceGubn.valueOf(eventCreateRequestdto.getDeviceGubn());
    }

}
