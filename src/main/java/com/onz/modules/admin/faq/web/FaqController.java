package com.onz.modules.admin.faq.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.common.web.BaseApiController;
import com.onz.modules.admin.companies.web.dto.CompaniesResponseDto;
import com.onz.modules.admin.event.web.dto.EventCreateRequestDto;
import com.onz.modules.admin.event.web.dto.EventSearchRequestDto;
import com.onz.modules.admin.faq.application.FaqService;
import com.onz.modules.admin.faq.web.dto.FaqCreateRequestDto;
import com.onz.modules.admin.faq.web.dto.FaqSearchRequestDto;
import com.onz.modules.admin.notice.web.dto.NoticeRequestDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 FAQ 제어", description = "어드민 FAQ 제어관련 api")
public class FaqController  extends BaseApiController {
    private final FaqService faqService;

    @Operation(summary = "FAQ 등록", description = "FAQ 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = FaqCreateRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = FaqCreateRequestDto.class)))
    })
    @PostMapping("/admin/faq")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal me, @RequestBody @Validated FaqCreateRequestDto faqCreateRequestDto) {
        return ResponseEntity.ok(faqService.create(faqCreateRequestDto,me));
    }

    @Operation(summary = "FAQ 검색  ", description = "FAQ 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = FaqSearchRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = FaqSearchRequestDto.class)))
    })
    @GetMapping("/admin/faq")
    public ResponseEntity<ApiR<?>> faqSearch(FaqSearchRequestDto faqSearchRequestDto, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(faqService.faqSearch(faqSearchRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }

    @Operation(summary = "FAQ 단일 선택  ", description = "FAQ 단일 선택 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/faq/{id}")
    public ResponseEntity<ApiR<?>> faqSearchDetail(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal me) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(faqService.faqSearchDetail(id,me)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "FAQ 업데이트  ", description = "FAQ 업데이트 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = FaqCreateRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = FaqCreateRequestDto.class)))
    })
    @PutMapping("/admin/faq/{id}")
    public ResponseEntity<ApiR<?>> faqSearchDetailfix(@PathVariable Long id, FaqCreateRequestDto faqCreateRequestDto, @AuthenticationPrincipal UserPrincipal me) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(faqService.faqSearchDetailfix(id,faqCreateRequestDto,me)));
        } catch (CustomException e) {
            throw e;
        }
    }
}
