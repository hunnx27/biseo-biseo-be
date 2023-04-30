package com.biseo.modules.admin.counsels.web.dto;

import com.biseo.common.web.dto.response.enums.Gubn;
import com.biseo.common.web.dto.response.enums.InterestCompany;
import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.counsel.domain.Counsel;
import com.biseo.modules.counsel.domain.embed.Images;
import com.biseo.modules.counsel.domain.enums.CounselState;
import com.biseo.modules.counsel.domain.enums.QnaGubn;
import com.biseo.modules.counsel.domain.enums.QnaItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class CounselsDetailResponseDto {
    private Gubn gubn;
    private String userIdq;
    private QnaItem qnaItem;
    private InterestCompany interestCompany;
    private String relatedZone;
    private String txt;
    private Images image;
    private String inputTage;
    private CounselState state;
    private ZonedDateTime createdBt;
    private YN open;
    private List<CounselAnswerListResponseDto> counselAnswerListResponseDtoList;

    public CounselsDetailResponseDto(Optional<Counsel> counsel,List<CounselAnswerListResponseDto> counselAnswerListResponseDtoList) {
        if(counsel.get().getQnaGubn()==QnaGubn.Q) {
            this.gubn = counsel.orElseThrow().getGubn();
            this.userIdq = counsel.orElseThrow().getAccount().getUserId();
            this.qnaItem = counsel.orElseThrow().getQnaItem();
            this.interestCompany = counsel.orElseThrow().getInterestCompany();
            this.relatedZone = counsel.orElseThrow().getRelatedZone();
            this.txt = counsel.orElseThrow().getTxt();
            this.createdBt = counsel.orElseThrow().getCreatedAt();
            this.image = counsel.orElseThrow().getImages();
            this.inputTage = counsel.orElseThrow().getInputTag();
            this.state = counsel.orElseThrow().getCounselState();
            this.open = counsel.orElseThrow().getOpenYn();
            this.counselAnswerListResponseDtoList=counselAnswerListResponseDtoList;
        }
//        if(counsel.get().getQnaGubn()== QnaGubn.Q){
//            if(!(counsel.get().getParentCounsel() == null)){
//                this.answer = counsel.orElseThrow().getParentCounsel().getTxt();
//                this.image2 = counsel.orElseThrow().getParentCounsel().getImages();
//                this.userIdA = counsel.orElseThrow().getParentCounsel().getAccount().getUserId();
//                this.createdAt = counsel.orElseThrow().getParentCounsel().getCreatedAt();
//                this.postState = counsel.orElseThrow().getParentCounsel().getCounselState();
//            }
//        }
    }
    //추천,신고
//업데이트문필요
}
