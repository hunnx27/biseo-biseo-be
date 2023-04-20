package com.onz.modules.admin.faq.infra;

import com.onz.modules.admin.event.web.dto.EventSearchRequestDto;
import com.onz.modules.admin.event.web.dto.EventSearchResponseDto;
import com.onz.modules.admin.faq.web.dto.FaqSearchRequestDto;
import com.onz.modules.admin.faq.web.dto.FaqSearchResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqRepositoryExtension {
    List<FaqSearchResponseDto> findFaqSearch(FaqSearchRequestDto faqSearchRequestDto, Pageable pageable);
}
