package com.onz.modules.review.application;

import com.onz.common.exception.CustomException;
import com.onz.common.web.dto.response.enums.ErrorCode;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.web.dto.AmtRequestDto;
import com.onz.modules.review.web.dto.AmtReviewDetailResponseDto;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import com.onz.modules.review.web.dto.YearAmtResponseDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.infra.dto.DistinctAddressResponse;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.company.web.dto.reponse.YearAmtAvgResponseDto;
import com.onz.modules.company.web.dto.reponse.YearAmtListResponseDto;
import com.onz.modules.review.infra.AmtReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class AmtReviewService {

    private final AmtReviewRepository amtReviewRepository;
    private final AccountRepository accountRepository;
    private final CompanyRepository companyRepository;
    private final AccountService accountService;
    private final AddressRepository addressRepository;
    Long one = Long.valueOf(0);
    Long count = Long.valueOf(0);

    public YearAmtReview create(AmtRequestDto amtRequestDto, UserPrincipal me) {
        Long companyId = amtRequestDto.getCompanyId();
        Account account = accountService.findOne(me.getId());
        Company company = companyRepository.findById(companyId).orElse(null);
        YearAmtReview yearAmtReview = new YearAmtReview(amtRequestDto, company, account);
        amtReviewRepository.save(yearAmtReview);
        accountService.createMyPointHistories(account, PointTable.AMT_REGIST);
        return yearAmtReview;
    }

    public YearAmtAvgResponseDto amtReviewAvgList(@PathVariable Long id) {
        List<YearAmtListResponseDto> list = amtReviewRepository.findByCompanyId(id);
        List<YearAmtResponseDto> array = list.stream().map(res->{
            YearAmtResponseDto aaa = new YearAmtResponseDto(res.getAmt());
            one +=res.getAmt();
            count+=1;
            log.debug(String.valueOf(one));
            return aaa;
        }).collect(Collectors.toList());
        long total = count!=0 ? one/count : 0;
        long min = list.stream().mapToLong(rv -> rv.getAmt()).min().orElse(0);
        long max = list.stream().mapToLong(rv -> rv.getAmt()).max().orElse(0);
        YearAmtAvgResponseDto yearAmtAvgResponseDto = YearAmtAvgResponseDto.builder()
                .minAmt(min)
                .maxAmt(max)
                .totalAmt(total)
                .build();
        return yearAmtAvgResponseDto;
    }

    public List<YearAmtListResponseDto> amtReviewList(FindEstaRequestDto findEstaRequestDto, Pageable pageable) {
        List<YearAmtListResponseDto> list = amtReviewRepository.ListAmt(findEstaRequestDto,pageable);
        List<YearAmtListResponseDto> array = list.stream().map(res -> {
            String[] one = res.getEtcItems().split(",");
            String[] two = res.getEtcAmt().split(",");
            int total = 0;
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < one.length; i++) {
                String key = one[i];
                String value = two[i];
                map.put(key, value);
                //value String 분기처리
                total += Integer.parseInt(value);

                switch (key) {
                    case "1":
                        res.setImpCost(value);
                        break;
                    case "2":
                        res.setWorkCost(value);
                        break;
                    case "3":
                        res.setAddCost(value);
                        break;
                    case "4":
                        res.setEtcCost(value);
                        break;
                }
                res.setTotalCost((long) total);
            }
            List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(res.zoneCode);
            if (addressList.size() > 0) {
                DistinctAddressResponse address = addressList.get(0);
                String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
                res.setMapsidogunguName(mapsidogunguName);
            }
            return res;
        }).collect(Collectors.toList());
        return array;
    }
    public AmtReviewDetailResponseDto amtReviewDetail(Long companyId, Long id) {
        YearAmtReview yearAmtReview = amtReviewRepository.findById(id).orElse(null);
        if(!(yearAmtReview != null ? yearAmtReview.getCompany().getId() : null).equals(companyId)){
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        AmtReviewDetailResponseDto result = new AmtReviewDetailResponseDto(yearAmtReview);
        return result;
    }
}