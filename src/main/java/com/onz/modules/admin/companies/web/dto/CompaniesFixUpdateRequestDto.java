package com.onz.modules.admin.companies.web.dto;

import com.onz.common.web.dto.response.enums.ProcessT;
import com.onz.common.web.dto.response.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompaniesFixUpdateRequestDto {
    private ProcessT process;
    private String adminTxt;
}
//    modi_dt	처리날자
//    modi_id	처리자_아이디
//    modi_txt	처리내용
