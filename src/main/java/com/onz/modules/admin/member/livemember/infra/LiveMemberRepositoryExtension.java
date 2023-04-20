package com.onz.modules.admin.member.livemember.infra;

import com.onz.modules.admin.member.livemember.web.dto.*;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LiveMemberRepositoryExtension {

    List<LiveMemberResponseDto> findByLiveMember(LiveMemberRequestDto liveMemberRequestDto, Pageable pageable);
    List<LiveMemberResponseDto> findByLiveMember(LiveMemberRequestDto liveMemberRequestDto);
    LiveMemberResponseDto findByLiveMemberTotal(LiveMemberRequestDto liveMemberRequestDto);
    JPQLQuery<Long> findCountMember(LiveMemberRequestDto liveMemberRequestDto);
//    List<LiveMemberDetailResponse> findByAccountDetail(Long id);
    LiveMemberDetailResponse findByAccountDetail(Long id);
    LiveMemberPointResponse findByAccountPointDetail(Long id);
    List<LiveMemberPointListResponse> findByAccountPointList(Long id, Pageable pageable);
}

