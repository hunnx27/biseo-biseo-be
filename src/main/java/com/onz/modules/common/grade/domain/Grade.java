package com.onz.modules.common.grade.domain;

import com.onz.common.util.dto.AttachDto;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.common.grade.web.dto.GradeCreateRequestDto;
import com.onz.modules.review.domain.embed.Images;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grade;

    @Size(max=200)
    private String iconUrl;

    private Long startTot;

    private Long endTot;

    private Long replayRate;

    private Long replyCnt;
    @Enumerated(EnumType.STRING)
    private YN useYn;

    private ZonedDateTime createDt;

    public void setImages(List<AttachDto> filelist){
        if(filelist!=null && filelist.size()>0){
            AttachDto atttach = filelist.get(0);
            this.iconUrl = atttach.getSaveName();
        }
    }
    public Grade(GradeCreateRequestDto gradeCreateRequestDto) {
        this.grade = gradeCreateRequestDto.getGrade();
        this.startTot = gradeCreateRequestDto.getStartTot();
        this.endTot = gradeCreateRequestDto.getEndTot();
        this.replayRate = gradeCreateRequestDto.getReplayRate();
        this.replyCnt = gradeCreateRequestDto.getReplyCnt();
        this.useYn = gradeCreateRequestDto.getUseYn();
    }


}
