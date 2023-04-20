package com.onz.modules.counsel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onz.common.domain.BaseEntity;
import com.onz.common.web.dto.response.enums.*;
import com.onz.common.util.dto.AttachDto;
import com.onz.modules.account.domain.Account;
import com.onz.modules.common.pointHistory.domain.PointHistory;
import com.onz.modules.counsel.domain.embed.Images;
import com.onz.modules.counsel.domain.enums.CounselState;
import com.onz.modules.counsel.domain.enums.JobGubn;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import com.onz.modules.counsel.domain.enums.QnaItem;
import com.onz.modules.counsel.web.dto.request.counsel.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Counsel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = GubnConverter.class)
    private Gubn gubn = Gubn.PARENT;
    @Enumerated(EnumType.STRING)
    private JobGubn jobGubn;
    @Enumerated(EnumType.STRING)
    private QnaGubn qnaGubn;
    @Enumerated(EnumType.STRING)
    private CounselState counselState;
    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private ZonedDateTime counselStateAt;
    @Enumerated(EnumType.STRING)
    private YN openYn;
    @Enumerated(EnumType.STRING)
    private YN shortOpenYn;
    @Convert(converter = InterestCompanyConverter.class)
    private InterestCompany interestCompany; // 관심 기관
    @Enumerated(EnumType.STRING)
    private QnaItem qnaItem;

    private String relatedZone; //관련된 지역코드(시)

    private long reportCnt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;
    @Size(max=1000)
    private String txt;
    @Embedded
    private Images images;

    private String inputTag;

    private String commentTxt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName="id", name = "parent_counsel_id")
    private Counsel parentCounsel;

    public Counsel() {
    }

    @Builder
    public Counsel(CounselQCreateRequest req, Account account) {
        this.account = account;
        this.gubn = account.getGubn();
        this.jobGubn = JobGubn.J;
        this.qnaGubn = QnaGubn.Q;
        this.counselState = CounselState.R;
        this.openYn = YN.Y;
        this.shortOpenYn = req.getShortOpenYn();
        this.interestCompany = req.getInterestCompanyName();
        this.qnaItem = req.getQnaItem();
        this.txt = req.getTxt();
        this.relatedZone = req.getRelatedZone();
        String inputTag = this.qnaItem.getTag();
        if(req.getAddedTagData()!=null && !"".equals(req.getAddedTagData())){
            String[] tagArr = req.getAddedTagData().split(",");
            for(int i=0; i<tagArr.length; i++){
                inputTag += " #" + tagArr[i];
            }

        }
        this.inputTag = inputTag;
    }

    public void updateCounsel(CounselQUpdateRequest req, Account account){
//        this.account = account;
//        this.gubn = account.getGubn();
//        this.jobGubn = JobGubn.J;
//        this.qnaGubn = QnaGubn.Q;
//        this.counselState = CounselState.R;
        this.openYn = YN.Y;
        this.shortOpenYn = req.getShortOpenYn();
        this.interestCompany = req.getInterestCommpanyName();
        this.relatedZone = req.getRelatedZone();
        this.txt = req.getTxt();

        // 기존 QnaItemTag 백업
        List<String> tempQnaItemTagList = this.qnaItem!=null?
                Arrays.stream(this.qnaItem.getTag().split(" ")).map(tag->tag.replaceAll("#","")).collect(Collectors.toList())
                : new ArrayList<>();
        // 신규 QnsItem 업데이트
        this.qnaItem = req.getQnaItem();
        String inputTag = this.qnaItem.getTag();
        if(req.getAddedTagData()!=null && !"".equals(req.getAddedTagData())){
            String[] tagArr = req.getAddedTagData().split(",");
            // 기존태그 필터링
            String[] filteredArr = Arrays.stream(tagArr).filter(tag-> !tempQnaItemTagList.contains(tag)).toArray(String[]::new);
            //신규
            for(int i=0; i<filteredArr.length; i++){
                inputTag += " #" + filteredArr[i];
            }
        }
        this.inputTag = inputTag;
    }

    public void updateAnswerCounsel(CounselAUpdateRequest req, Account account){
//        this.account = account;
//        this.gubn = account.getGubn();
//        this.jobGubn = JobGubn.J;
//        this.qnaGubn = QnaGubn.Q;
//        this.counselState = CounselState.R;
        this.openYn = YN.Y;
        this.txt = req.getTxt();
    }

    public void updateAnswerAdopt(CounselAAdoptRequest req){
        this.counselState = CounselState.A;
        this.commentTxt = req.getCommentTxt();
    }
    public void updateCounselAdopted(){
        this.counselState = CounselState.A;
    }

    @Builder
    public Counsel(CounselACreateRequest req, Account account) {
        this.account = account;
        this.gubn = account.getGubn();
        this.jobGubn = JobGubn.J;
        this.qnaGubn = QnaGubn.A;
        this.counselState = CounselState.R;
        this.openYn = YN.Y;
        this.shortOpenYn = YN.N;
        this.txt = req.getTxt();
        this.parentCounsel = req.getParentCounsel();
    }

    public void setImages(List<AttachDto> filelist){
                        Images embedImages = new Images();
                        if(filelist!=null && filelist.size()>0){
                            for(int i=0; i<filelist.size(); i++){
                                AttachDto atttach = filelist.get(i);
                                switch(i){
                                    case 0: embedImages.setImage1(atttach.getSaveName());break;
                                    case 1: embedImages.setImage2(atttach.getSaveName());break;
                                    case 2: embedImages.setImage3(atttach.getSaveName());break;
                                    case 3: embedImages.setImage4(atttach.getSaveName());break;
                                    case 4: embedImages.setImage5(atttach.getSaveName());break;
                }
            }
            this.images = embedImages;
        }
    }
}
