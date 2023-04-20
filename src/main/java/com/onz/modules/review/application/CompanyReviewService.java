package com.onz.modules.review.application;

import com.onz.common.exception.CustomException;
import com.onz.common.util.dto.AttachDto;
import com.onz.common.web.dto.response.enums.ErrorCode;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import com.onz.modules.review.infra.CompanyReviewRepository;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.common.util.FileUtil;
import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.infra.dto.DistinctAddressResponse;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.company.web.dto.reponse.CompanyReviewListResponseDto;
import com.onz.modules.review.web.dto.CompanyReviewDetailResponseDto;
import com.onz.modules.review.web.dto.CompanyReviewRequestDto;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
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