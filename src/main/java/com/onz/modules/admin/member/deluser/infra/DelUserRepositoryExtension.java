package com.onz.modules.admin.member.deluser.infra;

import com.onz.modules.admin.member.deluser.web.dto.DelUserListResponseDto;
import com.onz.modules.admin.member.deluser.web.dto.DelUserRequestDto;
import com.onz.modules.admin.member.deluser.web.dto.DelUserResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DelUserRepositoryExtension {
    List<DelUserListResponseDto> findByDelUser(DelUserRequestDto delUserRequestDto, Pageable pageable);

    List<DelUserListResponseDto> findByDelUser(DelUserRequestDto delUserRequestDto);

    DelUserResponse findByDelUserDetail(Long id);
}
