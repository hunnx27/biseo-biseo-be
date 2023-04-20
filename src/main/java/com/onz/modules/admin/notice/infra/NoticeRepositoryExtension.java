package com.onz.modules.admin.notice.infra;

import com.onz.modules.admin.counsels.web.dto.CounselsResponseDto;
import com.onz.modules.admin.notice.web.dto.NoticeSearchRequestDto;
import com.onz.modules.admin.notice.web.dto.NoticeSearchResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeRepositoryExtension {
    List<NoticeSearchResponseDto> findNoticeSearch(NoticeSearchRequestDto noticeSearchRequestDto, Pageable pageable);
}
