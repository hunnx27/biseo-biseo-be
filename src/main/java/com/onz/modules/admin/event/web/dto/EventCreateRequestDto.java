package com.onz.modules.admin.event.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class EventCreateRequestDto {
    @NotNull(message = "반드시 입력해야 합니다")
    private String startDt;
    @NotNull(message = "반드시 입력해야 합니다")
    private String endDt;
    @NotNull(message = "반드시 입력해야 합니다")
    private String deviceGubn;
    @NotNull(message = "반드시 입력해야 합니다")
    private String useYn;
    @NotNull(message = "반드시 입력해야 합니다")
    private String title;
    @NotNull(message = "반드시 입력해야 합니다")
    private String content;
    private String buttonLanding;
    @NotNull(message = "반드시 입력해야 합니다")
    private String popupShowOption;
    @NotNull(message = "반드시 입력해야 합니다")
    private String submit_popup;
    private String submit_popup_location;
    @NotNull(message = "반드시 입력해야 합니다")
    private Long noticeId;
    private String landingUrl;
}
