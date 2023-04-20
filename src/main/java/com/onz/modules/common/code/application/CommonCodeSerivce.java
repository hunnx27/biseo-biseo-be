package com.onz.modules.common.code.application;

import com.onz.common.web.dto.response.enums.Gubn;
import com.onz.common.web.dto.response.enums.Role;
import com.onz.common.web.dto.response.enums.State;
import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.admin.companies.domain.Companies;
import com.onz.modules.admin.companies.web.dto.CompaniesFixCreateRequestDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.code.domain.CommonCode;
import com.onz.modules.common.code.infra.CommonJRepository;
import com.onz.modules.common.code.infra.CommonRepository;
import com.onz.modules.common.code.web.dto.CommonCodeInitRequestDto;
import com.onz.modules.common.code.web.dto.CommonCodeInitResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.onz.modules.admin.companies.domain.QCompanies.companies;

@Service
@Transactional
@RequiredArgsConstructor
public class CommonCodeSerivce {
    private final CommonRepository commonRepository;
    private final CommonJRepository commonJRepository;
    private final AccountRepository accountRepository;

    public Long save(CommonCode commonCode) {
        return commonRepository.save(commonCode);
    }

    public CommonCode findById(Long id) {
        return commonRepository.findById(id);
    }

    public CommonCode create(CommonCodeInitRequestDto initRequestDto) {
        CommonCode commonCode = CommonCode.builder()
                .codeName(initRequestDto.getCodeName())
                .codeSebu(initRequestDto.getCodeSebu())
                .code(initRequestDto.getCode())
                .useYn(YN.Y)
                .build();
        commonJRepository.save(commonCode);
        return commonCode;
    }

    public List<CommonCodeInitResponseDto> allCommonCode() {
        List<CommonCode> commonCode = commonJRepository.findAllByOrderByCodeSebuAsc();
        List<CommonCodeInitResponseDto> response = commonCode.stream().map(res -> {
            CommonCodeInitResponseDto commonCodeInitResponseDto = new CommonCodeInitResponseDto();
            String temp = res.getCodeSebu().replaceAll("@", "").substring(0, 4);
            if (res.getCodeSebu().charAt(6) != '0') {
                commonCodeInitResponseDto.setId(res.getId());
                commonCodeInitResponseDto.setFiveScore(res.getCodeSebu() != null ? Integer.parseInt(String.valueOf(res.getCodeSebu().charAt(6))) * 20 : 0); //5점환산
                commonCodeInitResponseDto.setScore(res.getCodeSebu() != null ? Integer.parseInt(String.valueOf(res.getCodeSebu().charAt(6))) : 0);//100점 환산
                commonCodeInitResponseDto.setCodeName(String.valueOf(TestData.hi.get(temp).get("name")));
                commonCodeInitResponseDto.setCodeSebu(temp);
            } else {
                commonCodeInitResponseDto.setId(res.getId());
                commonCodeInitResponseDto.setGubn(res.getCodeName()); // FIXME
                commonCodeInitResponseDto.setSebuCode(res.getCodeName());
                commonCodeInitResponseDto.setScore(Integer.parseInt(String.valueOf(res.getCodeSebu().charAt(6))));
                commonCodeInitResponseDto.setFiveScore(Integer.parseInt(String.valueOf(res.getCodeSebu().charAt(6))));
                commonCodeInitResponseDto.setCodeSebu(temp);
            }
            return commonCodeInitResponseDto;
        }).collect(Collectors.toList());
        return response;
    }

}
