package com.onz.modules.admin.notice.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.common.web.BaseApiController;
import com.onz.modules.admin.companies.web.dto.CompaniesResponseDto;
import com.onz.modules.admin.counsels.web.dto.CounselsRequestDto;
import com.onz.modules.admin.menu.application.MenuService;
import com.onz.modules.admin.menu.web.dto.request.MenuRequsetDto;
import com.onz.modules.admin.notice.application.NoticeService;
import com.onz.modules.admin.notice.web.dto.NoticeRequestDto;
import com.onz.modules.admin.notice.web.dto.NoticeSearchRequestDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.review.web.dto.AmtRequestDto;
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
@Tag(name = "어드민 메세지 제어", description = "어드민 메세지 제어관련 api")
public class NoticeController  extends BaseApiController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 등록", description = "어드민 메뉴를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @PostMapping("/admin/notice")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal me, @RequestBody @Validated NoticeRequestDto noticeRequestDto) {
        return ResponseEntity.ok(noticeService.create(noticeRequestDto,me));
    }

    @Operation(summary = "공지사항 검색  ", description = "공지사항 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/notice")
    public ResponseEntity<ApiR<?>> noticeSearch(NoticeSearchRequestDto noticeSearchRequestDto, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(noticeService.noticeSearch(noticeSearchRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "공지사항 단일 선택  ", description = "공지사항 단일 선택 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/notice/{id}")
    public ResponseEntity<ApiR<?>> noticeSearchDetail(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal me) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(noticeService.noticeSearchDetail(id,me)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "공지사항 업데이트  ", description = "공지사항 업데이트 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @PutMapping("/admin/notice/{id}")
    public ResponseEntity<ApiR<?>> noticeSearchDetailfix(@PathVariable Long id,NoticeRequestDto noticeRequestDto, @AuthenticationPrincipal UserPrincipal me) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(noticeService.noticeSearchDetailfix(id,noticeRequestDto,me)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "공지사항 삭제  ", description = "공지사항 업데이트 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @DeleteMapping("/admin/notice/{id}")
    public ResponseEntity<ApiR<?>> noticeSearchDelete(@PathVariable Long id, @AuthenticationPrincipal UserPrincipal me) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(noticeService.noticeSearchDelete(id,me)));
        } catch (CustomException e) {
            throw e;
        }
    }
}
