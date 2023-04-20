package com.onz.modules.admin.member.deluser.infra;

import com.onz.modules.admin.member.deluser.web.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HumanRepositoryExtension {
    List<HumanListResponseDto> findByHumanList(HumanListRequestDto humanListRequestDto, Pageable pageable);

    List<HumanListResponseDto> findByHumanUser(HumanListRequestDto humanListRequestDto);

    HumanResponseDto findByHumanDetail(Long id);
}
