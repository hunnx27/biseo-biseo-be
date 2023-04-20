package com.onz;

import com.onz.modules.account.application.AccountService;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.infra.CompanyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class OnzApplication {
    public static void main(String[] args) {
        // 커몬코드 셋팅 함수
        SpringApplication.run(OnzApplication.class, args);
    }

    /**
     * 커몬 코드 셋팅
     */


}
