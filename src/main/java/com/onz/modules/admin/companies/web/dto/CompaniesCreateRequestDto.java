package com.onz.modules.admin.companies.web.dto;

import com.onz.common.web.dto.response.enums.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CompaniesCreateRequestDto {
    @NotNull(message = "은 반드시 입력해야 합니다  ")
    private String txt;

}
