package com.onz.modules.common.code.web;

import com.onz.common.web.ApiR;
import com.onz.modules.admin.menu.application.MenuService;
import com.onz.modules.admin.menu.web.dto.request.MenuRequsetDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.code.application.CommonCodeSerivce;
import com.onz.modules.common.code.web.dto.CommonCodeInitRequestDto;
import com.onz.modules.review.web.dto.AmtRequestDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 제어", description = "어드민 제어관련 api")
public class CommonCodeController {
    private final CommonCodeSerivce commonCodeSerivce;

    @Operation(summary = "공통코드 등록", description = "어드민 공통코드를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @PostMapping("/admin/common")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal me, @RequestBody @Validated CommonCodeInitRequestDto commonCodeInitRequestDto) {
        return ResponseEntity.ok(commonCodeSerivce.create(commonCodeInitRequestDto));
    }
    @Operation(summary = "전체 공통 코드 보기", description = "어드민메뉴의 공통코드를 읽어옵니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메뉴전체읽기성공", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @GetMapping("/admin/common")
    public ResponseEntity<ApiR<?>> allCommonCode() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(commonCodeSerivce.allCommonCode()));
        } catch (Exception e) {
            throw e;
        }
    }
}
