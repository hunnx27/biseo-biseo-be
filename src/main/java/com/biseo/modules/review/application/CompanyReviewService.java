package com.biseo.modules.review.application;

import com.biseo.common.exception.CustomException;
import com.biseo.common.util.dto.AttachDto;
import com.biseo.common.web.dto.response.enums.ErrorCode;
import com.biseo.modules.account.application.AccountService;
import com.biseo.modules.account.domain.Account;
import com.biseo.modules.common.pointHistory.domain.enums.PointTable;
import com.biseo.modules.review.infra.CompanyReviewRepository;
import com.biseo.modules.auth.web.dto.UserPrincipal;
import com.biseo.common.util.FileUtil;
import com.biseo.modules.common.address.infra.AddressRepository;
import com.biseo.modules.common.address.infra.dto.DistinctAddressResponse;
import com.biseo.modules.company.domain.Company;
import com.biseo.modules.company.infra.CompanyRepository;
import com.biseo.modules.review.domain.CompanyReview;
import com.biseo.modules.company.web.dto.reponse.CompanyReviewListResponseDto;
import com.biseo.modules.review.web.dto.CompanyReviewDetailResponseDto;
import com.biseo.modules.review.web.dto.CompanyReviewRequestDto;
import com.biseo.modules.review.web.dto.FindEstaRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyReviewService {
    private final CompanyReviewRepository companyReviewRepository;
    private final CompanyRepository companyRepository;
    private final AccountService accountService;
    private final AddressRepository addressRepository;
    private final FileUtil fileUtil;

    public CompanyReview create(CompanyReviewRequestDto companyReviewRequestDto, UserPrincipal me ,List<MultipartFile> files) {
        Long companyId = companyReviewRequestDto.getCompanyId();
        Account account = accountService.findOne(me.getId());
        Company company = companyRepository.findById(companyId).orElse(null);
        CompanyReview companyReview = new CompanyReview(companyReviewRequestDto, company, account);
        CompanyReview saved = companyReviewRepository.save(companyReview);
        accountService.createMyPointHistories(account, PointTable.REVIEW_REGIST);
        // Image File Upload
        if (files != null && files.size() > 0) {
            try {
                List<AttachDto> rs = fileUtil.uploadFiles(files, saved.getId());
                saved.setImages(rs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return companyReview;
    }

    public List<CompanyReviewListResponseDto> companyReviewList(FindEstaRequestDto findEstaRequestDto,Pageable pageable) {
        List<CompanyReviewListResponseDto> list = companyReviewRepository.listCompanyReview(findEstaRequestDto,pageable);
        List<CompanyReviewListResponseDto> array = list.stream().map(res -> {
//           String q_1 =(interviewReviewItemRepository.getById(res.getId()).getInterviewQ());
//            res.setQ_1(q_1);
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

    public CompanyReviewDetailResponseDto companyReviewDetail(Long id,Long companyId) {
        CompanyReview companyReview = companyReviewRepository.findById(id).orElse(null);
        if(!(companyReview != null ? companyReview.getCompany().getId() : null).equals(companyId)){
            throw new CustomException(ErrorCode.NOT_FOUND);
        }
        CompanyReviewDetailResponseDto result = new CompanyReviewDetailResponseDto(companyReview);
        return result;
    }

}