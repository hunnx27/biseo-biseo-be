package com.onz.modules.admin.counsels.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.common.web.BaseApiController;
import com.onz.modules.admin.companies.web.dto.CompaniesRequestDto;
import com.onz.modules.admin.companies.web.dto.CompaniesResponseDto;
import com.onz.modules.admin.counsels.application.CounselsService;
import com.onz.modules.admin.counsels.infra.CounselsRepository;
import com.onz.modules.admin.counsels.web.dto.CounselsRequestDto;
import com.onz.modules.admin.counsels.web.dto.TagRequestDto;
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
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 제어", description = "어드민 제어관련 api")
public class CounselsController  extends BaseApiController {

    private final CounselsService counselsService;

    @Operation(summary = "상담 검색  ", description = "상담 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/counsels/q")
    public ResponseEntity<ApiR<?>> counselsSearchQ(CounselsRequestDto counselsRequestDto,Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselsService.counselsSearchQ(counselsRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "상담 카테고리로 검색  ", description = "상담 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/counsels/q/item")
    public ResponseEntity<ApiR<?>> counselsQItem(CounselsRequestDto counselsRequestDto,String qnaItem,Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselsService.counselsQItem(counselsRequestDto,qnaItem,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "상담 디테일  ", description = "상담 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/counsels/q/{id}")
    public ResponseEntity<ApiR<?>> counselQItem(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselsService.counselQItem(id)));
        } catch (CustomException e) {
            throw e;
        }
    }

    @Operation(summary = "답변 리스트 검색  ", description = "상담 답변 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/counsels/a")
    public ResponseEntity<ApiR<?>> counselsSearchA(CounselsRequestDto counselsRequestDto,Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselsService.counselsSearchA(counselsRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "상담답변 카테고리로 검색  ", description = "상담 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/counsels/a/item")
    public ResponseEntity<ApiR<?>> counselsAItem(CounselsRequestDto counselsRequestDto,String qnaItem,Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselsService.counselsAItem(counselsRequestDto,qnaItem,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "상담답변 디테일  ", description = "상담 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/counsels/a/{id}")
    public ResponseEntity<ApiR<?>> counselAItem(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselsService.counselAItem(id)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "태그로 검색  ", description = "태그 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/counsels/tag")
    public ResponseEntity<ApiR<?>> counselTag(TagRequestDto tagRequestDto, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselsService.counselTag(tagRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "상담답변 태그로 검색  ", description = "상담 검색 입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompaniesResponseDto.class)))
    })
    @GetMapping("/admin/counsels/q/tag")
    public ResponseEntity<ApiR<?>> tagCounselQ(TagRequestDto tagRequestDto,Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselsService.tagCounselQ(tagRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
}
