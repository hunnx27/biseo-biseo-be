package com.onz.modules.review.web.dto;

import com.onz.common.domain.BaseEntity;
import com.onz.common.web.dto.response.enums.YN;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InterviewRequestDto extends BaseEntity {

    //1 page
    private List<Interview> interviews;
//    private Long interviewOrder; // 인터뷰 질문 내림차순정렬
//    private Long reviewOrder;
    @NotNull(message="반드시 입력해야 합니다.")
    private Long companyId;
    @NotNull(message="반드시 입력해야 합니다.")
    private Long workExp;
    private String item_1; //모의고사
    @NotNull(message="반드시 입력해야 합니다.")
    private YN item_2; //필기시험
    @NotNull(message="반드시 입력해야 합니다.")
    private YN item_3; //인적성검사
    private String item_4; //면접결과
    private String item_5; //난이도
    @NotNull(message="반드시 입력해야 합니다.")
    private YN item_6; //모의수업 Y/N
    private YN workExpOpenYn;


    @Getter
    @Setter
    static public class Interview{
        String q;
        String a;
        String Aq;
        String Aa;
    }
}



// for -> q 기준으로 i 값 answer -> map을 묶어서
