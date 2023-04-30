package com.biseo.modules.admin.faq.application;


import com.biseo.common.web.dto.response.enums.YN;
import com.biseo.modules.admin.faq.domian.Faq;
import com.biseo.modules.admin.faq.domian.enums.Category;
import com.biseo.modules.admin.faq.infra.FaqRepository;
import com.biseo.modules.admin.faq.web.dto.FaqCreateRequestDto;
import com.biseo.modules.admin.faq.web.dto.FaqSearchDetailResponseDto;
import com.biseo.modules.admin.faq.web.dto.FaqSearchRequestDto;
import com.biseo.modules.admin.faq.web.dto.FaqSearchResponseDto;
import com.biseo.modules.admin.notice.domain.enums.DeviceGubn;
import com.biseo.modules.auth.web.dto.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FaqService {
    private final FaqRepository faqRepository;
    public Faq create(FaqCreateRequestDto faqCreateRequestDto, UserPrincipal me) {
        Faq faq = new Faq(faqCreateRequestDto);
        faq.setUserId(me.getUserId());
        faq.setCreateDt(ZonedDateTime.now());
        faqRepository.save(faq);
        return faq;
    }
    public List<FaqSearchResponseDto> faqSearch(FaqSearchRequestDto faqSearchRequestDto, Pageable pageable) {
        List<FaqSearchResponseDto> list = faqRepository.findFaqSearch(faqSearchRequestDto, pageable);
        return list;
    }

    public FaqSearchDetailResponseDto faqSearchDetail(Long id, UserPrincipal me) {
        Optional<Faq> result = faqRepository.findById(id);
        FaqSearchDetailResponseDto faqSearchDetailResponseDto = new FaqSearchDetailResponseDto(result);
            return faqSearchDetailResponseDto;
    }
    public FaqSearchDetailResponseDto faqSearchDetailfix(Long id, FaqCreateRequestDto faqCreateRequestDto, UserPrincipal me) {
        Faq faq = faqRepository.getById(id);
        if(faq.getUserId().equals(me.getUserId())) {
            faq.setTitle(faqCreateRequestDto.getTitle());
            faq.setDeviceGubn(DeviceGubn.valueOf(faqCreateRequestDto.getDeviceGubn()));
            faq.setUseYn(YN.valueOf(faqCreateRequestDto.getUseYn()));
            faq.setContent(faqCreateRequestDto.getContent());
            faq.setCategory(Category.valueOf(faqCreateRequestDto.getCategory()));
            faqRepository.save(faq);
            FaqSearchDetailResponseDto faqSearchDetailResponseDto = new FaqSearchDetailResponseDto(faq);
            return faqSearchDetailResponseDto;
        }else{
            return null;
        }
    }
}
