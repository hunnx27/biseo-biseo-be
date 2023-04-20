package com.onz.modules.admin.member.deluser.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.member.deluser.application.DelUserService;
import com.onz.modules.admin.member.deluser.application.HumanService;
import com.onz.modules.admin.member.deluser.web.dto.DelUserRequestDto;
import com.onz.modules.admin.member.deluser.web.dto.HumanListRequestDto;
import com.onz.modules.admin.member.deluser.web.dto.HumanListResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Tag(name = "어드민 제어", description = "어드민을 제어하는 api.")
public class HumanUserController {
    private final HumanService humanService;

    @Operation(summary = "휴면 계정 정보 조회 ", description = "휴면계정 관리입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = HumanListRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = HumanListRequestDto.class)))
    })
    @GetMapping("/admin/human/")
    public ResponseEntity<ApiR<?>> humanAccountList(HttpServletResponse response, HumanListRequestDto humanListRequestDto, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(humanService.humanListResponseDtoList(response,humanListRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }

    @Operation(summary = "휴면 회원정보 단일 조회 ", description = "휴면 단일 조회입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = DelUserRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = DelUserRequestDto.class)))
    })
    @GetMapping("/admin/human/{id}")
    public ResponseEntity<ApiR<?>> humanAccountDetail(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(humanService.humanAccountDetail(id)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "휴면 엑셀 내보내기 ", description = "휴면 내보내기입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Account.class)))
    })
    @PostMapping("/admin/excel/human")
    public void getExcelFile(HumanListRequestDto humanListRequestDto , HttpServletResponse response) throws IOException {
        try{
         humanService.getExcelFile(response,humanListRequestDto);
        } catch (Exception e) {
            throw e;
        }
    }
}
