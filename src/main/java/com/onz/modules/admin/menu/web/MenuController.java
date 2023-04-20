package com.onz.modules.admin.menu.web;


import com.onz.common.web.ApiR;
import com.onz.common.web.BaseApiController;
import com.onz.modules.review.web.dto.AmtRequestDto;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.admin.menu.application.MenuService;
import com.onz.modules.admin.menu.web.dto.request.MenuRequsetDto;
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


@RequiredArgsConstructor
@RestController
@Tag(name = "어드민 제어", description = "어드민 제어관련 api")
public class MenuController extends BaseApiController {

    private final MenuService menuService;

    @Operation(summary = "메뉴 등록", description = "어드민 메뉴를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @PostMapping("/admin/menu")
    public void create(@AuthenticationPrincipal UserPrincipal me, @RequestBody @Validated MenuRequsetDto menuRequsetDto) {
        try {
            menuService.create(menuRequsetDto, me);
            ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "메인메뉴 선택", description = "어드민메뉴의 메인메뉴를 선택합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메인메뉴 선택", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @GetMapping("/menu/{id}")
    public ResponseEntity<ApiR<?>> selectMenu(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(menuService.selectMenu(id)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "전체 메뉴보기", description = "어드민메뉴의 메인메뉴를 읽어옵니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "메뉴전체읽기성공", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @GetMapping("/menu/all")
    public ResponseEntity<ApiR<?>> allMenu() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(menuService.allMenu()));
        } catch (Exception e) {
            throw e;
        }
    }
}
