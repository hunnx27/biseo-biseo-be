package com.onz.modules.admin.member.livemember.web;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.common.web.BaseApiController;
import com.onz.modules.account.domain.Account;
import com.onz.modules.admin.member.livemember.application.LiveMemberService;
import com.onz.modules.admin.member.livemember.web.dto.*;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.follower.application.FollowerService;
import com.onz.modules.follower.web.dto.FollowerFindAccountResponseDto;
import com.onz.modules.review.web.dto.AmtRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "어드민 제어", description = "어드민을 제어하는 api.")
public class LiveMemberController  extends BaseApiController {

    private final LiveMemberService liveMemberService;
    private final FollowerService followerService;

    @Operation(summary = "라이브 회원 관리 ", description = "회원 관리입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = LiveMemberResponseDto.class)))
    })
    @GetMapping("/admin/liveMember")
    public ResponseEntity<ApiR<?>> liveMember(HttpServletResponse response, LiveMemberRequestDto liveMemberRequestDto, Pageable pageable) {
//        liveMemberService.liveMember(response,liveMemberRequestDto);
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(liveMemberService.liveMember(response,liveMemberRequestDto,pageable)));
        } catch (CustomException e) {
            throw e;
        }
    }
    @Operation(summary = "라이브 회원 디테일 ", description = "회원 디테일입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Account.class)))
    })
    @GetMapping("/admin/liveMember/{id}")
    public ResponseEntity<ApiR<LiveMemberDetailResponse>> liveMemberDetail(HttpServletResponse response, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(liveMemberService.liveMemberDetail(response,id)));
        } catch (Exception e) {
            throw e;
        }
    }
    @Operation(summary = "라이브 통계 관리 ", description = "회원 통계 관리입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Account.class)))
    })
    @GetMapping("/admin/liveMember/point/{id}")
    public ResponseEntity<ApiR<?>> liveMemberResponseWrapPDto(HttpServletResponse response, @PathVariable Long id, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(liveMemberService.liveMemberResponseWrapPDto(response,id,pageable)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "라이브 엑셀 내보내기 ", description = "회원 내보내기입니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Account.class)))
    })
    @PostMapping("admin/excel/live")
    public void getExcelFile(LiveMemberRequestDto liveMemberRequestDto , HttpServletResponse response) throws IOException {
        try{
            StringHttpMessageConverter converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
            converter.setWriteAcceptCharset(false);
            liveMemberService.getExcelFile(response,liveMemberRequestDto);
        } catch (IOException e) {
            throw e;
        }
    }
    @Operation(summary = "회원>기관-팔로우", description = "팔로우를 검색합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팔로우 검색 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @GetMapping("/admin/liveMember/{id}/follower")
    public List<FollowerFindAccountResponseDto> findAccountFollower(@AuthenticationPrincipal UserPrincipal me, @PathVariable Long id) {
        try {
            return followerService.findAccountFollower(me, id);
        } catch (Exception e) {
            throw e;
        }
    }

}
