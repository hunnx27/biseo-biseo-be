package com.biseo.modules.admin.faq.infra;

import com.biseo.modules.admin.faq.web.dto.FaqSearchRequestDto;
import com.biseo.modules.admin.faq.web.dto.FaqSearchResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FaqRepositoryExtension {
    List<FaqSearchResponseDto> findFaqSearch(FaqSearchRequestDto faqSearchRequestDto, Pageable pageable);
}
