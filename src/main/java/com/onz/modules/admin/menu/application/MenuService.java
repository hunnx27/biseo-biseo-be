package com.onz.modules.admin.menu.application;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.admin.menu.domain.Menu;
import com.onz.modules.admin.menu.infra.MenuRepository;
import com.onz.modules.admin.menu.web.dto.response.MenuSelectResponseDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.admin.menu.web.dto.request.MenuRequsetDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.onz.common.web.dto.response.enums.ErrorCode.DUPLICATE_RESOURCE;
import static com.onz.common.web.dto.response.enums.ErrorCode.NOT_ACCEPTABLE;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MenuService {

    private final MenuRepository menuRepository;

    public ResponseEntity<ApiR<?>> create(MenuRequsetDto menuRequsetDto, UserPrincipal me) {
        Menu menu = new Menu(menuRequsetDto);
        if(menuRepository.existsByMainCodeAndSubCode(menuRequsetDto.getMainCode(), menuRequsetDto.getSubCode())) {
            throw new CustomException(DUPLICATE_RESOURCE);
        }

        menuRepository.save(menu);
        return null;
    }

    public List<MenuSelectResponseDto> selectMenu(Long mainCode){
        List<Menu> list = menuRepository.findByMainCodeEqualsOrderBySubCodeAsc(mainCode);
        if(list.isEmpty()){
            throw new CustomException(NOT_ACCEPTABLE);
        }
        List<MenuSelectResponseDto>list1 = list.stream().map(res->{
            MenuSelectResponseDto menuSelectResponseDto = new MenuSelectResponseDto(res);
            return menuSelectResponseDto;
        }).collect(Collectors.toList());
        return list1;
    }

    public List<Menu> allMenu(){
        List<Menu> list = menuRepository.findAllByOrderByMainCodeAscSubCodeAsc();
        return list;
    }

    /*
     public YearAmtReview create(AmtRequestDto amtRequestDto, UserPrincipal me) {
        Long companyId = amtRequestDto.getCompanyId();
        Account account = accountService.findOne(me.getId());
        Company company = companyRepository.findById(companyId).orElse(null);
        YearAmtReview yearAmtReview = new YearAmtReview(amtRequestDto, company, account);
        amtReviewRepository.save(yearAmtReview);
        return yearAmtReview;
    }

    List<Menu> list = menuRepository.findAll().stream().map(res -> {
                if (res.getMainCode().equals(menuRequsetDto.getMainCode())) {
                    //만약 dto main code와 db main code가 동일하다면
                    if (res.getSubCode().equals(menuRequsetDto.getSubCode())) {
                        //subcode 마져 같으면 안된다
                    } else {
                        //subcode가 다르면 ㄱㅊ
                        return menuRepository.save(menu);
                    }
                } else {
                    //만약 dto와 db maincode가 일치하지않으면
                    return menuRepository.save(menu);
                }
            return null;
        }).collect(Collectors.toList());
     */

}
