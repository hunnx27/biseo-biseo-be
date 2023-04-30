package com.biseo.modules.counsel.web.dto.request.counselComment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CounselCommentUpdateRequest {

    private String txt;

    // 자동처리
//    @Convert(converter = GubnConverter.class)
//    private Gubn gubn;
//    private JobGubn jobGubn;
//    private QnaGubn qnaGubn=QnaGubn.Q;





}
