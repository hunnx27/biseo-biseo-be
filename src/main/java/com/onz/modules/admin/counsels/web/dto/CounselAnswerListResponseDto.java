package com.onz.modules.admin.counsels.web.dto;

import com.onz.modules.counsel.domain.embed.Images;
import com.onz.modules.counsel.domain.enums.CounselState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Setter
@NoArgsConstructor
public class CounselAnswerListResponseDto {
    //--답변 --//
    @Getter
    private String txt;
    @Getter
    private Images images;
    @Getter
    private String userId;
    private ZonedDateTime createdAt;
    private CounselState counselState;

    public String getCreatedAt() {
        String createdAtStr = "";
        if(this.createdAt!=null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            createdAtStr = this.createdAt.format(formatter);
        }
        return createdAtStr;
    }

    public String getCounselState() {
        String postState2Str = "";
        if(this.counselState!=null){
            postState2Str = String.valueOf(this.counselState);
        }
        return postState2Str;
    }
}
