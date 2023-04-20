package com.onz.modules.company.application;

import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.common.address.infra.dto.DistinctAddressResponse;
import com.onz.modules.company.application.util.AggregateCompany;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.web.dto.reponse.CompanyDetailResponse;
import com.onz.modules.company.web.dto.reponse.CompanyJipyoResponse;
import com.onz.modules.company.web.dto.reponse.CompanySearchResponse;
import com.onz.modules.company.web.dto.request.CompanyCreateRequest;
import com.onz.modules.company.web.dto.request.CompanySearchRequest;
import com.onz.modules.company.web.dto.request.CompanyUpdateRequest;
import com.onz.modules.company.infra.CompanyRepository;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.infra.CompanyReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final AddressRepository addressRepository;
    private final CompanyReviewRepository companyReviewRepository;

    public Page<Company> list(CompanySearchRequest searchRequest) {
        return companyRepository.list(searchRequest);
    }

    public Company create(CompanyCreateRequest createRequest) {
        Company company = new Company(createRequest);
        companyRepository.save(company);
        return company;
    }

    public Company update(Long id, CompanyUpdateRequest updateRequest) {
        Company company = companyRepository.findById(id).orElseThrow();
        companyRepository.update(id,updateRequest);
        return company;
    }

    public CompanyDetailResponse findOne(Long id) {
        Company company = companyRepository.findById(id).orElseThrow();
        CompanyDetailResponse rs = new CompanyDetailResponse(company);
        List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(company.getZonecode());
        if (addressList.size() > 0) {
            DistinctAddressResponse address = addressList.get(0);
            String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
            rs.setMapsidogunguName(mapsidogunguName);
        }
        return rs;
    }

    public CompanyJipyoResponse findOneJipyo(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        CompanyJipyoResponse result = new CompanyJipyoResponse();
        if(company!=null){
            List<CompanyReview> reviews = companyReviewRepository.listCompanyReviewByCompanyId(company.getId());
            Map<String,Object> rsTot = new HashMap<>();
            rsTot.put("기관명", company.getOfficeName());
            AggregateCompany agg = reviews.stream().collect(AggregateCompany::new, AggregateCompany::add, AggregateCompany::merge);

            result = new CompanyJipyoResponse(company, agg);
            List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(company.getZonecode());
            if (addressList.size() > 0) {
                DistinctAddressResponse address = addressList.get(0);
                String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
                result.setMapsidogunguName(mapsidogunguName);
            }
        }

        return result;
    }

    public List<CompanyJipyoResponse> findJipyos(Pageable pageable) {
        List<Company> list = companyRepository.getListBaseCompany(pageable);
        List<CompanyJipyoResponse> jipyoList = list.stream().map(company -> {
            CompanyJipyoResponse result = new CompanyJipyoResponse();
            if(company!=null){
                List<CompanyReview> reviews = companyReviewRepository.listCompanyReviewByCompanyId(company.getId());
                Map<String,Object> rsTot = new HashMap<>();
                rsTot.put("기관명", company.getOfficeName());
                AggregateCompany agg = reviews.stream().collect(AggregateCompany::new, AggregateCompany::add, AggregateCompany::merge);

                result = new CompanyJipyoResponse(company, agg);
                List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(company.getZonecode());
                if (addressList.size() > 0) {
                    DistinctAddressResponse address = addressList.get(0);
                    String mapsidogunguName = address.getSidoName() + " " + address.getSigunguName();
                    result.setMapsidogunguName(mapsidogunguName);
                }
            }
            return result;
        }).collect(Collectors.toList());

        return jipyoList;
    }


    public Page<CompanySearchResponse> search(CompanySearchRequest companySearchRequest, Pageable pageable) {
        List<CompanySearchResponse> list = companyRepository.search(companySearchRequest, pageable);
        List<CompanySearchResponse> array = list.stream().map(res -> {
            List<DistinctAddressResponse> addressList = addressRepository.findDistinctBySigunguCode(res.getZonecode());
            if(addressList.size()>0) {
                DistinctAddressResponse address = addressList.get(0);
                String sidoName = address.getSidoName();
                String gubuName = address.getSigunguName();
                String mapsidogunguName = address.getSidoName()+" "+address.getSigunguName();
                res.setMapsidogunguName(mapsidogunguName);
            }
            return res;
        }).collect(Collectors.toList());

        Long totalSize = companyRepository.searchTotSize(companySearchRequest);
        Page<CompanySearchResponse> pagee = new PageImpl<>(list, pageable, totalSize);

        return pagee;
    }
}
