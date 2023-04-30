package com.biseo.modules.admin.counsels.infra;

import com.biseo.modules.admin.counsels.web.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CounselsRepositoryExtension {
    List<CounselsResponseDto> findcounselsSearchQ (CounselsRequestDto counselsRequestDto, Pageable pageable);
    List<CounselsResponseDto> findcounselsItemSearchQ (CounselsRequestDto counselsRequestDto, Pageable pageable,String qnaItem);
    CountEvent findcount(CounselsRequestDto counselsRequestDto);
    List<CounselAnswerListResponseDto> findByAnswer(Long answerId);
    List<CounselsAresponseDto> findcounselsSearchA (CounselsRequestDto counselsRequestDto, Pageable pageable);
    List<CounselsAresponseDto> findcounselsItemSearchA (CounselsRequestDto counselsRequestDto, Pageable pageable,String qnaItem);
    List<TagResponseDto> findTag(TagRequestDto tagRequestDto, Pageable pageable);
    List<CounselsResponseDto> findTagCounselQ(TagRequestDto tagRequestDto,Pageable pageable);
    CountEvent findcount2(TagRequestDto tagRequestDto);
}
