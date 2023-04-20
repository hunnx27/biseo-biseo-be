package com.onz.modules.counsel.web.dto.response.counsel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onz.common.util.TimeUtil;
import com.onz.modules.account.domain.Account;
import com.onz.modules.counsel.domain.Counsel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselAnswerDetailResponse {

    private Long id;
    private String txt;
    private List<String> images = new ArrayList<>();

    public CounselAnswerDetailResponse(Counsel counsel) {
        this.id = counsel.getId();
        this.txt = counsel.getTxt();
        if(counsel.getImages()!=null){
            if(counsel.getImages().getImage1()!=null) images.add(counsel.getImages().getImage1());
            if(counsel.getImages().getImage2()!=null) images.add(counsel.getImages().getImage2());
            if(counsel.getImages().getImage3()!=null) images.add(counsel.getImages().getImage3());
            if(counsel.getImages().getImage4()!=null) images.add(counsel.getImages().getImage4());
            if(counsel.getImages().getImage5()!=null) images.add(counsel.getImages().getImage5());
        }
    }
}
