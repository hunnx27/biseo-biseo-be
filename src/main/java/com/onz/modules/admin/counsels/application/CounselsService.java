package com.onz.modules.admin.counsels.application;

import com.onz.common.exception.CustomException;
import com.onz.modules.admin.counsels.infra.CounselsRepository;
import com.onz.modules.admin.counsels.web.dto.*;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.domain.enums.QnaGubn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CounselsService {
    private final CounselsRepository counselsRepository;

    public CounselsQWrapResponseDto counselsSearchQ(CounselsRequestDto counselsRequestDto, Pageable pageable) throws CustomException {
        // 모든 리뷰 조회하는데 state가 W인 항목만
        List<CounselsResponseDto> Result = counselsRepository.findcounselsSearchQ(counselsRequestDto, pageable);
        CountEvent countEvent = eventCount(counselsRequestDto);
        CounselsQWrapResponseDto counselsWrapResponseDto = new CounselsQWrapResponseDto(countEvent, Result);
        return counselsWrapResponseDto;
    }

    public CountEvent eventCount(CounselsRequestDto counselsRequestDto) {
        CountEvent countEvent = counselsRepository.findcount(counselsRequestDto);
        return countEvent;
    }
    public CountEvent eventCount2(TagRequestDto tagRequestDto) {
        CountEvent countEvent = counselsRepository.findcount2(tagRequestDto);
        return countEvent;
    }


    public List<CounselsResponseDto> counselsQItem(CounselsRequestDto counselsRequestDto, String qnaItem, Pageable pageable) {
        List<CounselsResponseDto> list = counselsRepository.findcounselsItemSearchQ(counselsRequestDto, pageable, qnaItem);
        return list;
    }

    public CounselsDetailResponseDto counselQItem(Long id) {
        Optional<Counsel> result = counselsRepository.findById(id);
        CounselsDetailResponseDto counselsDetailResponseDto = new CounselsDetailResponseDto();
        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (result.get().getQnaGubn().equals(QnaGubn.Q)) {
            List<CounselAnswerListResponseDto> answer = counselsRepository.findByAnswer(id);
            counselsDetailResponseDto = new CounselsDetailResponseDto(result, answer);
        }
        return counselsDetailResponseDto;
    }

    public CounselsAWrapResponseDto counselsSearchA(CounselsRequestDto counselsRequestDto, Pageable pageable) throws CustomException {
        // 모든 리뷰 조회하는데 state가 W인 항목만
        List<CounselsAresponseDto> Result = counselsRepository.findcounselsSearchA(counselsRequestDto, pageable);
        CountEvent countEvent = eventCount(counselsRequestDto);
        CounselsAWrapResponseDto counselsAWrapResponseDto = new CounselsAWrapResponseDto(countEvent, Result);
        return counselsAWrapResponseDto;

    }

    public List<CounselsAresponseDto> counselsAItem(CounselsRequestDto counselsRequestDto, String qnaItem, Pageable pageable) {
        List<CounselsAresponseDto> list = counselsRepository.findcounselsItemSearchA(counselsRequestDto, pageable, qnaItem);
        return list;
    }

    public List<TagResponseDto> counselTag(TagRequestDto tagRequestDto, Pageable pageable) {
        List<TagResponseDto> list = counselsRepository.findTag(tagRequestDto, pageable);
        return list;
    }
    public CounselsQWrapResponseDto tagCounselQ(TagRequestDto tagRequestDto, Pageable pageable) throws CustomException {
        // 모든 리뷰 조회하는데 state가 W인 항목만
        List<CounselsResponseDto> Result = counselsRepository.findTagCounselQ(tagRequestDto, pageable);
        CountEvent countEvent = eventCount2(tagRequestDto);
        CounselsQWrapResponseDto counselsWrapResponseDto = new CounselsQWrapResponseDto(countEvent, Result);
        return counselsWrapResponseDto;
    }
    public CounselADetailResponseDto counselAItem(Long id) {
        Optional<Counsel> result = counselsRepository.findById(id);
        CounselADetailResponseDto counselADetailResponseDto = new CounselADetailResponseDto();
        if (result.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (result.get().getQnaGubn().equals(QnaGubn.A)) {
            counselADetailResponseDto = new CounselADetailResponseDto(result);
        }
        return counselADetailResponseDto;
    }
}
