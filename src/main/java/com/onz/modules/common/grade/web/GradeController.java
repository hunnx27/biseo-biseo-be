package com.onz.modules.common.grade.web;

import com.onz.common.web.ApiR;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.code.web.dto.CommonCodeInitRequestDto;
import com.onz.modules.common.grade.application.GradeService;
import com.onz.modules.common.grade.web.dto.GradeCreateRequestDto;
import com.onz.modules.common.grade.web.dto.GradeListResponseDto;
import com.onz.modules.review.web.dto.AmtRequestDto;
import io.swagger.v3.oas.annotations.OpenAPI30;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 제어", description = "어드민 제어관련 api")
public class GradeController {
    private final GradeService gradeService;

    @Operation(summary = "등급 등록", description = "등급 코드를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = GradeCreateRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = GradeCreateRequestDto.class)))
    })
    @PostMapping(value = "/admin/grade",consumes = "multipart/form-data")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal me, @RequestPart(name = "data") @Validated GradeCreateRequestDto gradeCreateRequestDto, @RequestPart (value = "files", required = true) List<MultipartFile> iconUrl) {
        return ResponseEntity.ok(gradeService.create(gradeCreateRequestDto,iconUrl));
    }

    @Operation(summary = "등급 리스트", description = "등급 코드를 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @GetMapping("/admin/grade")
    public List<GradeListResponseDto> list(@AuthenticationPrincipal UserPrincipal me) {
        try {
            ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
            return gradeService.list();
        } catch (Exception e) {
            throw e;
        }
    }
}
