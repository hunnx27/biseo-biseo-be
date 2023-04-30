package com.biseo.modules.admin.auth.web;

import com.biseo.common.exception.CustomException;
import com.biseo.common.web.ApiR;
import com.biseo.common.web.BaseApiController;
import com.biseo.modules.admin.auth.application.AdminAuthService;
import com.biseo.modules.admin.auth.web.dto.AdminCreateRequestDto;
import com.biseo.modules.admin.auth.web.dto.AdminLonginRequestDto;
import com.biseo.modules.review.web.dto.ReviewResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 제어", description = "어드민을 제어하는 api.")
public class AdminAuthController extends BaseApiController {

    private final AdminAuthService adminAuthService;

    @Operation(summary = "관리자 로그인 ", description = "관리자 로그인입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = ReviewResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ReviewResponseDto.class)))
    })
    @PostMapping("/admin/login")
    public ResponseEntity<ApiR<?>> loginAdmin(HttpServletResponse response, @RequestBody @Validated AdminLonginRequestDto adminRequestDto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(adminAuthService.loginAdmin(response,adminRequestDto)));
        } catch (CustomException e) {
            throw e;
        }
    }

    @Operation(summary = "관리자 등록 ", description = "관리자 등록입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = ReviewResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ReviewResponseDto.class)))
    })
    @PostMapping("/admin/signup")
    public ResponseEntity<?> createAdmin(@Validated @RequestBody AdminCreateRequestDto createRequestDto){
        return ResponseEntity.ok().body(adminAuthService.createAdmin(createRequestDto));
    }
}
