package com.biseo.modules.admin.notice.application;


import com.biseo.common.web.ApiR;
import com.biseo.common.web.dto.response.enums.Role;
import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.account.application.AccountService;
import com.biseo.modules.account.domain.Account;
import com.biseo.modules.admin.notice.domain.Notice;
import com.biseo.modules.admin.notice.domain.enums.DeviceGubn;
import com.biseo.modules.admin.notice.infra.NoticeRepository;
import com.biseo.modules.admin.notice.web.dto.NoticeRequestDto;
import com.biseo.modules.admin.notice.web.dto.NoticeSearchDetailResponseDto;
import com.biseo.modules.admin.notice.web.dto.NoticeSearchRequestDto;
import com.biseo.modules.admin.notice.web.dto.NoticeSearchResponseDto;
import com.biseo.modules.auth.web.dto.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final AccountService accountService;

    public ResponseEntity<ApiR<?>> create(NoticeRequestDto noticeRequestDto, UserPrincipal me) {
        Account account = accountService.findOne(me.getId());
        Notice notice = new Notice(noticeRequestDto,account);
        if(noticeRequestDto.getUseYn().equals("Y")){
            notice.setUseYn(YN.Y);
        }else{
            notice.setUseYn(YN.N);
        }
        notice.setCreateDt(ZonedDateTime.now());
        noticeRepository.save(notice);
        return null;
    }

    public List<NoticeSearchResponseDto> noticeSearch(NoticeSearchRequestDto noticeSearchRequestDto, Pageable pageable) {
        List<NoticeSearchResponseDto> list = noticeRepository.findNoticeSearch(noticeSearchRequestDto, pageable);
        return list;
    }
    public NoticeSearchDetailResponseDto noticeSearchDetail(Long id, UserPrincipal me) {
        Optional<Notice> result = noticeRepository.findById(id);
        if(result.get().getAccount().getRole().equals(Role.ADMIN)) {
            NoticeSearchDetailResponseDto noticeSearchDetailResponseDto = new NoticeSearchDetailResponseDto(result);
            return noticeSearchDetailResponseDto;
        }else{
            return null;
        }
    }
    public NoticeSearchDetailResponseDto noticeSearchDetailfix(Long id, NoticeRequestDto noticeRequestDto, UserPrincipal me) {
        Notice notice = noticeRepository.getById(id);
        if(notice.getAccount().getUserId().equals(me.getUserId())) {
            notice.setTitle(noticeRequestDto.getTitle());
            notice.setDeviceGubn(DeviceGubn.valueOf(noticeRequestDto.getDeviceGubn()));
            notice.setUseYn(YN.valueOf(noticeRequestDto.getUseYn()));
            notice.setContent(noticeRequestDto.getContent());
            noticeRepository.save(notice);
            NoticeSearchDetailResponseDto noticeSearchDetailResponseDto = new NoticeSearchDetailResponseDto(notice);
            return noticeSearchDetailResponseDto;
        }else{
            return null;
        }
    }
    public ResponseEntity noticeSearchDelete(Long id, UserPrincipal me) {
        Notice notice = noticeRepository.getById(id);
        if(notice.getAccount().getUserId().equals(me.getUserId())) {
            notice.setUseYn(YN.N);
            noticeRepository.save(notice);
        }
        return null;
    }
}
