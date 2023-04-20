package com.onz.modules.counsel.web.dto.request.counsel;

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
public class CounselACreateRequest {

    @Size(min = 3,message = "3글자 이상 입력")
    @NotNull(message = "는 필수 값 입니다.")
    private String txt;
    @NotNull(message = "는 필수 값 입니다.")
    private Long parentCounselId;
    private Counsel parentCounsel;

    // 자동처리
//    @Convert(converter = GubnConverter.class)
//    private Gubn gubn;
//    private JobGubn jobGubn;
//    private QnaGubn qnaGubn=QnaGubn.Q;





}
