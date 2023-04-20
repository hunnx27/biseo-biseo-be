package com.onz.modules.common.code.application;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.common.code.domain.CommonCode;
import com.onz.modules.common.code.infra.CommonJRepository;
import com.onz.modules.common.code.infra.CommonRepository;
import com.onz.modules.common.code.web.dto.CommonCodeInitRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Component
public class TestData {
    public static Map<String, Map<String,Object>> hi= new HashMap<>();
    private final CommonCodeSerivce commonCodeSerivce;
    private final CommonJRepository commonJRepository;
    private final AccountService accountService;
    @PostConstruct
    public void init(){
        List<CommonCode> commonCode = commonJRepository.findAllByCode("SC");
        commonCode.stream().map(res->{
//            if(res.getCodeSebu().charAt(0) == 'A'){
            if(res.getUseYn()!=null && res.getUseYn().equals(YN.Y)) {
                    Map<String, Object> itemMap = new HashMap<>();
                    itemMap.put("name", res.getCodeName());
                    itemMap.put("score", 20 * Integer.parseInt(String.valueOf(res.getCodeSebu().charAt(6))));
                    String temp = res.getCodeSebu().substring(0, 5);
                    String result = temp.replaceAll("@", "");
                    hi.put(result, itemMap);
//            }
            }
//            commonCodeSerivce.save(res);
            return null;
        }).collect(Collectors.toList());
    }
}
