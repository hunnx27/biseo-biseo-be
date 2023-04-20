package com.onz.modules.counsel.web.dto.request.counselComment;

import com.onz.modules.counsel.domain.Counsel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CounselCommentCreateRequest {
    @Size(min = 3,message = "3글자 이상 입력")
    @NotNull(message = " 반드시 입력해야 합니다")
    private String txt;
    @NotNull(message = " 반드시 입력해야 합니다")
    private Long parentCounselId;
//    @NotNull(message = " 반드시 입력해야 합니다")
    private Counsel parentCounsel;

    // 자동처리
//    @Convert(converter = GubnConverter.class)
//    private Gubn gubn;
//    private JobGubn jobGubn;
//    private QnaGubn qnaGubn=QnaGubn.Q;





}
